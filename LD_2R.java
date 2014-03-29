
public class LD_2R extends Instruction{
   private int reg1, reg2;
   
   public LD_2R(int reg1, int reg2, Dmgcpu dmgcpu){
      this.reg1 = reg1;   
      this.reg2 = reg2;   
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b2, int b3){
      dmgcpu.pc += 3;
      writeReg(reg1, b3);
      writeReg(reg2, b2);
   }
}
