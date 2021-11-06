package nurture.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String fileName) {
        super("file not found : " + fileName);
    }
}
