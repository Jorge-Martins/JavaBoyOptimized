/*
 * this class emulates LD A, (nnnn)
 *
 */
public class LD_A_nn extends Instruction{
   
   public LD_A_nn(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
   }
   
   @Override
   public void execute(int b1, int b2, int b3, int offset){
      dmgcpu.pc+=3;
      dmgcpu.registers[a] = JavaBoy.unsign(dmgcpu.addressRead((b3 << 8) + b2));
   }
}
