/* Generated By:JJTree: Do not edit this line. ASTnumber.java */

package org.jzkit.a2j.codec.comp;

public class ASTnumber extends SimpleNode {
  public String number = null;
  // public String number_range = null;

  public Integer getNumber()
  {
    if ( null != number )
      return new Integer(number);
    else
      return new Integer(-1);
  }

  public ASTnumber(int id) {
    super(id);
  }

  public ASTnumber(AsnParser p, int id) {
    super(p, id);
  }

}
