package org.jzkit.a2j.gen.AsnUseful;

// For logging
import java.math.BigInteger;
import org.jzkit.a2j.codec.runtime.*;

public class GeneralString_codec extends base_codec
{
  public static GeneralString_codec me = null;
  private OctetString_codec i_octetstring_codec = OctetString_codec.getCodec();

  public synchronized static GeneralString_codec getCodec()
  {
    if ( me == null )
    {
      me = new GeneralString_codec();
    }
    return me;
  }

  public Object serialize(SerializationManager sm,
                          Object type_instance,
                          boolean is_optional,
                          String type_name) throws java.io.IOException
  {
    String retval = (String)type_instance;
 
    boolean is_constructed = false;
 
    if ( ( sm.getDirection() == SerializationManager.DIRECTION_ENCODE ) && ( retval != null ) )
    {
      sm.implicit_tag(i_octetstring_codec, 
                      retval != null ? retval.getBytes(sm.getCharsetEncoding()) : null, 
                      0, 27, is_optional, "GeneralString");
    }
    else if ( sm.getDirection() == SerializationManager.DIRECTION_DECODE )
    {
      byte[] octet_str = (byte[]) sm.implicit_tag(i_octetstring_codec, null, 0, 27, is_optional, "GeneralString");
      if ( octet_str != null )
        retval = new String(octet_str, sm.getCharsetEncoding());
    }
 
    if ( ( retval == null ) && ( ! is_optional ) )
      throw new java.io.IOException("Missing mandatory member: "+type_name);
 
    return retval;
  }
}
