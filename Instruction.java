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
   
   protected void incReg(int reg, int value){
      switch(reg){
         case 0:
            dmgcpu.a += value;
            break;
         case 1:
            dmgcpu.b += value;
            break;
         case 2:
            dmgcpu.c += value;
            break;
         case 3:
            dmgcpu.d += value;
            break;
         case 4:
            dmgcpu.e += value;
            break;
         case 5:
            dmgcpu.f += value;
            break;  
      }
   }
   
   protected int readReg(int reg){
      switch(reg){
         case 0:
            return dmgcpu.a;
            
         case 1:
            return dmgcpu.b;
           
         case 2:
            return dmgcpu.c;
           
         case 3:
            return dmgcpu.d;
            
         case 4:
            return dmgcpu.e;
           
         case 5:
            return dmgcpu.f;
           
      }
      System.out.println("Instruction.readReg Error: Reg not found");
      return -1;
   }
   
   public abstract void execute(int b2, int b3);
}
