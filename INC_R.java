
public class INC_R extends Instruction{
   private int reg;
   
   public INC_R(int reg, Dmgcpu dmgcpu){
      this.reg = reg;      
      this.dmgcpu = dmgcpu;
   }
   
         
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      
      switch (dmgcpu.registers[reg]) {
         case 0xFF:
            dmgcpu.f |= dmgcpu.F_HALFCARRY + dmgcpu.F_ZERO;
            dmgcpu.registers[reg] = 0x00;
            break;
         case 0x0F:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            dmgcpu.registers[reg] = 0x10;
            break;
         default:
            dmgcpu.registers[reg]++;
            break;
   
      }
   }
}
