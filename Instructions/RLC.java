package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    RLC A
 */
public class RLC extends Instruction{
  
   public RLC(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.f = 0;

      dmgcpu.registers[a] <<= 1;

      if (((dmgcpu.registers[a]) & 0x0100) != 0) {
         dmgcpu.f |= dmgcpu.F_CARRY;
         dmgcpu.registers[a] |= 1;
         dmgcpu.registers[a] &= 0xFF;
      }
      if (dmgcpu.registers[a] == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
   }
}
