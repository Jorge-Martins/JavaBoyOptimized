package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    RRC A
 */
public class RRC extends Instruction{
  
   public RRC(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      if (((dmgcpu.registers[a]) & 0x01) == 0x01) {
         dmgcpu.f = dmgcpu.F_CARRY;
      } else {
         dmgcpu.f = 0;
      }
      dmgcpu.registers[a] >>= 1;
      if ((dmgcpu.f & dmgcpu.F_CARRY) == dmgcpu.F_CARRY) {
         dmgcpu.registers[a] |= dmgcpu.F_ZERO;
      }
      if (dmgcpu.registers[a] == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
   }
}
