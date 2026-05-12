package LK07;
public class DuplicateNisException extends Exception {

    public DuplicateNisException() {
        super("NIS sudah terdaftar di sistem!");
    }

    public DuplicateNisException(String message) {
        super(message);
    }
}

