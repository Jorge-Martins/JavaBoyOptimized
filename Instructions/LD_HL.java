package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD HL, nnnn
 */
public class LD_HL extends Instruction{
  
   public LD_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 3;
      dmgcpu.hl = (b3 << 8) + b2;
   }
}
