package nurture.exception;

public class InvalidMemoryValue extends RuntimeException {
    public InvalidMemoryValue() {
        super("memory size value must be positive");
    }
}
