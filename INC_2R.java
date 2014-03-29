
public class INC_2R extends Instruction{
   private int reg1, reg2, value;
   
   public INC_2R(int reg1, int reg2, Dmgcpu dmgcpu){
      this.reg1 = reg1;   
      this.reg2 = reg2;   
      this.dmgcpu = dmgcpu;
      value = 0x0100;
   }
   
   @Override
   public void execute(int b2, int b3){
      dmgcpu.pc++;
      incReg(reg2, 1);
      
      if(readReg(reg2) == value){
         incReg(reg1, 1);
         writeReg(reg2, 0);
         
         if(readReg(reg1) == value){
            writeReg(reg1, 0);
         }
      }
   }
}
