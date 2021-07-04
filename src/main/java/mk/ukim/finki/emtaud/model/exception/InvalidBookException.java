package mk.ukim.finki.emtaud.model.exception;

public class InvalidBookException extends Exception {
    public InvalidBookException(Long id) {
        super(String.format("Book with id %d is not found"));
    }
}
