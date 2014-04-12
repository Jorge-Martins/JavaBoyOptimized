package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    RL A
 */
public class RL extends Instruction{
  
   public RL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      if (((dmgcpu.registers[a]) & dmgcpu.F_ZERO) == dmgcpu.F_ZERO) {
         dmgcpu.newf = dmgcpu.F_CARRY;
      } else {
         dmgcpu.newf = 0;
      }
      dmgcpu.registers[a] <<= 1;

      if ((dmgcpu.f & dmgcpu.F_CARRY) == dmgcpu.F_CARRY) {
         dmgcpu.registers[a] |= 1;
      }

      dmgcpu.registers[a] &= 0xFF;
      if (dmgcpu.registers[a] == 0) {
         dmgcpu.newf |= dmgcpu.F_ZERO;
      }
      dmgcpu.f = dmgcpu.newf;
   }
}
