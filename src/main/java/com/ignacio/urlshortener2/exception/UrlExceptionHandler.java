package com.ignacio.urlshortener2.exception;
import com.ignacio.urlshortener2.DataTransferObject.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
//La notación indica a Spring "esta clase se va a encargar de manejar excepciones que ocurran dentro de mis controllers"
public class UrlExceptionHandler {
    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse manejarUrlNoEncontrada(UrlNotFoundException e){
        return new ErrorResponse(e.getMessage());

    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse manejarArgumentoInvalido(IllegalArgumentException e){
        return new ErrorResponse(e.getMessage());
    }

}
