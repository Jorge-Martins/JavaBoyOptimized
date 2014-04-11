package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    RET nnnn
 */
public class RET extends Instruction{
  
   public RET(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc = (JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp + 1)) << 8) + JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp));
      dmgcpu.sp += 2;
   }
}
