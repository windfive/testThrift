package server;


import demo.Hello; 
import helloThrift.HelloServiceImpl; 



import org.apache.thrift.TProcessorFactory; 
import org.apache.thrift.protocol.TCompactProtocol; 
import org.apache.thrift.server.THsHaServer; 
import org.apache.thrift.server.TNonblockingServer; 
import org.apache.thrift.server.TServer; 
import org.apache.thrift.transport.TFramedTransport; 
import org.apache.thrift.transport.TNonblockingServerSocket; 
import org.apache.thrift.transport.TTransportException; 
 
/**
 * @ * @version
 */ 
public class HelloServiceAsyncServer { 
    public final static int PORT = 8989; 
 
    @SuppressWarnings({ "rawtypes", "unchecked" }) 
    private void start() { 
        try { 
            TNonblockingServerSocket socket = new TNonblockingServerSocket(PORT); 
            final Hello.Processor processor = new Hello.Processor(new HelloServiceImpl()); 
            THsHaServer.Args arg = new THsHaServer.Args(socket); 
            // ��Ч�ʵġ��ܼ��Ķ����Ʊ����ʽ�������ݴ��� 
            // ʹ�÷�������ʽ������Ĵ�С���д��䣬������ Java �е� NIO 
            arg.protocolFactory(new TCompactProtocol.Factory()); 
            arg.transportFactory(new TFramedTransport.Factory()); 
            arg.processorFactory(new TProcessorFactory(processor)); 
            System.out.println("#��������-ʹ��:������&��Ч�����Ʊ���"); 
            //TServer server = new THsHaServer(arg); 
            TServer server = new TNonblockingServer(arg); 
            server.serve(); 
        } catch (TTransportException e) { 
            e.printStackTrace(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
 
    public static void main(String args[]) { 
    	HelloServiceAsyncServer srv = new HelloServiceAsyncServer(); 
        srv.start(); 
    } 
} 
