package ru.netology.diplom.exception;

public class GetFileException extends  RuntimeException{
    public GetFileException() {
    }

    public GetFileException(String message) {
        super(message);
    }

    public GetFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetFileException(Throwable cause) {
        super(cause);
    }

    public GetFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
