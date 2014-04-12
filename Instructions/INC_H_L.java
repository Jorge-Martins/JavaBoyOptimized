package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    INC H
 *    INC L
 * 
 */
public class INC_H_L extends Instruction{
   private int mask1, mask2, address, shift;
   
   public INC_H_L(int mask1, int mask2, int address, int shift, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.mask1 = mask1;
      this.mask2 = mask2;
      this.shift = shift;
      this.address = address;
   }
   
   private int getAddress(){
      if(shift == 0){
         return (dmgcpu.hl + 1);
      }
      
      return ((dmgcpu.hl & mask1) | 0x10);
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      
      switch ((dmgcpu.hl & mask2) >> shift){
         case 0xFF:
            dmgcpu.f |= dmgcpu.F_HALFCARRY + dmgcpu.F_ZERO;
            dmgcpu.hl = (dmgcpu.hl & mask1);
            break;
         case 0x0F:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.hl = getAddress();
            break;
         default:
            dmgcpu.hl = (dmgcpu.hl + address);
            break;
      }
   }
}
