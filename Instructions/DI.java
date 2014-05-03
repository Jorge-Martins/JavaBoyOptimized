package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    DI
 */
public class DI extends Instruction{
  
   public DI(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.interruptsEnabled = false;
   }
}