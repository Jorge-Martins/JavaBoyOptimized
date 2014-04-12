package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    RST 00
 *    RST 08
 *    RST 10
 *    RST 18
 *    RST 20
 *    RST 28
 *    RST 30
 *    RST 38
 */
public class RST extends Instruction{
   private int address;
   
   public RST(int address, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.address = address;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.sp -= 2;
      dmgcpu.addressWrite(dmgcpu.sp + 1, dmgcpu.pc >> 8);
      dmgcpu.addressWrite(dmgcpu.sp, dmgcpu.pc & 0x00FF);
      dmgcpu.pc = address;
   }
}
