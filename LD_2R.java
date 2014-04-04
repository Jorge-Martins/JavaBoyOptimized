
public class LD_2R extends Instruction{
   private int reg1, reg2;
   
   public LD_2R(int reg1, int reg2, Dmgcpu dmgcpu){
      this.reg1 = reg1;   
      this.reg2 = reg2;   
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      loadRegisters();
      
      dmgcpu.pc += 3;
      dmgcpu.registers[reg1] = b3;
      dmgcpu.registers[reg2] = b2;
      
      storeRegisters();
   }
}
