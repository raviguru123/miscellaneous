package nurture.exception;

import nurture.models.Memory;

public class MemoryNotAvailableException extends RuntimeException {
    public MemoryNotAvailableException() {
        super("Memory not available to save the file content");
    }
}
