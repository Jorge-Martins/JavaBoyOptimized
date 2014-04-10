package Instructions;
import Emulator.Dmgcpu;


public class STOP extends Instruction {

   public STOP(Dmgcpu dmgcpu){
     this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset) { 
      dmgcpu.pc += 2;

      if (dmgcpu.gbcFeatures) {
         if ((dmgcpu.ioHandler.registers[0x4D] & 0x01) == 1) {
            int newKey1Reg = dmgcpu.ioHandler.registers[0x4D] & 0xFE;
            if ((newKey1Reg & 0x80) == 0x80) {
               dmgcpu.setDoubleSpeedCpu(false);
               newKey1Reg &= 0x7F;
            } else {
               dmgcpu.setDoubleSpeedCpu(true);
               newKey1Reg |= 0x80;
               // System.out.println("CAUTION: Game uses double speed CPU, humoungus PC required!");
            }
            dmgcpu.ioHandler.registers[0x4D] = (byte) newKey1Reg;
         }
      }
   }
}
