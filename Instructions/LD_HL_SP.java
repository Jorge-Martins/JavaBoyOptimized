package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD HL, SP + nn 
 */
public class LD_HL_SP extends Instruction{
  
   public LD_HL_SP(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.hl = (dmgcpu.sp + offset);
      if ((dmgcpu.hl & 0x10000) != 0) {
         dmgcpu.f = dmgcpu.F_CARRY;
         dmgcpu.hl &= 0xFFFF;
      } else {
         dmgcpu.f = 0;
      }
   }
}
