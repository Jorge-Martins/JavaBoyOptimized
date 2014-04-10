package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    JP NZ, nnnn
 *    JP Z, nnnn
 *    JP NC, nnnn
 *    JP C, nnnn
 */
public class JP_F extends Instruction{
   private short flag1, flag2;
   
   public JP_F(short flag1, short flag2, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.flag1 = flag1;
      this.flag2 = flag2;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      if ((dmgcpu.f & flag1) == flag2) {        
         dmgcpu.pc = (b3 << 8) + b2;
      } else {
         dmgcpu.pc += 3;
      }
   }
}
