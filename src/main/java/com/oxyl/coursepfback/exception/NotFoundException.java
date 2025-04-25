package com.oxyl.coursepfback.exception;

/**
 * Exception lancée lorsqu'une ressource n'est pas trouvée
 */
public class NotFoundException extends RuntimeException {
    
    /**
     * Constructeur avec un message d'erreur
     * @param message le message d'erreur
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructeur avec un message d'erreur et une cause
     * @param message le message d'erreur
     * @param cause la cause de l'exception
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
} 