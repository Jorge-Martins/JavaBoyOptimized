package Instructions;
import Emulator.Dmgcpu;


public abstract class Instruction {
   protected Dmgcpu dmgcpu;
   protected final int a = 7, b = 0, c = 1, d = 2, e = 3;
   
   public abstract void execute(int b1, int b2, int b3, int offset);
}
