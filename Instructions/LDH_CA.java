package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LDH (FF00 + C), A
 */
public class LDH_CA extends Instruction{
  
   public LDH_CA(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.addressWrite(0xFF00 + dmgcpu.registers[c], dmgcpu.registers[a]);
   }
}
