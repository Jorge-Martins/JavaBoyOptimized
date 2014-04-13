package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    INC (HL)
 * 
 */
public class DEC_A_HL extends Instruction{
   
   public DEC_A_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      dmgcpu.f |= dmgcpu.F_SUBTRACT;
      int dat = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.hl));
      switch (dat) {
         case 0x00:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.addressWrite(dmgcpu.hl, 0xFF);
            break;
         case 0x10:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.addressWrite(dmgcpu.hl, 0x0F);
            break;
         case 0x01:
            dmgcpu.f |= dmgcpu.F_ZERO;
            dmgcpu.addressWrite(dmgcpu.hl, 0x00);
            break;
         default:
            dmgcpu.addressWrite(dmgcpu.hl, dat - 1);
            break;
      }
   }
}
