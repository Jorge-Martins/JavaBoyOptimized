package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD SP, nnnn
 */
public class LD_SP extends Instruction{
  
   public LD_SP(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 3;
      dmgcpu.sp = (b3 << 8) + b2;
   }
}
