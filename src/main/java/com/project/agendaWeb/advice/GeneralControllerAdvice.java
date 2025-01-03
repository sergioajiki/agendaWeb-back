package com.project.agendaWeb.advice;

import com.project.agendaWeb.exception.DuplicateEntryException;
import com.project.agendaWeb.exception.InvalidEmailFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {
    private final MessageSource messageSource;

    @Autowired
    public GeneralControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleDuplicateEntryException(DuplicateEntryException exception) {
        Problem problem = new Problem(
                HttpStatus.CONFLICT.value(),
                "Duplicate Entry Info",
                exception.getLocalizedMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleInvalidEmailFormatException(InvalidEmailFormatException exception) {
        Problem problem = new Problem(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Email Format",
                exception.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }
}
