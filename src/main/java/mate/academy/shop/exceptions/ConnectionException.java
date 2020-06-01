package mate.academy.shop.exceptions;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
