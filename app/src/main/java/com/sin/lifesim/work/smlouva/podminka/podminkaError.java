package com.sin.lifesim.work.smlouva.podminka;

public class podminkaError extends RuntimeException {
    public podminkaError() {
        super();
    }

    public podminkaError(String msg) {
        super(msg);
    }

    public podminkaError(Throwable cause) {
        super(cause);
    }

    public podminkaError(String msg, Throwable cause) {
        super(msg, cause);
    }
}
