package geekbrains.spring.lesson12.exceptions;

public class IncorrectParamException extends RuntimeException{
    public IncorrectParamException(String msg) {
        super(msg);
    }
}
