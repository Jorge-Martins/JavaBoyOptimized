package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD (nnnn), SP
 */
public class LD_nn_SP extends Instruction{
  
   public LD_nn_SP(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 3;
      dmgcpu.addressWrite((b3 << 8) + b2 + 1, (dmgcpu.sp & 0xFF00) >> 8);
      dmgcpu.addressWrite((b3 << 8) + b2, (dmgcpu.sp & 0x00FF));
   }
}
