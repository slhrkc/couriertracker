package com.salih.migros.couriertracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The exception used to return a status and a message to the calling system.
 * @author norrisshelton
 */
@SuppressWarnings("ClassWithoutNoArgConstructor")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;


    /**
     * Constructs a new runtime exception with the specified HttpStatus code and detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     * @param httpStatus the http status.  The detail message is saved for later retrieval by the {@link
     *                   #getHttpStatus()} method.
     * @param message    the detail message. The detail message is saved for later retrieval by the {@link
     *                   #getMessage()} method.
     * @see HttpStatus
     */
    public BadRequestException(String message) {
        super(message);
    }
}