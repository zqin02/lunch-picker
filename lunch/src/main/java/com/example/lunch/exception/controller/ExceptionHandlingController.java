package com.example.lunch.exception.controller;

import com.example.lunch.bean.ErrorInfo;
import com.example.lunch.exception.ErrorCode;
import com.example.lunch.exception.NoSessionException;
import com.example.lunch.exception.WebSockerException;
import com.example.lunch.service.WebSocketService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
  private final WebSocketService webSocketService;

  public ExceptionHandlingController(WebSocketService webSocketService) {
    this.webSocketService = webSocketService;
  }

  @MessageExceptionHandler({WebSockerException.class})
  public void websocketError(WebSockerException ex) {
    webSocketService.notifyError(ex);
  }
  @ExceptionHandler({NoSessionException.class})
  public ResponseEntity<ErrorInfo> noSessionError() {
    return ResponseEntity.ok(new ErrorInfo(ErrorCode.INVALID_SESSION,"No active session found"));
  }

}