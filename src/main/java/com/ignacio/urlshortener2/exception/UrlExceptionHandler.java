package com.ignacio.urlshortener2.exception;
import com.ignacio.urlshortener2.DataTransferObject.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
//La notación indica a Spring "esta clase se va a encargar de manejar excepciones que ocurran dentro de mis controllers"
public class UrlExceptionHandler {
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarUrlNoEncontrada(UrlNotFoundException e){
        return ResponseEntity .status(HttpStatus.NOT_FOUND) .body(new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND.value(),"Not Found"));

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> manejarArgumentoInvalido(IllegalArgumentException e){
    return ResponseEntity .status((HttpStatus.BAD_REQUEST)) .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), "Bad Request"));

    }
}



