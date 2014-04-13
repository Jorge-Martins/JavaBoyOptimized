package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LDH (FFnn), A
 */
public class LDH_A extends Instruction{
  
   public LDH_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.addressWrite(0xFF00 + b2, dmgcpu.registers[a]);
   }
}
