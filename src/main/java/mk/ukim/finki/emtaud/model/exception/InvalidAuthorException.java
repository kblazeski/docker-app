package mk.ukim.finki.emtaud.model.exception;

public class InvalidAuthorException extends Exception {
    public InvalidAuthorException(Long id) {
        super(String.format("Author with id %d is not found",id));
    }
}
