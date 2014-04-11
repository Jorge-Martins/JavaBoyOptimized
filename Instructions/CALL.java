package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    CALL nnnn
 */
public class CALL extends Instruction{
  
   public CALL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 3;
      dmgcpu.sp -= 2;
      dmgcpu.addressWrite(dmgcpu.sp + 1, dmgcpu.pc >> 8);
      dmgcpu.addressWrite(dmgcpu.sp, dmgcpu.pc & 0x00FF);
      dmgcpu.pc = (b3 << 8) + b2;
   }
}
