package source;

/**
* ConnectionOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Connection.idl
* jeudi 15 janvier 2015 19 h 23 CEST
*/

public interface ConnectionOperations 
{
  Emitter connect (String pseudo, Receiver rcv);
  void disconnect (String pseudo);
} // interface ConnectionOperations
