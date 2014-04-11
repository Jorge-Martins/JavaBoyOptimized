package Instructions;
import Emulator.Dmgcpu;


public class DEC_L extends Instruction{
   
   public DEC_L(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      dmgcpu.f |= dmgcpu.F_SUBTRACT;
     
      switch (dmgcpu.hl & 0x00FF) {
         case 0x00:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.hl = (dmgcpu.hl & 0xFF00) | 0x00FF;
            break;
         case 0x10:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.hl = (dmgcpu.hl & 0xFF00) | 0x000F;
            break;
         case 0x01:
            dmgcpu.f |= dmgcpu.F_ZERO;
            dmgcpu.hl = (dmgcpu.hl & 0xFF00);
            break;
         default:
            dmgcpu.hl = (dmgcpu.hl & 0xFF00) | ((dmgcpu.hl & 0x00FF) - 1);
            break;
      }
   }
}
