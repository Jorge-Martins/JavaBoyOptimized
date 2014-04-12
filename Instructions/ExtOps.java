package Instructions;
import Emulator.Dmgcpu;

/*
 * Extended Operations (Two byte instruction codes)
 * 
 */
public class ExtOps extends Instruction {
   private Instruction[] vec1 = new Instruction[8];
   private Instruction[] vec2 = new Instruction[3];

   public ExtOps(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;

      vec1[0] = new RLC_A(dmgcpu);     // RLC A    0x00
      vec1[1] = new RRC_A(dmgcpu);     // RRC A    0x08
      vec1[2] = new RL_r(dmgcpu);      // RL r     0x10
      vec1[3] = new RR_r(dmgcpu);      // RR r     0x18
      vec1[4] = new SLA_r(dmgcpu);     // SLA r    0x20
      vec1[5] = new SRA_r(dmgcpu);     // SRA r    0x28
      vec1[6] = new SWAP_r(dmgcpu);    // SWAP r   0x30
      vec1[7] = new SRL_r(dmgcpu);     // SRL r    0x38

      vec2[0] = new BIT_n(dmgcpu);     // BIT n, r    0x40
      vec2[1] = new RES_n(dmgcpu);     // RES n, r    0x80
      vec2[2] = new SET_n(dmgcpu);     // SET n, r    0xC0
   }

   public void execute(int b1, int b2, int b3, int offset) {
      int i;
      dmgcpu.pc += 2;

      if ((b2 & 0xC0) == 0) {
         i = (b2 & 0xF8) / 0x08;
     
         vec1[i].execute(b1, b2, b3, offset);
         
      } else {
          i = ((b2 & 0xC0) / 0x40) - 1;
      
         vec2[i].execute(b1, b2, b3, offset);
      
      }
   }
}



class RLC_A extends Instruction {
   private int regNum;
   private int data;

   public RLC_A(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      if ((data & 0x80) == 0x80) {
         dmgcpu.f = dmgcpu.F_CARRY;
      } else {
         dmgcpu.f = 0;
      }
      data <<= 1;
      if ((dmgcpu.f & dmgcpu.F_CARRY) == dmgcpu.F_CARRY) {
         data |= 1;
      }

      data &= 0xFF;
      if (data == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      dmgcpu.registerWrite(regNum, data);
   }
}

class RRC_A extends Instruction {
   private int regNum;
   private int data;

   public RRC_A(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      if ((data & 0x01) == 0x01) {
         dmgcpu.f = dmgcpu.F_CARRY;
      } else {
         dmgcpu.f = 0;
      }
      data >>= 1;
      if ((dmgcpu.f & dmgcpu.F_CARRY) == dmgcpu.F_CARRY) {
         data |= 0x80;
      }
      if (data == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      dmgcpu.registerWrite(regNum, data);
   }
}

class RL_r extends Instruction {
   private int regNum;
   private int data;

   public RL_r(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      if ((data & 0x80) == 0x80) {
         dmgcpu.newf = dmgcpu.F_CARRY;
      } else {
         dmgcpu.newf = 0;
      }
      data <<= 1;

      if ((dmgcpu.f & dmgcpu.F_CARRY) == dmgcpu.F_CARRY) {
         data |= 1;
      }

      data &= 0xFF;
      if (data == 0) {
         dmgcpu.newf |= dmgcpu.F_ZERO;
      }
      dmgcpu.f = dmgcpu.newf;
      dmgcpu.registerWrite(regNum, data);
   }
}

class RR_r extends Instruction {
   private int regNum;
   private int data;

   public RR_r(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      if ((data & 0x01) == 0x01) {
         dmgcpu.newf = dmgcpu.F_CARRY;
      } else {
         dmgcpu.newf = 0;
      }
      data >>= 1;

      if ((dmgcpu.f & dmgcpu.F_CARRY) == dmgcpu.F_CARRY) {
         data |= 0x80;
      }

      if (data == 0) {
         dmgcpu.newf |= dmgcpu.F_ZERO;
      }
      dmgcpu.f = dmgcpu.newf;
      dmgcpu.registerWrite(regNum, data);
   }
}

class SLA_r extends Instruction {
   private int regNum;
   private int data;

   public SLA_r(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      if ((data & 0x80) == 0x80) {
         dmgcpu.f = dmgcpu.F_CARRY;
      } else {
         dmgcpu.f = 0;
      }

      data <<= 1;

      data &= 0xFF;
      if (data == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      dmgcpu.registerWrite(regNum, data);
   }
}

class SRA_r extends Instruction {
   private int regNum;
   private int data;

   public SRA_r(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      short topBit = 0;

      topBit = (short) (data & 0x80);
      if ((data & 0x01) == 0x01) {
         dmgcpu.f = dmgcpu.F_CARRY;
      } else {
         dmgcpu.f = 0;
      }

      data >>= 1;
      data |= topBit;

      if (data == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      dmgcpu.registerWrite(regNum, data);
   }
}

class SWAP_r extends Instruction {
   private int regNum;
   private int data;

   public SWAP_r(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      data = (short) (((data & 0x0F) << 4) | ((data & 0xF0) >> 4));
      if (data == 0) {
         dmgcpu.f = dmgcpu.F_ZERO;
      } else {
         dmgcpu.f = 0;
      }

      dmgcpu.registerWrite(regNum, data);
   }
}

class SRL_r extends Instruction {
   private int regNum;
   private int data;

   public SRL_r(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      if ((data & 0x01) == 0x01) {
         dmgcpu.f = dmgcpu.F_CARRY;
      } else {
         dmgcpu.f = 0;
      }

      data >>= 1;

      if (data == 0) {
         dmgcpu.f |= dmgcpu.F_ZERO;
      }
      dmgcpu.registerWrite(regNum, data);
   }
}

class BIT_n extends Instruction {
   private int regNum;
   private int data;
   private int bitNumber;
   private short mask;

   public BIT_n(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      bitNumber = (b2 & 0x38) >> 3;
      mask = (short) (0x01 << bitNumber);
      if ((data & mask) != 0) {
         dmgcpu.f = (short) ((dmgcpu.f & dmgcpu.F_CARRY) | dmgcpu.F_HALFCARRY);
      } else {
         dmgcpu.f = (short) ((dmgcpu.f & dmgcpu.F_CARRY) | (dmgcpu.F_HALFCARRY + dmgcpu.F_ZERO));
      }
   }
}

class RES_n extends Instruction {
   private int regNum;
   private int data;
   private int bitNumber;
   private short mask;

   public RES_n(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      bitNumber = (b2 & 0x38) >> 3;
      mask = (short) (0xFF - (0x01 << bitNumber));
      data = (short) (data & mask);
      dmgcpu.registerWrite(regNum, data);
   }
}

class SET_n extends Instruction {
   private int regNum;
   private int data;
   private int bitNumber;
   private short mask;

   public SET_n(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;
   }

   public void execute(int b1, int b2, int b3, int offset) {
      regNum = b2 & 0x07;
      data = dmgcpu.registerRead(regNum);

      bitNumber = (b2 & 0x38) >> 3;
      mask = (short) (0x01 << bitNumber);
      data = (short) (data | mask);
      dmgcpu.registerWrite(regNum, data);
   }
}
