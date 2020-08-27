public class CorruptedFileException extends DukeException {
    CorruptedFileException() {
        super("Save file is corrupted, unable to load tasks.");
    }
}
