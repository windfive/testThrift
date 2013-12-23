package server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import demo.Hello;
import helloThrift.HelloServiceImpl;

public class HelloServiceServer {
    /**
     * ����thrift������
     * @param args
     */
    public static void main(String[] args) {        
        try{
        //���÷������˿�Ϊ7911
        TServerSocket serverTransport = new TServerSocket(7911);
        //����Э�鹤��ΪTBinaryProtocol.Factory
        Factory proFactory = new TBinaryProtocol.Factory();
        //������������Hello�����ʵ��
        TProcessor processor = new Hello.Processor<Hello.Iface>(new HelloServiceImpl());
        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(proFactory);
        //ʹ��TSimpleServer
        TServer server = new TSimpleServer(tArgs);
        System.out.println("Start server on port 7911....");
        server.serve();
        }catch(TTransportException e){
            e.printStackTrace();
        }        
    }

}