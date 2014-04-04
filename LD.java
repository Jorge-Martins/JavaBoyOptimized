
public class LD extends Instruction{
   
   public LD(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
         
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.registerWrite((b1 & 0x38) >> 3, dmgcpu.registerRead(b1 & 0x07));
   }
}
