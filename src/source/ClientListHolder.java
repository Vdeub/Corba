package source;

/**
* ClientListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Receiver.idl
* jeudi 15 janvier 2015 19 h 23 CEST
*/

public final class ClientListHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public ClientListHolder ()
  {
  }

  public ClientListHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ClientListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ClientListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ClientListHelper.type ();
  }

}
