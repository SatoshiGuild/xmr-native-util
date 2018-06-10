package com.satoshiguild.xmr.util;

public class InvalidBlobException extends IllegalArgumentException {
    public InvalidBlobException() {
    }

    public InvalidBlobException(String s) {
        super(s);
    }

    public InvalidBlobException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidBlobException(Throwable throwable) {
        super(throwable);
    }
}
