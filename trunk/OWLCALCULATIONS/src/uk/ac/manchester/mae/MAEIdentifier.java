/* Generated By:JJTree: Do not edit this line. MAEIdentifier.java Version 4.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=MAE,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package uk.ac.manchester.mae;

public
class MAEIdentifier extends SimpleNode {
  public MAEIdentifier(int id) {
    super(id);
  }

  public MAEIdentifier(ArithmeticsParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ArithmeticsParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=4922da05d72d9241ba505236cd0ffa18 (do not edit this line) */
