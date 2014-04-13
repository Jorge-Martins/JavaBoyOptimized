package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    JP (HL)
 */
public class JP_HL extends Instruction{
  
   public JP_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc = dmgcpu.hl;
   }
}
