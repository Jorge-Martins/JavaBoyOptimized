package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    CP nn
 */
public class CP extends Instruction{
  
   public CP(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.f = 0;
      if (b2 == dmgcpu.registers[a]) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      if (dmgcpu.registers[a] < b2) {
         dmgcpu.f |= dmgcpu.F_CARRY;
      }
      if (((((dmgcpu.registers[a]) & 0x0F) - (b2 & 0x0F)) & 0xFFF0) != 0x00){
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }
   }
}
