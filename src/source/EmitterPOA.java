package source;

/**
* EmitterPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Emitter.idl
* jeudi 15 janvier 2015 19 h 23 CEST
*/

@SuppressWarnings("unchecked")
public abstract class EmitterPOA extends org.omg.PortableServer.Servant
 implements EmitterOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  @SuppressWarnings("rawtypes")
private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("sendMessage", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Emitter/sendMessage
       {
         String to = in.read_string ();
         String message = in.read_string ();
         this.sendMessage (to, message);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Emitter:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Emitter _this() 
  {
    return EmitterHelper.narrow(
    super._this_object());
  }

  public Emitter _this(org.omg.CORBA.ORB orb) 
  {
    return EmitterHelper.narrow(
    super._this_object(orb));
  }


} // class EmitterPOA
