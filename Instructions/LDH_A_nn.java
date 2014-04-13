package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    LDH A, (FFnn)
 */
public class LDH_A_nn extends Instruction{
  
   public LDH_A_nn(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.registers[a] = JavaBoy.unsign(dmgcpu.addressRead(0xFF00 + b2));
   }
}
