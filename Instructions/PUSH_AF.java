package Instructions;
import Emulator.Dmgcpu;


public class PUSH_AF extends Instruction{
   
   public PUSH_AF(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.sp -= 2;
      dmgcpu.sp &= 0xFFFF;
      dmgcpu.addressWrite(dmgcpu.sp, dmgcpu.f);
      dmgcpu.addressWrite(dmgcpu.sp + 1, dmgcpu.registers[a]);
   }
}
