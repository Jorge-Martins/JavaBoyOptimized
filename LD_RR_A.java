/*
 * this class emulates 2 instructions
 *  type 0 -> LD (BC), A 
 *  type 1 -> LD (DE), A
 * 
 *
 */
public class LD_RR_A extends Instruction{
   private int type;
   
   public LD_RR_A(int type, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.type = type;
   }
   
   
   @Override
   public void execute(int b2, int b3){
      loadRegisters();
      
      dmgcpu.pc++;
      if(type == 0){
         dmgcpu.addressWrite((dmgcpu.b << 8) | dmgcpu.c, dmgcpu.a);
      } else{
         dmgcpu.addressWrite((dmgcpu.d << 8) + dmgcpu.e, dmgcpu.a);
      }
   }
}
