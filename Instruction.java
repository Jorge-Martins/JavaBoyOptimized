import java.util.*;

public abstract class Instruction {
   protected Dmgcpu dmgcpu;
   
   protected void writeReg(int reg, int value){
      switch(reg){
         case 0:
            dmgcpu.a = value;
            break;
         case 1:
            dmgcpu.b = value;
            break;
         case 2:
            dmgcpu.c = value;
            break;
         case 3:
            dmgcpu.d = value;
            break;
         case 4:
            dmgcpu.e = value;
            break;
         case 5:
            dmgcpu.f = value;
            break;  
      }
   }
   
   public abstract void execute(int b2, int b3);
}
