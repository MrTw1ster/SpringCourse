package geekbrains.spring.lesson12.exceptions;

public class SessionExpiredException extends RuntimeException{
    public SessionExpiredException(String message) {
        super(message);
    }
}
