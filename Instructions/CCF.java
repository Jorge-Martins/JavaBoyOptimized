package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    CCF
 */
public class CCF extends Instruction{
  
   public CCF(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      if ((dmgcpu.f & dmgcpu.F_CARRY) == 0) {
         dmgcpu.f = (short) ((dmgcpu.f & dmgcpu.F_ZERO) | dmgcpu.F_CARRY);
      } else {
         dmgcpu.f = (short) (dmgcpu.f & dmgcpu.F_ZERO);
      }
   }
}
