/* Generated By:JJTree: Do not edit this line. ASTTag.java */

package org.jzkit.a2j.codec.comp;

public class ASTTag extends SimpleNode {

  public boolean hasTagClass = false;

  public ASTClass getTagClass()
  {
    if ( hasTagClass )
      return (ASTClass)jjtGetChild(0);

    return null;
  }

  public ASTClassNumber getClassNumber()
  {
    if ( hasTagClass )
      return (ASTClassNumber)jjtGetChild(1);

    return (ASTClassNumber)jjtGetChild(0);
  }

  public ASTTag(int id) {
    super(id);
  }

  public ASTTag(AsnParser p, int id) {
    super(p, id);
  }

}
