package com.vertex.kafkapoc.exception;

public class AppException extends Exception {
   public AppException(){
       super();
   }
   public AppException(String msg){
       super(msg);
   }
}
