
public class DEC_R extends Instruction{
   private int reg;
   
   public DEC_R(int reg, Dmgcpu dmgcpu){
      this.reg = reg;      
      this.dmgcpu = dmgcpu;
   }
   
         
   @Override
   public void execute(int b2, int b3){
      loadRegisters();
      
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      dmgcpu.f |= dmgcpu.F_SUBTRACT;
      
      switch (dmgcpu.registers[reg]) {
         case 0x00:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.registers[reg] = 0xFF;
            break;
         case 0x10:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.registers[reg] = 0x0F;
            break;
         case 0x01:
            dmgcpu.f |= dmgcpu.F_ZERO;
            dmgcpu.registers[reg] = 0x00;
            break;
         default:
            dmgcpu.registers[reg]--;
            break;
   
      }
      
      storeRegisters();
   }
}
