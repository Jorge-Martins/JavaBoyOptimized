import java.util.*;

public class InstructionManager {
   private int a = 0, b = 1, c = 2, d= 3, e = 4, f = 5;
   private Dmgcpu dmgcpu;
   private Map<Integer, Instruction> instructionMap;
   
   private void init(){
      instructionMap = new HashMap<Integer, Instruction>();
      instructionMap.put(0x00, new NOP(dmgcpu));
      instructionMap.put(0x3E, new LD_R(a, dmgcpu));
      instructionMap.put(0x06, new LD_R(b, dmgcpu));
      instructionMap.put(0x0E, new LD_R(c, dmgcpu));
      instructionMap.put(0x16, new LD_R(d, dmgcpu));
      instructionMap.put(0x1E, new LD_R(e, dmgcpu));
   }
   
   public InstructionManager(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      init();
   }
   
   public void execute(int b1, int b2, int b3){
      Instruction i = instructionMap.get(b1);
      if(i != null){
         i.execute(b2, b3);
      } else{
         System.out.println("InstructionManager Error: Instructions not found!");
      }
   }
}
