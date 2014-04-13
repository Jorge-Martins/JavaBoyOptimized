package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    INC (HL)
 * 
 */
public class INC_A_HL extends Instruction{
   
   public INC_A_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      int dat = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.hl));
      switch (dat) {
         case 0xFF:
            dmgcpu.f |= dmgcpu.F_HALFCARRY + dmgcpu.F_ZERO;
            dmgcpu.addressWrite(dmgcpu.hl, 0x00);
            break;
         case 0x0F:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.addressWrite(dmgcpu.hl, 0x10);
            break;
         default:
            dmgcpu.addressWrite(dmgcpu.hl, dat + 1);
            break;
      }
   }
}
