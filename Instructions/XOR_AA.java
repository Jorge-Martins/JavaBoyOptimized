package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    XOR A, A 
 */
public class XOR_AA extends Instruction{
  
   public XOR_AA(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.registers[a] = 0;
      dmgcpu.f = dmgcpu.F_ZERO;
   }
}
