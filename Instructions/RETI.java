package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    RETI
 */
public class RETI extends Instruction{
  
   public RETI(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.interruptsEnabled = true;
      dmgcpu.inInterrupt = false;
      dmgcpu.pc = (JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp + 1)) << 8) + JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp));
      dmgcpu.sp += 2;
   }
}
