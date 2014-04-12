/*
 * this class emulates 4 instructions
 * type rr ->  ADD HL, BC
 *             ADD HL, DE
 * type hl -> ADD HL, HL
 * type sp -> ADD HL, SP
 * 
 */
package Instructions;
import Emulator.Dmgcpu;

public class ADD_2R extends Instruction{
   private int reg1, reg2, address;
   private String type;
   
   private void setAddress(){
      if(type.equals("rr")){
         address = (dmgcpu.hl + ((dmgcpu.registers[reg1] << 8) + dmgcpu.registers[reg2]));
      } else if(type.equals("hl")){
         
         address = (dmgcpu.hl + dmgcpu.hl);
      } else{
         address = (dmgcpu.hl + dmgcpu.sp);
      }
   }
   
   public ADD_2R(String type, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.type = type;
   }
   
   public ADD_2R(int reg1, int reg2, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.reg1 = reg1;
      this.reg2 = reg2;
      type = "rr";
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      setAddress();
      dmgcpu.pc++;
      dmgcpu.hl = address;
      
      if ((dmgcpu.hl & 0xFFFF0000) != 0) {
         dmgcpu.f = (short) ((dmgcpu.f & (dmgcpu.F_SUBTRACT + dmgcpu.F_ZERO + dmgcpu.F_HALFCARRY)) | (dmgcpu.F_CARRY));
         dmgcpu.hl &= 0xFFFF;
      } else {
         dmgcpu.f = (short) ((dmgcpu.f & (dmgcpu.F_SUBTRACT + dmgcpu.F_ZERO + dmgcpu.F_HALFCARRY)));
      }
   }
}
