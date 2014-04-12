package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    SBC A, nn
 */
public class SBC extends Instruction{
   private SUB sub;
   
   public SBC(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      sub = new SUB(dmgcpu);
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      if ((dmgcpu.f & dmgcpu.F_CARRY) != 0) {
         b2++;
      }

      sub.execute(b2);
   }
}
