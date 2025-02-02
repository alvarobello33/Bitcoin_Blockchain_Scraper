package business;

public class BusinessException extends Exception{
    /**
     * Constructor of the class.
     * @param message Message describing the exception.
     * @param cause The cause of the exception.
     */
    public BusinessException(String message, Exception cause) {
        super(message, cause);
    }
}
