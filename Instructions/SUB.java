package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    SUB A, nn
 */
public class SUB extends Instruction{
  
   public SUB(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   public void execute(int b2){
      dmgcpu.f = dmgcpu.F_SUBTRACT;
      if (((((dmgcpu.registers[a]) & 0x0F) - (b2 & 0x0F)) & 0xFFF0) != 0x00) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }
      dmgcpu.registers[a] -= b2;
      if (((dmgcpu.registers[a]) & 0xFF00) != 0) {
         dmgcpu.registers[a] &= 0x00FF;
         dmgcpu.f |= dmgcpu.F_CARRY;
      }
      if (dmgcpu.registers[a] == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;

      execute(b2);
   }
}
