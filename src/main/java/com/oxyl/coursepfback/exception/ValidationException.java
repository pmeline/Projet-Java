package com.oxyl.coursepfback.exception;

/**
 * Exception lancée lors d'une erreur de validation
 */
public class ValidationException extends RuntimeException {
    
    /**
     * Constructeur avec un message d'erreur
     * @param message le message d'erreur
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructeur avec un message d'erreur et une cause
     * @param message le message d'erreur
     * @param cause la cause de l'exception
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
} 