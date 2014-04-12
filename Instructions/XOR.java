package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    XOR A, nn
 */
public class XOR extends Instruction{
  
   public XOR(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.registers[a] ^= b2;
      if (dmgcpu.registers[a] == 0) {
         dmgcpu.f = dmgcpu.F_ZERO;
      } else {
         dmgcpu.f = 0;
      }
   }
}
