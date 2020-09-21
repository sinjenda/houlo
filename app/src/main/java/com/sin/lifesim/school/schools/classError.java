package com.sin.lifesim.school.schools;

public class classError extends Exception {
    public classError() {
        super();
    }

    public classError(String msg) {
        super(msg);
    }

    public classError(Throwable cause) {
        super(cause);
    }

    public classError(String msg, Throwable cause) {
        super(msg, cause);
    }
}
