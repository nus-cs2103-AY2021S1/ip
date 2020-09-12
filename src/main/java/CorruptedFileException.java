public class CorruptedFileException extends ClippyException {
    CorruptedFileException() {
        super("Save file is corrupted, unable to load tasks.");
    }
}
