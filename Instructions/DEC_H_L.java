package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    DEC H
 *    DEC L
 * 
 */
public class DEC_H_L extends Instruction{
   private int mask1, mask2, mask3, address, shift;
   
   public DEC_H_L(int mask1, int mask2, int mask3, int address, int shift, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.mask1 = mask1;
      this.mask2 = mask2;
      this.mask3 = mask3;
      this.shift = shift;
      this.address = address;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      dmgcpu.f |= dmgcpu.F_SUBTRACT;
     
      switch ((dmgcpu.hl & mask2) >> shift){
         case 0x00:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.hl = (dmgcpu.hl & mask1) | mask2;
            break;
         case 0x10:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.hl = (dmgcpu.hl & mask1) | mask3;
            break;
         case 0x01:
            dmgcpu.f |= dmgcpu.F_ZERO;
            dmgcpu.hl = (dmgcpu.hl & mask1);
            break;
         default:
            dmgcpu.hl = (dmgcpu.hl & mask1) | ((dmgcpu.hl & mask2) - address);
            break;
      }
   }
}
