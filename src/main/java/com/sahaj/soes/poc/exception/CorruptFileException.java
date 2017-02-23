package com.sahaj.soes.poc.exception;

/**
 * Created by sukhan on 2/23/2017.
 */
public class CorruptFileException extends Exception{
    public CorruptFileException(String message){
        super("The Supplied file contains errors : " + message);
    }
}
