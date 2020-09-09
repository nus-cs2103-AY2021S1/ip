package duke.error;

public class DeleteOutOfBounds extends Exception {
    public DeleteOutOfBounds(int i) {
        super(String.format("    List only has %d items", i));
    }
}
