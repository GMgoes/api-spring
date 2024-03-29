package com.example.demo.exceptions.handler;

import com.example.demo.dtos.StandardErrorMessageDto;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.PartnerWithoutGameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GameClubResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<StandardErrorMessageDto> handleConflictException(Exception e) {
        var errorMessage = new StandardErrorMessageDto(new Date(), HttpStatus.CONFLICT.value(),  e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PartnerWithoutGameException.class)
    public final ResponseEntity<StandardErrorMessageDto> handleBadRequestException(Exception e) {
        var errorMessage = new StandardErrorMessageDto(new Date(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
