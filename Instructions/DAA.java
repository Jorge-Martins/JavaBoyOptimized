package Instructions;
import Emulator.Dmgcpu;

/*
 * this class emulates: 
 *    DAA
 */
public class DAA extends Instruction{
  
   public DAA(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){  
      dmgcpu.pc++;

      int upperNibble = ((dmgcpu.registers[a]) & 0xF0) >> 4;
      int lowerNibble = (dmgcpu.registers[a]) & 0x0F;

      dmgcpu.newf = (short) (dmgcpu.f & dmgcpu.F_SUBTRACT);

      if ((dmgcpu.f & dmgcpu.F_SUBTRACT) == 0) {

         if ((dmgcpu.f & dmgcpu.F_CARRY) == 0) {
            if ((upperNibble <= 8) && (lowerNibble >= 0xA) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == 0)) {
               dmgcpu.registers[a] += 0x06;
            }

            if ((upperNibble <= 9) && (lowerNibble <= 0x3) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == dmgcpu.F_HALFCARRY)) {
               dmgcpu.registers[a] += 0x06;
            }

            if ((upperNibble >= 0xA) && (lowerNibble <= 0x9) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == 0)) {
               dmgcpu.registers[a] += 0x60;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }

            if ((upperNibble >= 0x9) && (lowerNibble >= 0xA) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == 0)) {
               dmgcpu.registers[a] += 0x66;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }

            if ((upperNibble >= 0xA) && (lowerNibble <= 0x3) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == dmgcpu.F_HALFCARRY)) {
               dmgcpu.registers[a] += 0x66;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }

         } else { // If carry set
            if ((upperNibble <= 0x2) && (lowerNibble <= 0x9) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == 0)) {
               dmgcpu.registers[a] += 0x60;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }
            if ((upperNibble <= 0x2) && (lowerNibble >= 0xA) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == 0)) {
               dmgcpu.registers[a] += 0x66;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }
            if ((upperNibble <= 0x3) && (lowerNibble <= 0x3) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == dmgcpu.F_HALFCARRY)) {
               dmgcpu.registers[a] += 0x66;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }
         }
      } else { // Subtract is set
         if ((dmgcpu.f & dmgcpu.F_CARRY) == 0) {
            if ((upperNibble <= 0x8) && (lowerNibble >= 0x6) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == dmgcpu.F_HALFCARRY)) {
               dmgcpu.registers[a] += 0xFA;
            }
         } else { // Carry is set
            if ((upperNibble >= 0x7) && (lowerNibble <= 0x9) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == 0)) {
               dmgcpu.registers[a] += 0xA0;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }
            if ((upperNibble >= 0x6) && (lowerNibble >= 0x6) && ((dmgcpu.f & dmgcpu.F_HALFCARRY) == dmgcpu.F_HALFCARRY)) {
               dmgcpu.registers[a] += 0x9A;
               dmgcpu.newf |= dmgcpu.F_CARRY;
            }
         }
      }

      dmgcpu.registers[a] &= 0x00FF;
      if (dmgcpu.registers[a] == 0)
         dmgcpu.newf |= dmgcpu.F_ZERO;

      dmgcpu.f = dmgcpu.newf;
   }
}
