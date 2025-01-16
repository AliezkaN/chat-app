package org.aliezkan.chatapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.aliezkan.chatapi.dto.response.ExceptionResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
public class RootControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handleValidationExceptions(MethodArgumentNotValidException exp, HttpServletRequest req) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .forEach(errors::add);

        return ExceptionResponse.builder()
                .reqId(req.getRequestId())
                .status(BAD_REQUEST.name())
                .code(BAD_REQUEST.value())
                .validationErrors(errors)
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handleBadCredentialsException() {
        return ExceptionResponse.builder()
                .status(FORBIDDEN.name())
                .code(FORBIDDEN.value())
                .message("Login or Password is incorrect")
                .build();
    }
}
