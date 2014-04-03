
public class DEC_2R extends Instruction{
   private int reg1, reg2, address, value;
   
   public DEC_2R(int reg1, int reg2, Dmgcpu dmgcpu){
      this.reg1 = reg1;   
      this.reg2 = reg2;   
      this.dmgcpu = dmgcpu;
      address = 0xFF00;
      value = 0xFF;
   }
   
   @Override
   public void execute(int b2, int b3, int offset){
      loadRegisters();
      
      dmgcpu.pc++;
      dmgcpu.registers[reg2]--;
      
      if((dmgcpu.registers[reg2] & address) != 0){
         dmgcpu.registers[reg2] = value;
         dmgcpu.registers[reg1]--;
         
         if((dmgcpu.registers[reg1] & address) != 0){
            dmgcpu.registers[reg1] = value;
         }
      }
      
      storeRegisters();
   }
}
