package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    CALL NZ, nnnn
 *    CALL Z, nnnn
 *    CALL NC, nnnn
 *    CALL C, nnnn
 */
public class CALL_F extends Instruction{
   private short flag1, flag2;
   
   public CALL_F(short flag1, short flag2, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.flag1 = flag1;
      this.flag2 = flag2;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      if ((dmgcpu.f & flag1) == flag2) {        
         dmgcpu.pc += 3;
         dmgcpu.sp -= 2;
         dmgcpu.addressWrite(dmgcpu.sp + 1, dmgcpu.pc >> 8);
         dmgcpu.addressWrite(dmgcpu.sp, dmgcpu.pc & 0x00FF);
         dmgcpu.pc = (b3 << 8) + b2;
      } else {
         dmgcpu.pc += 3;
      }
   }
}
