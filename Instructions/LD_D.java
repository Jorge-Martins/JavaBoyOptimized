package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD D, D
 */
public class LD_D extends Instruction{
  
   public LD_D(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      if (dmgcpu.breakpointEnable) {
         dmgcpu.terminate = true;
         System.out.println("- Breakpoint reached");
      } else {
         dmgcpu.pc++;
      }
   }
}
