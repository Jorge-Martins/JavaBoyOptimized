package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    LDI A, (HL)
 */
public class LDI extends Instruction{
  
   public LDI(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.registers[a] = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.hl));
      dmgcpu.hl++;
   }
}
