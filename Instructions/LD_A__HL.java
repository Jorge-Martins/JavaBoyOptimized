package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 * this class emulates: 
 *    LD A, (HL-)
 */
public class LD_A__HL extends Instruction{
  
   public LD_A__HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.registers[a] = JavaBoy.unsign(dmgcpu.addressRead(dmgcpu.hl));
      dmgcpu.hl = (dmgcpu.hl - 1) & 0xFFFF;
   }
}
