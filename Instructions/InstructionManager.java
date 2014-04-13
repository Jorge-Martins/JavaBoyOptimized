package Instructions;
import Emulator.Dmgcpu;

public class InstructionManager {
   private final int a = 7, b = 0, c = 1, d = 2, e = 3;
   private Dmgcpu dmgcpu;
   
   private Instruction[] instructions;
   
   private void init(){
      instructions[0x00] = new NOP(dmgcpu);                                          // NOP
      instructions[0x01] = new LD_2R(b, c, dmgcpu);                                  // LD BC, nn
      instructions[0x02] = new LD_RR_A("bc", dmgcpu);                                // LD (BC), A
      instructions[0x03] = new INC_2R(b, c, dmgcpu);                                 // INC BC
      instructions[0x04] = new INC_R(b, dmgcpu);                                     // INC B
      instructions[0x05] = new DEC_R(b, dmgcpu);                                     // DEC B
      instructions[0x06] = new LD_R(b, dmgcpu);                                      // LD B, nn
      instructions[0x07] = new RLC(dmgcpu);                                          // RLC A
      instructions[0x08] = new LD_nn_SP(dmgcpu);                                     // LD (nnnn), SP
      instructions[0x09] = new ADD_2R(b, c, dmgcpu);                                 // ADD HL, BC
      instructions[0x0A] = new LD_A(b, c, dmgcpu);                                   // LD A, (BC)
      instructions[0x0B] = new DEC_2R(b, c, dmgcpu);                                 // DEC BC
      instructions[0x0C] = new INC_R(c, dmgcpu);                                     // INC C
      instructions[0x0D] = new DEC_R(c, dmgcpu);                                     // DEC C
      instructions[0x0E] = new LD_R(c, dmgcpu);                                      // LD C, nn
      instructions[0x0F] = new RRC(dmgcpu);                                          // RRC A
      instructions[0x10] = new STOP(dmgcpu);                                         // STOP
      instructions[0x11] = new LD_2R(d, e, dmgcpu);                                  // LD DE, nnnn
      instructions[0x12] = new LD_RR_A("de", dmgcpu);                                // LD (DE), A
      instructions[0x13] = new INC_2R(d, e, dmgcpu);                                 // INC DE
      instructions[0x14] = new INC_R(d, dmgcpu);                                     // INC D
      instructions[0x15] = new DEC_R(d, dmgcpu);                                     // DEC D
      instructions[0x16] = new LD_R(d, dmgcpu);                                      // LD D, nn
      instructions[0x17] = new RL(dmgcpu);                                           // RL A
      instructions[0x19] = new ADD_2R(d, e, dmgcpu);                                 // ADD HL, DE
      instructions[0x1A] = new LD_A(d, e, dmgcpu);                                   // LD A, (DE)
      instructions[0x1B] = new DEC_2R(d, e, dmgcpu);                                 // DEC DE
      instructions[0x1C] = new INC_R(e, dmgcpu);                                     // INC E
      instructions[0x1D] = new DEC_R(e, dmgcpu);                                     // DEC E
      instructions[0x1E] = new LD_R(e, dmgcpu);                                      // LD E, nn
      instructions[0x1F] = new RR(dmgcpu);                                           // RR A
      instructions[0x18] = new JR(dmgcpu);                                           // JR nn
      instructions[0x20] = new JR_F(dmgcpu.F_ZERO, (short)0, dmgcpu);                // JR NZ, nn
      instructions[0x21] = new LD_HL(dmgcpu);                                        // LD HL, nnnn
      instructions[0x22] = new LD_HL_A("+", dmgcpu);                                 // LD (HL+), A
      instructions[0x23] = new INC_HL(dmgcpu);                                       // INC HL
      instructions[0x24] = new INC_H_L(0x00FF, 0xFF00, 0x0100, 8, dmgcpu);           // INC H
      instructions[0x25] = new DEC_H_L(0x00FF, 0xFF00, 0x0F00, 0x0100, 8, dmgcpu);   // DEC H
      instructions[0x26] = new LD_H_L(0x00FF, 8, dmgcpu);                            // LD H, nn
      instructions[0x27] = new DAA(dmgcpu);                                          // DAA
      instructions[0x28] = new JR_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu);           // JR Z, nn
      instructions[0x29] = new ADD_2R("hl", dmgcpu);                                 // ADD HL, HL
      instructions[0x2A] = new LDI(dmgcpu);                                          // LDI A, (HL)
      instructions[0x2B] = new DEC_HL(dmgcpu);                                       // DEC HL
      instructions[0x2C] = new INC_H_L(0xFF00, 0x00FF, 1, 0, dmgcpu);                // INC L
      instructions[0x2D] = new DEC_H_L(0xFF00, 0x00FF, 0x000F, 1, 0, dmgcpu);        // DEC L
      instructions[0x2E] = new LD_H_L(0xFF00, 0, dmgcpu);                            // LD L, nn
      instructions[0x2F] = new CPL(dmgcpu);                                          // CPL A
      instructions[0x30] = new JR_F(dmgcpu.F_CARRY, (short)0, dmgcpu);               // JR NC, nn
      instructions[0x31] = new LD_SP(dmgcpu);                                        // LD SP, nnnn
      instructions[0x32] = new LD_HL_A("-", dmgcpu);                                 // LD (HL-), A
      instructions[0x33] = new INC_SP(dmgcpu);                                       // INC SP
      instructions[0x34] = new INC_A_HL(dmgcpu);                                     // INC (HL)
      instructions[0x35] = new DEC_A_HL(dmgcpu);                                     // DEC (HL)
      instructions[0x36] = new LD_A_HL(dmgcpu);                                      // LD (HL), nn
      instructions[0x37] = new SCF(dmgcpu);                                          // SCF
      instructions[0x38] = new JR_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu);         // JR C, nn
      instructions[0x39] = new ADD_2R("sp", dmgcpu);                                 // ADD HL, SP
      instructions[0x3A] = new LD_A__HL(dmgcpu);                                     // LD A, (HL-)
      instructions[0x3B] = new DEC_SP(dmgcpu);                                       // DEC SP
      instructions[0x3C] = new INC_R(a, dmgcpu);                                     // INC A
      instructions[0x3D] = new DEC_R(a, dmgcpu);                                     // DEC A
      instructions[0x3E] = new LD_R(a, dmgcpu);                                      // LD A, nn
      instructions[0x3F] = new CCF(dmgcpu);                                          // CCF
      
      // opcode 0x40 - 0x7F -> LD Reg, Reg
      Instruction ld = new LD(dmgcpu);
      for(int i = 0x40; i <= 0x7F; i++){
         //the halt and LD D instructions have this opcodes 
         if(i != 0x76 && i != 0x52){
            instructions[i] = ld;
         } else if(i == 0x76){
            instructions[i] = new HALT(dmgcpu);                                   // HALT
         } else{
            instructions[i] = new LD_D(dmgcpu);                                   // LD D, D
         }
         
      }
      
      // opcode 0x80 - 0xBF -> ALU
      Instruction alu = new ALU(dmgcpu);
      for(int i = 0x80; i <= 0xBF; i++){
         if(i != 0xAF){
            instructions[i] = alu;
         } else{
            instructions[i] = new XOR_AA(dmgcpu);                                 // XOR A, A   
         }
      }
      instructions[0xC0] = new RET_F(dmgcpu.F_ZERO, (short)0, dmgcpu);            // RET NZ, nnnn
      instructions[0xC1] = new POP_RR(b, c, dmgcpu);                              // POP BC
      instructions[0xC2] = new JP_F(dmgcpu.F_ZERO, (short)0, dmgcpu);             // JP NZ, nnnn
      instructions[0xC3] = new JP(dmgcpu);                                        // JP nnnn
      instructions[0xC4] = new CALL_F(dmgcpu.F_ZERO, (short)0, dmgcpu);           // CALL  NZ, nnnn
      instructions[0xC5] = new PUSH_RR(b, c, dmgcpu);                             // PUSH BC
      instructions[0xC6] = new ADD(dmgcpu);                                       // ADD A, nn
      instructions[0xC7] = new RST(0x00, dmgcpu);                                 // RST 00
      instructions[0xC8] = new RET_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu);       // RET Z, nnnn
      instructions[0xC9] = new RET(dmgcpu);                                       // RET nnnn
      instructions[0xCA] = new JP_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu);        // JP Z, nnnn
      instructions[0xCB] = new ExtOps(dmgcpu);                                    // ExtOps
      instructions[0xCC] = new CALL_F(dmgcpu.F_ZERO, dmgcpu.F_ZERO, dmgcpu);      // CALL Z, nnnn
      instructions[0xCD] = new CALL(dmgcpu);                                      // CALL nnnn
      instructions[0xCE] = new ADC(dmgcpu);                                       // ADC A, nn
      instructions[0xCF] = new RST(0x08, dmgcpu);                                 // RST 08
      instructions[0xD0] = new RET_F(dmgcpu.F_CARRY, (short)0, dmgcpu);           // RET NC, nnnn
      instructions[0xD1] = new POP_RR(d, e, dmgcpu);                              // POP DE
      instructions[0xD2] = new JP_F(dmgcpu.F_CARRY, (short)0, dmgcpu);            // JP NC, nnnn
      instructions[0xD4] = new CALL_F(dmgcpu.F_CARRY, (short)0, dmgcpu);          // CALL NC, nnnn
      instructions[0xD5] = new PUSH_RR(d, e, dmgcpu);                             // PUSH DE
      instructions[0xD6] = new SUB(dmgcpu);                                       // SUB A, nn
      instructions[0xD7] = new RST(0x10, dmgcpu);                                 // RST 10
      instructions[0xD8] = new RET_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu);     // RET C, nnnn
      instructions[0xD9] = new RETI(dmgcpu);                                      // RETI
      instructions[0xDA] = new JP_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu);      // JP C, nnnn
      instructions[0xDC] = new CALL_F(dmgcpu.F_CARRY, dmgcpu.F_CARRY, dmgcpu);    // CALL C, nnnn
      instructions[0xDE] = new SBC(dmgcpu);                                       // SBC A, nn
      instructions[0xDF] = new RST(0x18, dmgcpu);                                 // RST 18
      instructions[0xE0] = new LDH_A(dmgcpu);                                     // LDH (FFnn), A
      instructions[0xE1] = new POP_HL(dmgcpu);                                    // POP HL
      instructions[0xE2] = new LDH_CA(dmgcpu);                                    // LDH (FF00 + C), A
      instructions[0xE5] = new PUSH_HL(dmgcpu);                                   // PUSH HL
      instructions[0xE6] = new AND(dmgcpu);                                       // AND nn
      instructions[0xE7] = new RST(0x20, dmgcpu);                                 // RST 20
      instructions[0xE8] = new ADD_SP(dmgcpu);                                    // ADD SP, nn
      instructions[0xE9] = new JP_HL(dmgcpu);                                     // JP (HL)
      instructions[0xEA] = new LD_nn(dmgcpu);                                     // LD (nnnn), A
      instructions[0xEE] = new XOR(dmgcpu);                                       // XOR A, nn
      instructions[0xEF] = new RST(0x28, dmgcpu);                                 // RST 28
      instructions[0xF0] = new LDH_A_nn(dmgcpu);                                  // LDH A, (FFnn)
      instructions[0xF1] = new POP_AF(dmgcpu);                                    // POP AF
      instructions[0xF2] = new LD_A(dmgcpu);                                      // LD A, (FF00 + C)
      instructions[0xF3] = new DI(dmgcpu);                                        // DI
      instructions[0xF5] = new PUSH_AF(dmgcpu);                                   // PUSH AF
      instructions[0xF6] = new OR(dmgcpu);                                        // OR A, nn
      instructions[0xF7] = new RST(0x30, dmgcpu);                                 // RST 30
      instructions[0xF8] = new LD_HL_SP(dmgcpu);                                  // LD HL, SP + nn 
      instructions[0xF9] = new LD_SP_HL(dmgcpu);                                  // LD SP, HL
      instructions[0xFA] = new LD_A_nn(dmgcpu);                                   // LD A, (nnnn)
      instructions[0xFB] = new EI(dmgcpu);                                        // EI
      instructions[0xFE] = new CP(dmgcpu);                                        // CP nn 
      instructions[0xFF] = new RST(0x38, dmgcpu);                                 // RST 38
      
      Instruction noInst = new NoInst(dmgcpu);
      instructions[0xD3] = noInst;
      instructions[0xDB] = noInst;
      instructions[0xDD] = noInst;
      instructions[0xE3] = noInst;
      instructions[0xE4] = noInst;
      instructions[0xEB] = noInst;
      instructions[0xEC] = noInst;
      instructions[0xED] = noInst;
      instructions[0xF2] = noInst;
      instructions[0xF4] = noInst;
      instructions[0xFC] = noInst;
      instructions[0xFD] = noInst;
   }
   
   public InstructionManager(Dmgcpu dmgcpu){
      this.dmgcpu = dmgcpu;
      instructions = new Instruction[256];
      init();
   }
   
   public void execute(int b1, int b2, int b3, int offset){
      instructions[b1].execute(b1, b2, b3, offset);
   }
}
