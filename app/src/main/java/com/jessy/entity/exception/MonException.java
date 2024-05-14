package com.jessy.entity.exception;

/**
 * Une exception custom qui est utilisé dans l'application de manière globale
 * elle extend Exception
 */
public class MonException extends Exception{
    /**
     * Construction de MonException qui prendra comme paramètre un message d'erreur.
     *
     * @param Message Le message d'erreur décrivant l'exception.
     */
    public MonException(String Message) {
        super(Message);}
}

