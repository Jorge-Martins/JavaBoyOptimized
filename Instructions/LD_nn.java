package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD (nnnn), A
 */
public class LD_nn extends Instruction{
  
   public LD_nn(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 3;
      dmgcpu.addressWrite((b3 << 8) + b2, dmgcpu.registers[a]);
   }
}
