package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    INC SP
 * 
 */
public class INC_SP extends Instruction{
   
   public INC_SP(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.sp = (dmgcpu.sp + 1) & 0xFFFF;
   }
}
