package pe.business.app.products.controller.exception;

public class CustomParameterConstraintException extends RuntimeException {

    public CustomParameterConstraintException() {
        super();
    }

    public CustomParameterConstraintException(String message) {
        super(message);
    }
}