package LK07;

 /**
  * Custom exception untuk menangani duplikasi NIS
  */
public class DuplicateNisException extends Exception {
    
    /**
     * Constructor tanpa parameter
     */
    public DuplicateNisException() {
        super("NIS sudah terdaftar di sistem!");
    }

    /**
     * Constructor dengan pesan custom
     * @param message Pesan error
     */
    public DuplicateNisException(String message) {
        super(message);
    }
}

