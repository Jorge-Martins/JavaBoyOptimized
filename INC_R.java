
public class INC_R extends Instruction{
   private int reg;
   
   public INC_R(int reg, Dmgcpu dmgcpu){
      this.reg = reg;      
      this.dmgcpu = dmgcpu;
   }
   
         
   @Override
   public void execute(int b2, int b3){
      dmgcpu.pc++;
      dmgcpu.f &= dmgcpu.F_CARRY;
      
      switch (readReg(reg)) {
         case 0xFF:
            dmgcpu.f |= dmgcpu.F_HALFCARRY + dmgcpu.F_ZERO;
            writeReg(reg, 0x00);
            break;
         case 0x0F:
            dmgcpu.f |= dmgcpu.F_HALFCARRY;
            writeReg(reg, 0x10);
            break;
         default:
            incReg(reg, 1);
            break;
   
      }
   }
}
