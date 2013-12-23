package helloThrift.callback;

import org.apache.thrift.async.AsyncMethodCallback; 
import org.apache.thrift.TException;

import demo.Hello.AsyncClient.helloString_call;

public class MethodCallback implements AsyncMethodCallback <Object> { 
   Object response = null; 

   public Object getResult() { 
       // ���ؽ��ֵ
       return this.response; 
   } 

   // ������񷵻صĽ��ֵ
   @Override 
   public void onComplete(Object response) { 
       this.response = response; 
   } 
   // ������÷�������г��ֵ��쳣
   @Override
   public void onError(Exception e) { 

   } 
} 


