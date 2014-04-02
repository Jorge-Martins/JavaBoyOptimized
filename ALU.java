import java.util.*;

public class ALU{
   private Dmgcpu dmgcpu;
   private Map<Integer, ALUInstruction> map = new HashMap<Integer, ALUInstruction>();
   
   public ALU(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      
      map.put(0, new ADD_A(dmgcpu));
      map.put(1, new ADC_A(dmgcpu));
      map.put(2, new SUB_A(dmgcpu));
      map.put(3, new SBC_A(dmgcpu));
      map.put(4, new AND_A(dmgcpu));
      map.put(5, new XOR_A(dmgcpu));
      map.put(6, new OR_A(dmgcpu));
      map.put(7, new CP_A(dmgcpu));
   }
   
   public boolean execute(int b1){
      ALUInstruction i = map.get((b1 & 0x38) >> 3);
      
      if(i != null){    
         dmgcpu.pc++;
         i.execute(b1);
         //System.out.println("ALU executed: " + i.toString());
         return true;
      }
      
      return false;
   }
}

abstract class ALUInstruction{
   protected Dmgcpu dmgcpu;
   protected int operand;
   
   public abstract void execute(int b1);
}

class ADC_A extends ALUInstruction{
   private ADD_A add;
   
   public ADC_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      add = new ADD_A(dmgcpu);
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);
      
      if ((dmgcpu.f & dmgcpu.F_CARRY) != 0) {
         operand++;
      }
      
      add.execute(b1, operand);
   }
}

class ADD_A extends ALUInstruction{
   
   public ADD_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   private void add(int b1, int operand){
      dmgcpu.f = 0;

      if ((((dmgcpu.a & 0x0F) + (operand & 0x0F)) & 0xF0) != 0x00) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }

      dmgcpu.a += operand;

      if (dmgcpu.a == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }

      if ((dmgcpu.a & 0xFF00) != 0) { // Perform 8-bit overflow
                               // and
                               // set zero flag
         if (dmgcpu.a == 0x0100) {
            dmgcpu.f |= dmgcpu.F_ZERO + dmgcpu.F_CARRY + dmgcpu.F_HALFCARRY;
            dmgcpu.a = 0;
         } else {
            dmgcpu.f |= dmgcpu.F_CARRY + dmgcpu.F_HALFCARRY;
            dmgcpu.a &= 0x00FF;
         }
      }
   }
   
   public void execute(int b1, int operand){
      add(b1, operand);
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);
      
      add(b1, operand);
   }
}

class SBC_A extends ALUInstruction{
   private SUB_A sub;
   
   public SBC_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      sub = new SUB_A(dmgcpu);
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);
      
      if ((dmgcpu.f & dmgcpu.F_CARRY) != 0) {
         operand++;
      }
      
      sub.execute(b1, operand);
   }
}

class SUB_A extends ALUInstruction{
   
   public SUB_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   private void sub(int b1, int operand){
      dmgcpu.f = dmgcpu.F_SUBTRACT;

      if ((((dmgcpu.a & 0x0F) - (operand & 0x0F)) & 0xFFF0) != 0x00) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }

      dmgcpu.a -= operand;

      if ((dmgcpu.a & 0xFF00) != 0) {
         dmgcpu.a &= 0x00FF;
         dmgcpu.f |= dmgcpu.F_CARRY;
      }
      if (dmgcpu.a == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
   }
   
   public void execute(int b1, int operand){
      sub(b1, operand);
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);
      
      sub(b1, operand);
   }
}

class AND_A extends ALUInstruction{
   
   public AND_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);

      dmgcpu.a &= operand;
      if (dmgcpu.a == 0) {
         dmgcpu.f = dmgcpu.F_ZERO;
      } else {
         dmgcpu.f = 0;
      }
   }
}

class XOR_A extends ALUInstruction{
   
   public XOR_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);
      
      dmgcpu.a ^= operand;
      if (dmgcpu.a == 0) {
         dmgcpu.f = dmgcpu.F_ZERO;
      } else {
         dmgcpu.f = 0;
      }
   }
}

class OR_A extends ALUInstruction{
   
   public OR_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);
      
      dmgcpu.a |= operand;
      if (dmgcpu.a == 0) {
         dmgcpu.f = dmgcpu.F_ZERO;
      } else {
         dmgcpu.f = 0;
      }
   }
}

         
class CP_A extends ALUInstruction{
   
   public CP_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   public void execute(int b1){
      operand = dmgcpu.registerRead(b1 & 0x07);
      
      dmgcpu.f = dmgcpu.F_SUBTRACT;
      if (dmgcpu.a == operand) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      if (dmgcpu.a < operand) {
         dmgcpu.f |= dmgcpu.F_CARRY;
      }
      if ((dmgcpu.a & 0x0F) < (operand & 0x0F)) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }
   }
}
