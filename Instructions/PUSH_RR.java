package Instructions;
import Emulator.Dmgcpu;


public class PUSH_RR extends Instruction{
   private int reg1, reg2;
   
   public PUSH_RR(int reg1, int reg2, Dmgcpu dmgcpu){
      this.reg1 = reg1;   
      this.reg2 = reg2;   
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.sp -= 2;
      dmgcpu.sp &= 0xFFFF;
      dmgcpu.addressWrite(dmgcpu.sp, dmgcpu.registers[reg2]);
      dmgcpu.addressWrite(dmgcpu.sp + 1, dmgcpu.registers[reg1]);
   }
}
