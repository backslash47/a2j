/* Generated By:JJTree: Do not edit this line. ASTSymbolsImported.java */

package org.jzkit.a2j.codec.comp;

public class ASTSymbolsImported extends SimpleNode {
  public ASTSymbolsImported(int id) {
    super(id);
  }

  public ASTSymbolsImported(AsnParser p, int id) {
    super(p, id);
  }

  public void pass1()
  {
    int i, k = jjtGetNumChildren();
 
    for (i = 0; i < k; i++)
    {
      jjtGetChild(i).pass1();
    }
 
  }            
}
