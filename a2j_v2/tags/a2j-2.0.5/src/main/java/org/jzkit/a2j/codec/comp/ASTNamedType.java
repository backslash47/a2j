/* Generated By:JJTree: Do not edit this line. ASTNamedType.java */

package org.jzkit.a2j.codec.comp;

public class ASTNamedType extends SimpleNode {

  public boolean hasid = false;

  public String getName()
  {
    if ( hasid == true )
      return ((ASTidentifier)jjtGetChild(0)).id;
    else
      return null;
  }

  public ASTType getType()
  {
    if ( hasid == true )
      return (ASTType)jjtGetChild(1);
    else
      return (ASTType)jjtGetChild(0);
  }

  public ASTNamedType(int id) {
    super(id);
  }

  public ASTNamedType(AsnParser p, int id) {
    super(p, id);
  }

}
