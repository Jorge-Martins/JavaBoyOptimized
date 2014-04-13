package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD (HL), nn
 */
public class LD_A_HL extends Instruction{
  
   public LD_A_HL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc += 2;
      dmgcpu.addressWrite(dmgcpu.hl, b2);
   }
}
