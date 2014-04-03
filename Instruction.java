
public abstract class Instruction {
   protected Dmgcpu dmgcpu;
   
   protected void loadRegisters(){
      dmgcpu.registers[0] = dmgcpu.a;
      dmgcpu.registers[1] = dmgcpu.b;
      dmgcpu.registers[2] = dmgcpu.c;
      dmgcpu.registers[3] = dmgcpu.d;
      dmgcpu.registers[4] = dmgcpu.e;
   }
   
   protected void storeRegisters(){
      dmgcpu.a = dmgcpu.registers[0];
      dmgcpu.b = dmgcpu.registers[1];
      dmgcpu.c = dmgcpu.registers[2];
      dmgcpu.d = dmgcpu.registers[3];
      dmgcpu.e = dmgcpu.registers[4];
   }
   
   public abstract void execute(int b2, int b3, int offset);
}
