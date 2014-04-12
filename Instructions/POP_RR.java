package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;


public class POP_RR extends Instruction{
   private int reg1, reg2;
   
   public POP_RR(int reg1, int reg2, Dmgcpu dmgcpu){
      this.reg1 = reg1;   
      this.reg2 = reg2;   
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.registers[reg2] = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp));
      dmgcpu.registers[reg1] = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.sp + 1));
      dmgcpu.sp += 2;
   }
}
