/* Generated By:JJTree: Do not edit this line. ASTModuleBody.java */

package org.jzkit.a2j.codec.comp;
import java.util.logging.*;

public class ASTModuleBody extends SimpleNode {

  private static Logger log = Logger.getLogger(ASTModuleBody.class.getName());

  public ASTModuleBody(int id) {
    super(id);
  }

  public ASTModuleBody(AsnParser p, int id) {
    super(p, id);
  }

  public void pass1()
  {
    log.fine("      ASTModuleBody::pass1");
    int i, k = jjtGetNumChildren();
 
    for (i = 0; i < k; i++)
    {
      jjtGetChild(i).pass1();
    }
  }                                    
}