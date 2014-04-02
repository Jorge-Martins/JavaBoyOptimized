import java.util.*;

public class InstructionManager {
   private int a = 0, b = 1, c = 2, d= 3, e = 4;
   private Dmgcpu dmgcpu;
   private Map<Integer, Instruction> instructionMap;
   
   private void init(){
      instructionMap = new HashMap<Integer, Instruction>();
      
      instructionMap.put(0x00, new NOP(dmgcpu));
      instructionMap.put(0x01, new LD_2R(b, c, dmgcpu));
      instructionMap.put(0x02, new LD_RR_A(0, dmgcpu));
      instructionMap.put(0x03, new INC_2R(b, c, dmgcpu));
      instructionMap.put(0x04, new INC_R(b, dmgcpu));
      instructionMap.put(0x05, new DEC_R(b, dmgcpu));
      instructionMap.put(0x0C, new INC_R(c, dmgcpu));
      instructionMap.put(0x0D, new DEC_R(c, dmgcpu));
      instructionMap.put(0x10, new STOP(dmgcpu));
      instructionMap.put(0x11, new LD_2R(d, e, dmgcpu));
      instructionMap.put(0x12, new LD_RR_A(1, dmgcpu));
      instructionMap.put(0x13, new INC_2R(d, e, dmgcpu));
      instructionMap.put(0x14, new INC_R(d, dmgcpu));
      instructionMap.put(0x15, new DEC_R(d, dmgcpu));
      instructionMap.put(0x1C, new INC_R(e, dmgcpu));
      instructionMap.put(0x1D, new DEC_R(e, dmgcpu));
      instructionMap.put(0x3C, new INC_R(a, dmgcpu));
      instructionMap.put(0x3E, new LD_R(a, dmgcpu));
      instructionMap.put(0x06, new LD_R(b, dmgcpu));
      instructionMap.put(0x0A, new LD_A(b, c, dmgcpu));
      instructionMap.put(0x0E, new LD_R(c, dmgcpu));
      instructionMap.put(0x16, new LD_R(d, dmgcpu));
      instructionMap.put(0x1A, new LD_A(d, e, dmgcpu));
      instructionMap.put(0x1E, new LD_R(e, dmgcpu));
      instructionMap.put(0x3D, new DEC_R(a, dmgcpu));
      instructionMap.put(0xF2, new LD_A(dmgcpu));
      instructionMap.put(0xFA, new LD_A_nn(dmgcpu));
   }
   
   public InstructionManager(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      init();
   }
   
   public boolean execute(int b1, int b2, int b3){
      Instruction i = instructionMap.get(b1);
      if(i != null){    
         i.execute(b2, b3);
         //System.out.println("executed: " + i.toString());
         return true;
      }
      
      return false;
   }
}
