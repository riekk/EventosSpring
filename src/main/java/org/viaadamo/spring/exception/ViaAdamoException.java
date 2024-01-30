package org.viaadamo.spring.exception;

import org.hibernate.HibernateException;

public class ViaAdamoException extends Exception {

    //private long errorCode;
    private ErrorCode errorCode;

    public ViaAdamoException(ErrorCode errorCode, String s, HibernateException e) {
        this.errorCode = errorCode;
    }

    public ViaAdamoException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }



    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getDetailMessage() {
        return errorCode + " " + this.getMessage();
    }

}
