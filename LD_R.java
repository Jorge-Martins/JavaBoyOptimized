
public class LD_R extends Instruction{
   private int reg;
   
   public LD_R(int reg, Dmgcpu dmgcpu){
      this.reg = reg;   
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b2, int b3){
      dmgcpu.pc += 2;
      writeReg(reg, b2);
   }
}
