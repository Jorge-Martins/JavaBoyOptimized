package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    EI
 */
public class EI extends Instruction{
  
   public EI(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.ieDelay = 1;
   }
}
