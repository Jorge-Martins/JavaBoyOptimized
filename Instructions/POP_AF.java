package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;


public class POP_AF extends Instruction{
   
   public POP_AF(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.f = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp));
      dmgcpu.registers[a] = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp + 1));
      dmgcpu.sp += 2;
   }
}
