package client;

import org.apache.thrift.TException; 
import org.apache.thrift.protocol.TBinaryProtocol; 
import org.apache.thrift.protocol.TProtocol; 
import org.apache.thrift.transport.TSocket; 
import org.apache.thrift.transport.TTransport; 
import org.apache.thrift.transport.TTransportException; 

import demo.Hello; 


public class HelloServiceClient { 
/** 
    * ���� Hello ����
    * @param args 
    */ 
   public static void main(String[] args) { 
       try { 
           // ���õ��õķ����ַΪ���أ��˿�Ϊ 7911 
           TTransport transport = new TSocket("localhost", 7911); 
           transport.open(); 
           // ���ô���Э��Ϊ TBinaryProtocol 
           TProtocol protocol = new TBinaryProtocol(transport); 
           Hello.Client client = new Hello.Client(protocol); 
           // ���÷���� helloVoid ����
         //  client.helloVoid(); 
           System.out.println(client.helloString("install..."));
           
           transport.close(); 
       } catch (TTransportException e) { 
           e.printStackTrace(); 
       } catch (TException e) { 
           e.printStackTrace(); 
       } 
   } 
} 