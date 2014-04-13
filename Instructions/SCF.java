package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    SCF
 */
public class SCF extends Instruction{
  
   public SCF(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_ZERO;
      dmgcpu.f |= dmgcpu.F_CARRY;
   }
}
