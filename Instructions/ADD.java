package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    ADD A, nn
 */
public class ADD extends Instruction{
  
   public ADD(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   public void execute(int b2){
      dmgcpu.f = 0;

      if (((((dmgcpu.registers[a]) & 0x0F) + (b2 & 0x0F)) & 0xF0) != 0x00) {
         dmgcpu.f |= dmgcpu.F_HALFCARRY;
      }
      dmgcpu.registers[a] += b2;
      if (((dmgcpu.registers[a]) & 0xFF00) != 0) {
         if (dmgcpu.registers[a] == 0x0100) {
            dmgcpu.f |= dmgcpu.F_ZERO + dmgcpu.F_CARRY + dmgcpu.F_HALFCARRY;
            dmgcpu.registers[a] = 0;
         } else {
            dmgcpu.f |= dmgcpu.F_CARRY + dmgcpu.F_HALFCARRY;
            (dmgcpu.registers[a]) &= 0x00FF;
         }
      }
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;

      execute(b2);
   }
}
