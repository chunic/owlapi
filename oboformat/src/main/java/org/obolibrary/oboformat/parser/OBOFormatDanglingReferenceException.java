package org.obolibrary.oboformat.parser;

/** The Class OBOFormatDanglingReferenceException. */
public class OBOFormatDanglingReferenceException extends OBOFormatException {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7805725187214533880L;

    /** Instantiates a new oBO format dangling reference exception. */
    public OBOFormatDanglingReferenceException() {}

    /** Instantiates a new oBO format dangling reference exception.
     * 
     * @param message
     *            the message */
    public OBOFormatDanglingReferenceException(String message) {
        super(message);
    }

    /** Instantiates a new oBO format dangling reference exception.
     * 
     * @param e
     *            the e */
    public OBOFormatDanglingReferenceException(Throwable e) {
        super(e);
    }

    /** Instantiates a new oBO format dangling reference exception.
     * 
     * @param message
     *            the message
     * @param e
     *            the e */
    public OBOFormatDanglingReferenceException(String message, Throwable e) {
        super(message, e);
    }
}
