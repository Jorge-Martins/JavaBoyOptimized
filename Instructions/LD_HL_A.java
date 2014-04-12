package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    LD (HL+), A
 *    LD (HL-), A
 */
public class LD_HL_A extends Instruction{
   private String type;
   
   public LD_HL_A(String type, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.type = type;
   }
   
   private int getAddress(){
      if(type.equals("+")){
         return ((dmgcpu.hl + 1) & 0xFFFF);
      } 

      return (dmgcpu.hl - 1);   
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;
      dmgcpu.addressWrite(dmgcpu.hl, dmgcpu.registers[a]);
      dmgcpu.hl = getAddress();
   }
}
