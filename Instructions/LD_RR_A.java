package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates 2 instructions
 *  type bc -> LD (BC), A 
 *  type de -> LD (DE), A
 * 
 *
 */
public class LD_RR_A extends Instruction{
   private String type;
   
   public LD_RR_A(String type, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.type = type;
   }
   
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      if(type.equals("bc")){
         dmgcpu.addressWrite(((dmgcpu.registers[b]) << 8) | (dmgcpu.registers[c]), dmgcpu.registers[a]);
      } else{
         dmgcpu.addressWrite(((dmgcpu.registers[d]) << 8) + (dmgcpu.registers[e]), dmgcpu.registers[a]);
      }
   }
}
