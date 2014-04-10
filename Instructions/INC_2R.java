package Instructions;
import Emulator.Dmgcpu;


public class INC_2R extends Instruction{
   private int reg1, reg2, value;
   
   public INC_2R(int reg1, int reg2, Dmgcpu dmgcpu){
      this.reg1 = reg1;   
      this.reg2 = reg2;   
      this.dmgcpu = dmgcpu;
      value = 0x0100;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.registers[reg2]++;
      
      if(dmgcpu.registers[reg2] == value){
         dmgcpu.registers[reg1]++;
         dmgcpu.registers[reg2] = 0;
         
         if(dmgcpu.registers[reg1] == value){
            dmgcpu.registers[reg1] = 0;
         }
      }
   }
}
