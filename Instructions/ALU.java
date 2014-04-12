package Instructions;
import Emulator.Dmgcpu;


public class ALU extends Instruction{
   private ALUInstruction[] vec = new ALUInstruction[8];
   
   public ALU(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      
      vec[0] = new ADD_A(dmgcpu);      // ADD A, r
      vec[1] = new ADC_A(dmgcpu);      // ADC A, r
      vec[2] = new SUB_A(dmgcpu);      // SUB A, r
      vec[3] = new SBC_A(dmgcpu);      // SBC A, r
      vec[4] = new AND_A(dmgcpu);      // AND A, r
      vec[5] = new XOR_A(dmgcpu);      // XOR A, r
      vec[6] = new OR_A(dmgcpu);       // OR A, r
      vec[7] = new CP_A(dmgcpu);       // CP A, r (compare)
   }
   
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      vec[((b1 & 0x38) >> 3)].execute(b1);
   }
}

abstract class ALUInstruction{
   protected Dmgcpu dmgcpu;
   protected int operand;
   protected final int a = 7, b = 0, c = 1, d = 2, e = 3;
   
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

      if (((((dmgcpu.registers[a]) & 0x0F) + (operand & 0x0F)) & 0xF0) != 0x00) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }

      dmgcpu.registers[a] += operand;

      if (dmgcpu.registers[a] == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }

      if (((dmgcpu.registers[a]) & 0xFF00) != 0) { 
         if (dmgcpu.registers[a] == 0x0100) {
            dmgcpu.f |= dmgcpu.F_ZERO + dmgcpu.F_CARRY + dmgcpu.F_HALFCARRY;
            dmgcpu.registers[a] = 0;
         } else {
            dmgcpu.f |= dmgcpu.F_CARRY + dmgcpu.F_HALFCARRY;
            dmgcpu.registers[a] &= 0x00FF;
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

      if (((((dmgcpu.registers[a]) & 0x0F) - (operand & 0x0F)) & 0xFFF0) != 0x00) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }

      dmgcpu.registers[a] -= operand;

      if (((dmgcpu.registers[a]) & 0xFF00) != 0) {
         dmgcpu.registers[a] &= 0x00FF;
         dmgcpu.f |= dmgcpu.F_CARRY;
      }
      if (dmgcpu.registers[a] == 0) {
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

      dmgcpu.registers[a] &= operand;
      if (dmgcpu.registers[a] == 0) {
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
      
      dmgcpu.registers[a] ^= operand;
      if (dmgcpu.registers[a] == 0) {
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
      
      dmgcpu.registers[a] |= operand;
      if (dmgcpu.registers[a] == 0) {
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
      if (dmgcpu.registers[a] == operand) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      if (dmgcpu.registers[a] < operand) {
         dmgcpu.f |= dmgcpu.F_CARRY;
      }
      if (((dmgcpu.registers[a]) & 0x0F) < (operand & 0x0F)) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }
   }
}
