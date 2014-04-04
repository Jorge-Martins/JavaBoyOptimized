
public class HALT extends Instruction{
   
   public HALT(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
         
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.interruptsEnabled = true;
      
      while (dmgcpu.ioHandler.registers[0x0F] == 0) {
         dmgcpu.initiateInterrupts();
         dmgcpu.instrCount++;
      }
      dmgcpu.pc++;
   }
}
