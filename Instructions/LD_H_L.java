package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD H, nn
 *    LD L, nn
 */
public class LD_H_L extends Instruction{
   private int mask, shift;
   
   public LD_H_L(int mask, int shift, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.mask = mask;
      this.shift = shift;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.hl = (dmgcpu.hl & mask) | (b2 << shift);
   }
}
