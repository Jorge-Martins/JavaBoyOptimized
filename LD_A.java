/*
 * this class emulates 3 instructions
 * type 0 ->  LD A, (FF00 + C)
 * type 1 -> LD A, (BC) e LD A, (DE)
 * 
 */
public class LD_A extends Instruction{
   private int reg1, reg2, address, type;
   
   private void setAddress(){
      if(type == 0){
         address = 0xFF00 + dmgcpu.c;
      } else{
         loadRegisters();
         address = ((dmgcpu.registers[reg1] << 8) + dmgcpu.registers[reg2]);
      }
   }
   
   public LD_A(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      type = 0;
   }
   
   public LD_A(int reg1, int reg2, Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      this.reg1 = reg1;
      this.reg2 = reg2;
      type = 1;
   }
   
   @Override
   public void execute(int b2, int b3, int offset){
      setAddress();
      dmgcpu.pc++;
      dmgcpu.a = JavaBoy.unsign(dmgcpu.addressRead(address));
   }
}
