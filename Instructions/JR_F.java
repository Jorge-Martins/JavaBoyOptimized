package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    JR NZ, nn
 *    JR Z, nn
 *    JR NC, nn
 *    JR C, nn
 */
public class JR_F extends Instruction{
   private short flag1, flag2;
   
   public JR_F(short flag1, short flag2, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.flag1 = flag1;
      this.flag2 = flag2;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      if ((dmgcpu.f & flag1) == flag2) {
         dmgcpu.pc += 2 + offset;
      } else {
         dmgcpu.pc += 2;
      }
   }
}
