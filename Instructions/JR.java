package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    JR nn
 */
public class JR extends Instruction{
  
   public JR(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2 + offset;  
   }
}
