package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD SP, HL
 */
public class LD_SP_HL extends Instruction{
  
   public LD_SP_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.sp = dmgcpu.hl;
   }
}
