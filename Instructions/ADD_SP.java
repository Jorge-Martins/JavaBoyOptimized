package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    ADD SP, nn
 */
public class ADD_SP extends Instruction{
  
   public ADD_SP(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.sp = (dmgcpu.sp + offset);
      if ((dmgcpu.sp & 0xFFFF0000) != 0) {
         dmgcpu.f = (short) ((dmgcpu.f & (dmgcpu.F_SUBTRACT + dmgcpu.F_ZERO + dmgcpu.F_HALFCARRY)) | (dmgcpu.F_CARRY));
         dmgcpu.sp &= 0xFFFF;
      } else {
         dmgcpu.f = (short) ((dmgcpu.f & (dmgcpu.F_SUBTRACT + dmgcpu.F_ZERO + dmgcpu.F_HALFCARRY)));
      }
   }
}
