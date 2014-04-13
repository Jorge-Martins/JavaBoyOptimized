package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    DEC HL
 * 
 */
public class DEC_HL extends Instruction{
   
   public DEC_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      if (dmgcpu.hl == 0) {
         dmgcpu.hl = 0xFFFF;
      } else {
         dmgcpu.hl--;
      }
   }
}
