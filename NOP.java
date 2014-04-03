
public class NOP extends Instruction {

   public NOP(Dmgcpu dmgcpu){
     this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b2, int b3, int offset) { 
      dmgcpu.pc++;   
   }
}
