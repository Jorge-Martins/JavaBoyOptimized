package Instructions;
import Emulator.Dmgcpu;
import Emulator.JavaBoy;

/*
 *  Unrecognized opcodes
 */
public class NoInst extends Instruction{
   
   public NoInst(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      System.out.println("Unrecognized opcode (" + JavaBoy.hexByte(b1) + ")");
      dmgcpu.terminate = true;
      dmgcpu.pc++;
   }
}
