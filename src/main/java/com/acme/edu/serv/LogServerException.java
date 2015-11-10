package com.acme.edu.serv;

/**
 * Created by pugach on 10/11/15.
 */
public class LogServerException extends Exception {
    /**
     * Конструктор по умолчанию
     */
    public LogServerException() {
    }

    /**
     * Конструктор, принимающий в качестве параметра комментарий к ошибке
     * @param message - комментарий
     */
    public LogServerException(String message) {
    }
}
