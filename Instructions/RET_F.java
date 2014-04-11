package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    RET NZ, nnnn
 *    RET Z, nnnn
 *    RET NC, nnnn
 *    RET C, nnnn
 */
public class RET_F extends Instruction{
   private short flag1, flag2;
   
   public RET_F(short flag1, short flag2, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.flag1 = flag1;
      this.flag2 = flag2;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      if ((dmgcpu.f & flag1) == flag2) {        
         dmgcpu.pc = (JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp + 1)) << 8) + JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp));
         dmgcpu.sp += 2;
      } else {
         dmgcpu.pc++;
      }
   }
}
