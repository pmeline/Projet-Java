package com.oxyl.coursepfback.dto;

/**
 * DTO représentant une réponse d'erreur
 */
public class ErrorResponse {
    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    /**
     * Constructeur par défaut
     */
    public ErrorResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Constructeur avec tous les paramètres
     * @param status le code HTTP de l'erreur
     * @param error le type d'erreur
     * @param message le message d'erreur
     * @param path le chemin de la requête
     */
    public ErrorResponse(int status, String error, String message, String path) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * @return le timestamp de l'erreur
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp le timestamp à définir
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return le code HTTP de l'erreur
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status le code HTTP à définir
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return le type d'erreur
     */
    public String getError() {
        return error;
    }

    /**
     * @param error le type d'erreur à définir
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return le message d'erreur
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message le message d'erreur à définir
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return le chemin de la requête
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path le chemin de la requête à définir
     */
    public void setPath(String path) {
        this.path = path;
    }
} 