public class DukeNoSaveFileFoundException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "I cannot find an existing save file, so I shall create a new one!\n";
    }
}

