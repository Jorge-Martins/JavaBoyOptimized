package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    JP nnnn
 */
public class JP extends Instruction{
  
   public JP(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc = (b3 << 8) + b2; 
   }
}
