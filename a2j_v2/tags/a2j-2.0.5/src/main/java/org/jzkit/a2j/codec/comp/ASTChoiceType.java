/* Generated By:JJTree: Do not edit this line. ASTChoiceType.java */

package org.jzkit.a2j.codec.comp;

import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.*;

public class ASTChoiceType extends SimpleNode {

  private static Logger log = Logger.getLogger(ASTChoiceType.class.getName());

  public ASTChoiceType(int id) {
    super(id);
  }

  public ASTChoiceType(AsnParser p, int id) {
    super(p, id);
  }

  public void getChoiceMembers(ChoiceTypeInfo cti)
  {
    CodecBuilderInfo info = CodecBuilderInfo.getInfo();
                                                                           
    log.fine("ASTChoiceType::getChoiceMembers");

    ASTElementTypeList choice_elements = (ASTElementTypeList)jjtGetChild(0);
    int i, k = choice_elements.jjtGetNumChildren();

    for (i = 0; i < k; i++)
    {
      ASTElementType choice_element = (ASTElementType)(choice_elements.jjtGetChild(i));

      if ( choice_element.which == 1 )
      {
        ASTNamedType name = (ASTNamedType)(choice_element.jjtGetChild(0));

        String element_name = null;
        ASTType element_type_info = name.getType();

        boolean has_tagging = false;
        int tag_class = -1;
        int tag_number = -1;
        boolean is_implicit = ( info.default_tagging_is_explicit == true ? false : true );                                                        

        // Find out if the type is tagged
        if ( element_type_info.which == 1 ) // It's a builtin type
        {
          ASTBuiltinType bit = (ASTBuiltinType) (element_type_info.jjtGetChild(0));
 
          if ( bit.which == 6 )
          {
            has_tagging = true;
            // It's a tagged type, extract tagging information and proceed
            // with the actual type info...
            ASTTaggedType tt = (ASTTaggedType) (bit.jjtGetChild(0));
            is_implicit = tt.isImplicit();

            // The real type we want to process is inside the tagging info
            element_type_info = tt.getType();

            // Extract the tagging info
            ASTTag tag = tt.getTag();
            if ( tag.hasTagClass )
              tag_class = tag.getTagClass().tag_class;
            else
              tag_class = 0x80; // Assume context tag class if none given
   
            ASTClassNumber cn = tag.getClassNumber();
   
            if ( cn.which == 1 )
            {
              // It's a number
              tag_number = cn.getNumber().getNumber().intValue();
            }
            else
            {
              log.fine("Unhandled tag number type");
              System.exit(0);
              // LATER: Should throw an exception here
            }
          }
        }

        String type_name = element_type_info.getTypeName();

        if ( name.jjtGetNumChildren() == 2 )
        {
          // Then named type has identifier
          element_name = name.getName().replace ( '-', '_' );
        }
        else
        {
          // Need to make up element name from type name
          element_name = (type_name+"_var").replace ( '-', '_' );
        }

        // If it's a tagged built in type, just check some specials
        if ( element_type_info.which == 1 ) // It's a builtin type
        {
          ASTBuiltinType bit = (ASTBuiltinType) (element_type_info.jjtGetChild(0));
          switch ( bit.which )
          {
            case 2:  // SetOrSequence
              ASTSetOrSequenceType sos = (ASTSetOrSequenceType)(bit.jjtGetChild(0));
              type_name=element_name+"_inline"+info.getNextInlineCounter();
              log.fine("Creating inline type "+type_name);
              // if ( sos.which == 1 )
              // {
                info.createTypeInfoFor(type_name, element_type_info);
              // }
              // else
              // {               
              //   info.createTypeInfoFor(type_name, element_type_info);
              // }
              break;

            case 3:  // SetOrSequenceOf
              // ASTSetOrSequenceOfType soso = (ASTSetOrSequenceOfType)(bit.jjtGetChild(0));
              // ASTType sot = (ASTType) (soso.jjtGetChild(0));
              // type_name=element_name+"_inline"+info.getNextInlineCounter();
              // info.createTypeInfoFor(type_name, sot);

              type_name=element_name+"_inline"+info.getNextInlineCounter();
              info.createTypeInfoFor(type_name, element_type_info);        

              // if ( sot.which == 1 )
              // {
              //   System.err.println("Creating inline type "+type_name);
              //   ASTBuiltinType sub_bit = (ASTBuiltinType) (sot.jjtGetChild(0));
              //   // subtype_reference depends upon internal type....
              //   switch ( sub_bit.which )
              //   {
              //     case 2:
              //       System.err.println("SEQUENCEOF SEQUENCE");
              //       info.createTypeInfoFor(type_name, sot);
              //       break;
              //     case 4: // SequenceOf Choice
              //       System.err.println("SEQUENCEOF CHOICE ");
              //       info.createTypeInfoFor(type_name, sot);
              //       break;
              //     default:
              //       System.err.println("SOST SEQUENCEOF something ok.. "+sub_bit.which+" "+info.getInternalClass(bit.which));
              //       info.createTypeInfoFor(type_name, sot);
              //       break;
              //   }
              // }
              // else
              // {
              //   ASTDefinedType sub_dt = (ASTDefinedType) (sot.jjtGetChild(0));
              //   System.err.println("SEQUENCEOF defined type "+ sub_dt.getTypeReference().typeref);
              //   type_name =  sub_dt.getTypeReference().typeref;
              // }                                                                                                                            
              break;
	      case 4:  // Choice
	        type_name=element_name+"_inline"+info.getNextInlineCounter();
		info.createTypeInfoFor(type_name, element_type_info);
		break;

            default:
                // Not fatal.. just not a special case
		// log.log(Level.SEVERE,"Unhandled internal type "+bit.which+" "+type_name+" in choice body ("+element_name+")");
                break;
          }
        }

        cti.registerTaggedMember(element_name,
                                 tag_class,
                                 tag_number,
                                 is_implicit,
                                 type_name,
                                 choice_element.optional);

        log.fine("Adding Choice element "+tag_class+
                           " "+tag_number+" "+is_implicit+
                           " "+element_name+
                           " "+ type_name +
                               ( choice_element.optional == true ? " OPTIONAL " : "" ) );
      }
    }
  }

  // public String getBaseClassName(String element_name)
  // {
  //   return element_name+"_codec ";
  // }
}
