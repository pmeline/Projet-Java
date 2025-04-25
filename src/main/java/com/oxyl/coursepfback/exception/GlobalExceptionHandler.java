package com.oxyl.coursepfback.exception;

import com.oxyl.coursepfback.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Gestionnaire global des exceptions de l'application
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Gère les exceptions de type NotFoundException
     * @param ex l'exception
     * @param request la requête web
     * @return la réponse d'erreur
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
        logger.error("Ressource non trouvée: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Ressource non trouvée",
                        ex.getMessage(),
                        request.getDescription(false)
                ));
    }

    /**
     * Gère les exceptions de type ValidationException
     * @param ex l'exception
     * @param request la requête web
     * @return la réponse d'erreur
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, WebRequest request) {
        logger.error("Erreur de validation: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Erreur de validation",
                        ex.getMessage(),
                        request.getDescription(false)
                ));
    }

    /**
     * Gère toutes les autres exceptions non gérées
     * @param ex l'exception
     * @param request la requête web
     * @return la réponse d'erreur
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Erreur inattendue: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Erreur interne du serveur",
                        ex.getMessage(),
                        request.getDescription(false)
                ));
    }
} 