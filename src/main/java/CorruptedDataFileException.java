public class CorruptedDataFileException extends Exception {

    public CorruptedDataFileException() {
    }

    @Override
    public String toString () {
        return String.format("data/duke.txt is corrupted.") ;
    }
}