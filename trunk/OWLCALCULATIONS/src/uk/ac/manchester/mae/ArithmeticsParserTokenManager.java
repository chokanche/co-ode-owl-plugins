/* Generated By:JJTree&JavaCC: Do not edit this line. ArithmeticsParserTokenManager.java */
package uk.ac.manchester.mae;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.protege.editor.owl.model.OWLModelManager;
import org.semanticweb.owl.model.OWLDataProperty;
import org.semanticweb.owl.expression.OWLEntityChecker;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.coode.manchesterowlsyntax.ManchesterOWLSyntaxEditorParser;
import org.semanticweb.owl.model.OWLDataFactory;
import org.semanticweb.owl.expression.ParserException;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLObject;
import org.protege.editor.owl.ui.clsdescriptioneditor.AutoCompleterMatcher;

/** Token Manager. */
public class ArithmeticsParserTokenManager implements ArithmeticsParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x1814000000L) != 0L)
         {
            jjmatchedKind = 17;
            return 10;
         }
         return -1;
      case 1:
         if ((active0 & 0x1814000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 1;
            return 10;
         }
         return -1;
      case 2:
         if ((active0 & 0x1814000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 2;
            return 10;
         }
         return -1;
      case 3:
         if ((active0 & 0x14000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 3;
            return 10;
         }
         return -1;
      case 4:
         if ((active0 & 0x14000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 4;
            return 10;
         }
         return -1;
      case 5:
         if ((active0 & 0x14000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 5;
            return 10;
         }
         return -1;
      case 6:
         if ((active0 & 0x14000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 6;
            return 10;
         }
         return -1;
      case 7:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 6)
            {
               jjmatchedKind = 17;
               jjmatchedPos = 6;
            }
            return -1;
         }
         if ((active0 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 7;
            return 10;
         }
         return -1;
      case 8:
         if ((active0 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 8;
            return 10;
         }
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 6)
            {
               jjmatchedKind = 17;
               jjmatchedPos = 6;
            }
            return -1;
         }
         return -1;
      case 9:
         if ((active0 & 0x10000000L) != 0L)
         {
            if (jjmatchedPos < 8)
            {
               jjmatchedKind = 17;
               jjmatchedPos = 8;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjStopAtPos(0, 10);
      case 36:
         return jjStopAtPos(0, 25);
      case 40:
         return jjStopAtPos(0, 32);
      case 41:
         return jjStopAtPos(0, 33);
      case 44:
         return jjStopAtPos(0, 23);
      case 59:
         return jjStopAtPos(0, 24);
      case 61:
         return jjStopAtPos(0, 29);
      case 62:
         return jjStopAtPos(0, 27);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x10000000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x804000000L);
      case 91:
         return jjStopAtPos(0, 30);
      case 93:
         return jjStopAtPos(0, 31);
      case 94:
         return jjStopAtPos(0, 34);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x1000000000L);
      case 123:
         return jjStopAtPos(0, 8);
      case 125:
         jjmatchedKind = 9;
         return jjMoveStringLiteralDfa1_0(0x80L);
      default :
         return jjMoveNfa_0(8, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 80:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000L);
      case 84:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      case 85:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(2, 7);
         break;
      case 77:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000L);
      case 79:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000L);
      case 80:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000L);
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 40:
         if ((active0 & 0x800000000L) != 0L)
            return jjStopAtPos(3, 35);
         else if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(3, 36);
         break;
      case 76:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000000L);
      case 82:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000L);
      case 73:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000L);
      case 84:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 79:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000000L);
      case 83:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa8_0(active0, 0x4000000L);
      case 84:
         return jjMoveStringLiteralDfa8_0(active0, 0x10000000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 60:
         if ((active0 & 0x4000000L) != 0L)
            return jjStopAtPos(8, 26);
         break;
      case 79:
         return jjMoveStringLiteralDfa9_0(active0, 0x10000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
static private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa10_0(active0, 0x10000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
static private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 60:
         if ((active0 & 0x10000000L) != 0L)
            return jjStopAtPos(10, 28);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 64;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 8:
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 11)
                        kind = 11;
                     jjCheckNAddStates(0, 4);
                  }
                  else if ((0x842000000000L & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                  }
                  else if ((0x400400800000000L & l) != 0L)
                  {
                     if (kind > 20)
                        kind = 20;
                  }
                  else if ((0x280000000000L & l) != 0L)
                  {
                     if (kind > 21)
                        kind = 21;
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 11)
                        kind = 11;
                     jjCheckNAddStates(5, 11);
                  }
                  if (curChar == 47)
                     jjAddStates(12, 13);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 11:
                  if ((0x400400800000000L & l) != 0L && kind > 20)
                     kind = 20;
                  break;
               case 12:
                  if ((0x280000000000L & l) != 0L && kind > 21)
                     kind = 21;
                  break;
               case 13:
                  if ((0x842000000000L & l) != 0L && kind > 22)
                     kind = 22;
                  break;
               case 32:
                  if (curChar == 47)
                     jjAddStates(12, 13);
                  break;
               case 33:
                  if (curChar == 47)
                     jjCheckNAddStates(14, 16);
                  break;
               case 34:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(14, 16);
                  break;
               case 35:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 36:
                  if (curChar == 10 && kind > 5)
                     kind = 5;
                  break;
               case 37:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 38:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 39:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 40:
                  if (curChar == 42)
                     jjAddStates(17, 18);
                  break;
               case 41:
                  if ((0xffff7fffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(42, 40);
                  break;
               case 42:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(42, 40);
                  break;
               case 43:
                  if (curChar == 47 && kind > 6)
                     kind = 6;
                  break;
               case 44:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAddStates(0, 4);
                  break;
               case 45:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAddTwoStates(45, 46);
                  break;
               case 47:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(19, 21);
                  break;
               case 49:
                  if ((0x400400800000000L & l) != 0L)
                     jjAddStates(22, 23);
                  break;
               case 50:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(51, 52);
                  break;
               case 51:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(51, 52);
                  break;
               case 53:
                  if (curChar != 48)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(24, 26);
                  break;
               case 55:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(55, 52);
                  break;
               case 56:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(56, 52);
                  break;
               case 57:
                  if (curChar != 48)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAddStates(5, 11);
                  break;
               case 59:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAddTwoStates(59, 46);
                  break;
               case 60:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAddTwoStates(60, 46);
                  break;
               case 62:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(27, 29);
                  break;
               case 63:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(30, 32);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 8:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 17)
                        kind = 17;
                     jjCheckNAdd(10);
                  }
                  else if (curChar == 126)
                  {
                     if (kind > 20)
                        kind = 20;
                  }
                  if (curChar == 79)
                     jjAddStates(33, 34);
                  else if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 0:
                  if (curChar == 78 && kind > 15)
                     kind = 15;
                  break;
               case 1:
                  if (curChar == 79)
                     jjCheckNAdd(0);
                  break;
               case 2:
                  if (curChar == 73)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 4:
                  if (curChar == 80)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 5:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if (curChar == 67)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (curChar == 88)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 9:
               case 10:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAdd(10);
                  break;
               case 11:
                  if (curChar == 126 && kind > 20)
                     kind = 20;
                  break;
               case 14:
                  if (curChar == 79)
                     jjAddStates(33, 34);
                  break;
               case 15:
                  if (curChar == 71 && kind > 15)
                     kind = 15;
                  break;
               case 16:
                  if (curChar == 78)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 73)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 18:
                  if (curChar == 68)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 19:
                  if (curChar == 73)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 20:
                  if (curChar == 82)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 21:
                  if (curChar == 82)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 23:
                  if (curChar == 86)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 24:
                  if (curChar == 69)
                     jjCheckNAdd(0);
                  break;
               case 25:
                  if (curChar == 68)
                     jjstateSet[jjnewStateCnt++] = 24;
                  break;
               case 26:
                  if (curChar == 68)
                     jjstateSet[jjnewStateCnt++] = 25;
                  break;
               case 27:
                  if (curChar == 73)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 28:
                  if (curChar == 82)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 29:
                  if (curChar == 82)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 30:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 31:
                  if (curChar == 86)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 34:
                  jjAddStates(14, 16);
                  break;
               case 39:
                  jjCheckNAddTwoStates(39, 40);
                  break;
               case 41:
               case 42:
                  jjCheckNAddTwoStates(42, 40);
                  break;
               case 46:
                  if ((0x100000001000L & l) != 0L && kind > 11)
                     kind = 11;
                  break;
               case 48:
                  if ((0x100000001000L & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAdd(49);
                  break;
               case 49:
                  if (curChar == 126)
                     jjAddStates(22, 23);
                  break;
               case 52:
                  if ((0x100000001000L & l) != 0L && kind > 16)
                     kind = 16;
                  break;
               case 54:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(55);
                  break;
               case 55:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(55, 52);
                  break;
               case 58:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(59);
                  break;
               case 59:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAddTwoStates(59, 46);
                  break;
               case 61:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(62);
                  break;
               case 62:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(27, 29);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 34:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(14, 16);
                  break;
               case 39:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 41:
               case 42:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(42, 40);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 64 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   45, 46, 47, 48, 49, 58, 60, 46, 61, 63, 48, 49, 33, 38, 34, 35, 
   37, 41, 43, 47, 48, 49, 50, 53, 54, 56, 52, 62, 48, 49, 63, 48, 
   49, 23, 31, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, "\175\55\76", "\173", "\175", "\41", 
null, null, null, null, null, null, null, null, null, null, null, null, "\54", 
"\73", "\44", "\123\124\117\122\105\124\117\40\74", "\76", 
"\101\120\120\114\111\105\123\124\117\40\74", "\75", "\133", "\135", "\50", "\51", "\136", "\123\125\115\50", 
"\163\165\155\50", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x1ffff38f81L, 
};
static final long[] jjtoSkip = {
   0x7eL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[64];
static private final int[] jjstateSet = new int[128];
static protected char curChar;
/** Constructor. */
public ArithmeticsParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public ArithmeticsParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 64; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
