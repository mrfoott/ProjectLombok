package guru.springframework.spring6restmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// Add annotation @ControllerAdvice will allow to have a global exception handler and handle it globally!!!
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException() {
        System.out.println("Global exception handler!!!");
        System.out.println("ERROR!!!\n404 NOT FOUND");

        return ResponseEntity.notFound().build();
    }
}
