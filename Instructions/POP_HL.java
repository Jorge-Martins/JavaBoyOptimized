package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;


public class POP_HL extends Instruction{
   
   public POP_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.hl = (JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp + 1)) << 8) + JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp));
      dmgcpu.sp += 2;
   }
}
