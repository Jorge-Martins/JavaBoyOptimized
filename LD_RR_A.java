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
   public void execute(int b2, int b3){
      loadRegisters();
      
      dmgcpu.pc++;
      if(type.equals("bc")){
         dmgcpu.addressWrite((dmgcpu.b << 8) | dmgcpu.c, dmgcpu.a);
      } else{
         dmgcpu.addressWrite((dmgcpu.d << 8) + dmgcpu.e, dmgcpu.a);
      }
   }
}
