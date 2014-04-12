package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    ADC A, nn
 */
public class ADC extends Instruction{
   private ADD add;
   
   public ADC(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      add = new ADD(dmgcpu);
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      
      if ((dmgcpu.f & dmgcpu.F_CARRY) != 0) {
         b2++;
      }

      add.execute(b2);
   }
}
