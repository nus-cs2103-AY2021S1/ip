package duke.exception;

public class CorruptedDataFileException extends DukeException {

    public CorruptedDataFileException() {
    }

    @Override
    public String toString () {
        return String.format("data/duke.txt is corrupted.") ;
    }
}