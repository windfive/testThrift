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
     * 启动thrift服务器
     * @param args
     */
    public static void main(String[] args) {        
        try{
        //设置服务器端口为7911
        TServerSocket serverTransport = new TServerSocket(7911);
        //设置协议工厂为TBinaryProtocol.Factory
        Factory proFactory = new TBinaryProtocol.Factory();
        //关联处理器与Hello服务的实现
        TProcessor processor = new Hello.Processor<Hello.Iface>(new HelloServiceImpl());
        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(proFactory);
        //使用TSimpleServer
        TServer server = new TSimpleServer(tArgs);
        System.out.println("Start server on port 7911....");
        server.serve();
        }catch(TTransportException e){
            e.printStackTrace();
        }        
    }

}