/* Generated By:JJTree: Do not edit this line. ASTModuleIdentifier.java */

package org.jzkit.a2j.codec.comp;

public class ASTModuleIdentifier extends SimpleNode {

  public ASTmodulereference reference = null;

  public ASTModuleIdentifier(int id) {
    super(id);
  }

  public ASTModuleIdentifier(AsnParser p, int id) {
    super(p, id);
  }

  public ASTmodulereference getModuleReference()
  {
    return (ASTmodulereference) jjtGetChild(0);
  }

  public void pass1()
  {
    int i, k = jjtGetNumChildren();

    for (i = 0; i < k; i++)
    {
      Node o = jjtGetChild(i);

      if ( o instanceof ASTmodulereference )
          reference = (ASTmodulereference) o;

      jjtGetChild(i).pass1();
    }
  }
}
