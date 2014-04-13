package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    INC HL
 * 
 */
public class INC_HL extends Instruction{
   
   public INC_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.hl = (dmgcpu.hl + 1) & 0xFFFF;
   }
}
