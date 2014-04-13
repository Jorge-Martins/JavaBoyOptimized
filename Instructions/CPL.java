package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    CPL A
 */
public class CPL extends Instruction{
  
   public CPL(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;            
      dmgcpu.registers[a] = (short) ((~(dmgcpu.registers[a])) & 0x00FF);
      dmgcpu.f = (short) ((dmgcpu.f & (dmgcpu.F_CARRY | dmgcpu.F_ZERO)) | dmgcpu.F_SUBTRACT | dmgcpu.F_HALFCARRY);
   }
}
