package Instructions;
import Emulator.Dmgcpu;

import java.util.*;

public class InstructionManager {
   private final int a = 7, b = 0, c = 1, d = 2, e = 3;
   private Dmgcpu dmgcpu;
   private Map<Integer, Instruction> instructionMap;
   
   private void init(){
      instructionMap = new HashMap<Integer, Instruction>();
      
      instructionMap.put(0x00, new NOP(dmgcpu));                                       // NOP
      instructionMap.put(0x01, new LD_2R(b, c, dmgcpu));                               // LD BC, nn
      instructionMap.put(0x02, new LD_RR_A("bc", dmgcpu));                             // LD (BC), A
      instructionMap.put(0x03, new INC_2R(b, c, dmgcpu));                              // INC BC
      instructionMap.put(0x04, new INC_R(b, dmgcpu));                                  // INC B
      instructionMap.put(0x05, new DEC_R(b, dmgcpu));                                  // DEC B
      instructionMap.put(0x06, new LD_R(b, dmgcpu));                                   // LD B, nn
      instructionMap.put(0x09, new ADD(b, c, dmgcpu));                                 // ADD HL, BC
      instructionMap.put(0x0A, new LD_A(b, c, dmgcpu));                                // LD A, (BC)
      instructionMap.put(0x0B, new DEC_2R(b, c, dmgcpu));                              // DEC BC
      instructionMap.put(0x0C, new INC_R(c, dmgcpu));                                  // INC C
      instructionMap.put(0x0D, new DEC_R(c, dmgcpu));                                  // DEC C
      instructionMap.put(0x0E, new LD_R(c, dmgcpu));                                   // LD C, nn
      instructionMap.put(0x10, new STOP(dmgcpu));                                      // STOP
      instructionMap.put(0x11, new LD_2R(d, e, dmgcpu));                               // LD DE, nnnn
      instructionMap.put(0x12, new LD_RR_A("de", dmgcpu));                             // LD (DE), A
      instructionMap.put(0x13, new INC_2R(d, e, dmgcpu));                              // INC DE
      instructionMap.put(0x14, new INC_R(d, dmgcpu));                                  // INC D
      instructionMap.put(0x15, new DEC_R(d, dmgcpu));                                  // DEC D
      instructionMap.put(0x16, new LD_R(d, dmgcpu));                                   // LD D, nn
      instructionMap.put(0x19, new ADD(d, e, dmgcpu));                                 // ADD HL, DE
      instructionMap.put(0x1A, new LD_A(d, e, dmgcpu));                                // LD A, (DE)
      instructionMap.put(0x1B, new DEC_2R(d, e, dmgcpu));                              // DEC DE
      instructionMap.put(0x1C, new INC_R(e, dmgcpu));                                  // INC E
      instructionMap.put(0x1D, new DEC_R(e, dmgcpu));                                  // DEC E
      instructionMap.put(0x1E, new LD_R(e, dmgcpu));                                   // LD E, nn
      instructionMap.put(0x18, new JR(dmgcpu));                                        // JR nn
      instructionMap.put(0x20, new JR_F(dmgcpu.F_ZERO, (short)0, dmgcpu));             // JR NZ, nn
      instructionMap.put(0x26, new LD_H_L(0x00FF, 8, dmgcpu));                         // LD H, nn
      instructionMap.put(0x27, new DAA(dmgcpu));                                       // DAA
      instructionMap.put(0x28, new JR_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu));        // JR Z, nn
      instructionMap.put(0x29, new ADD("hl", dmgcpu));                                 // ADD HL, HL
      instructionMap.put(0x2D, new DEC_L(dmgcpu));                                     // DEC L
      instructionMap.put(0x2E, new LD_H_L(0xFF00, 0, dmgcpu));                         // LD L, nn
      instructionMap.put(0x30, new JR_F(dmgcpu.F_CARRY, (short)0, dmgcpu));            // JR NC, nn
      instructionMap.put(0x38, new JR_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu));      // JR C, nn
      instructionMap.put(0x39, new ADD("sp", dmgcpu));                                 // ADD HL, SP
      instructionMap.put(0x3C, new INC_R(a, dmgcpu));                                  // INC A
      instructionMap.put(0x3D, new DEC_R(a, dmgcpu));                                  // DEC A
      instructionMap.put(0x3E, new LD_R(a, dmgcpu));                                   // LD A, nn
      
      // opcode 0x40 - 0x7F -> LD Reg, Reg
      Instruction ld = new LD(dmgcpu);
      for(int i = 0x40; i <= 0x7F; i++){
         //the halt instruction has this opcode 
         if(i != 0x76){
            instructionMap.put(i, ld);
         } else{
            instructionMap.put(i, new HALT(dmgcpu));
         }
         
      }
      
      //          opcode 0x80 - 0xBF -> ALU
      Instruction alu = new ALU(dmgcpu);
      for(int i = 0x80; i <= 0xBF; i++){
         instructionMap.put(i, alu);
      }
      instructionMap.put(0xC0, new RET_F(dmgcpu.F_ZERO, (short)0, dmgcpu));            // RET NZ, nnnn
      instructionMap.put(0xC2, new JP_F(dmgcpu.F_ZERO, (short)0, dmgcpu));             // JP NZ, nnnn
      instructionMap.put(0xC3, new JP(dmgcpu));                                        // JP nnnn
      instructionMap.put(0xC4, new CALL_F(dmgcpu.F_ZERO, (short)0, dmgcpu));           // CALL  NZ, nnnn
      instructionMap.put(0xC7, new RST(0x00, dmgcpu));                                 // RST 00
      instructionMap.put(0xC8, new RET_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu));       // RET Z, nnnn
      instructionMap.put(0xC9, new RET(dmgcpu));                                       // RET nnnn
      instructionMap.put(0xCA, new JP_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu));        // JP Z, nnnn
      instructionMap.put(0xCB, new ExtOps(dmgcpu));                                    // ExtOps
      instructionMap.put(0xCC, new CALL_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu));      // CALL Z, nnnn
      instructionMap.put(0xCD, new CALL(dmgcpu));                                      // CALL nnnn
      instructionMap.put(0xCF, new RST(0x08, dmgcpu));                                 // RST 08
      instructionMap.put(0xD0, new RET_F(dmgcpu.F_CARRY, (short)0, dmgcpu));           // RET NC, nnnn
      instructionMap.put(0xD2, new JP_F(dmgcpu.F_CARRY, (short)0, dmgcpu));            // JP NC, nnnn
      instructionMap.put(0xD4, new CALL_F(dmgcpu.F_CARRY, (short)0, dmgcpu));          // CALL NC, nnnn
      instructionMap.put(0xD7, new RST(0x10, dmgcpu));                                 // RST 10
      instructionMap.put(0xD8, new RET_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu));     // RET C, nnnn
      instructionMap.put(0xDA, new JP_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu));      // JP C, nnnn
      instructionMap.put(0xDC, new CALL_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu));    // CALL C, nnnn
      instructionMap.put(0xDF, new RST(0x18, dmgcpu));                                 // RST 18
      instructionMap.put(0xE7, new RST(0x20, dmgcpu));                                 // RST 20
      instructionMap.put(0xEF, new RST(0x28, dmgcpu));                                 // RST 28
      instructionMap.put(0xF2, new LD_A(dmgcpu));                                      // LD A, (FF00 + C)
      instructionMap.put(0xF7, new RST(0x30, dmgcpu));                                 // RST 30
      instructionMap.put(0xFA, new LD_A_nn(dmgcpu));                                   // LD A, (nnnn)
      instructionMap.put(0xFF, new RST(0x38, dmgcpu));                                 // RST 38
   }
   
   public InstructionManager(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      init();
   }
   
   public boolean execute(int b1, int b2, int b3, int offset){
      Instruction i = instructionMap.get(b1);
      if(i != null){    
         i.execute(b1, b2, b3, offset);
         //System.out.println("executed: " + i.toString());
         return true;
      }
      
      return false;
   }
   
//   public boolean execute(int b1){
//      return alu.execute(b1);
//   }
}
