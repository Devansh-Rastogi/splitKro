package com.devansh.splitKro.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String msg) {
        super(msg);
    }

    public GroupNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
