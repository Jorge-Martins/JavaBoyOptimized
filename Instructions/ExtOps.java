package Instructions;
import Emulator.Dmgcpu;

import java.util.*;

/*
 * Extended Operations (Two byte instruction codes)
 * 
 */
public class ExtOps extends Instruction {
   private Map<Integer, Instruction> map1 = new HashMap<Integer, Instruction>();
   private Map<Integer, Instruction> map2 = new HashMap<Integer, Instruction>();

   public ExtOps(Dmgcpu dmgcpu) {
      this.dmgcpu = dmgcpu;

      map1.put(0x00, new RLC_A(dmgcpu));     // RLC A
      map1.put(0x08, new RRC_A(dmgcpu));     // RRC A
      map1.put(0x10, new RL_r(dmgcpu));      // RL r
      map1.put(0x18, new RR_r(dmgcpu));      // RR r
      map1.put(0x20, new SLA_r(dmgcpu));     // SLA r
      map1.put(0x28, new SRA_r(dmgcpu));     // SRA r
      map1.put(0x30, new SWAP_r(dmgcpu));    // SWAP r
      map1.put(0x38, new SRL_r(dmgcpu));     // SRL r

      map2.put(0x40, new BIT_n(dmgcpu));     // BIT n, r
      map2.put(0x80, new RES_n(dmgcpu));     // RES n, r
      map2.put(0xC0, new SET_n(dmgcpu));     // SET n, r
   }

   public void execute(int b1, int b2, int b3, int offset) {
      Instruction i;
      dmgcpu.pc += 2;

      if ((b2 & 0xC0) == 0) {
         i = map1.get((b2 & 0xF8));

         if (i != null) {
            i.execute(b1, b2, b3, offset);
            // System.out.println("ExtOps executed: " + i.toString());
         } else {
            System.out.println("ExtOps error: Instruction not found in map1: op = " + (b2 & 0xF8));
         }
      } else {
          i = map2.get((b2 & 0xC0));
         
         if (i != null) {
            i.execute(b1, b2, b3, offset);
            //System.out.println("ExtOps executed: " + i.toString());
         } else {
            System.out.println("ExtOps error: Instruction not found in map2: op = " + (b2 & 0xC0));
         }
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
