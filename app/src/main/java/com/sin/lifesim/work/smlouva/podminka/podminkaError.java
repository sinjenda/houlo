package com.sin.lifesim.work.smlouva.podminka;

@SuppressWarnings("unused")
public class podminkaError extends RuntimeException {
    @SuppressWarnings("unused")
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
