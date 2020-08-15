public class ViscountIndexOutOfBoundsException extends ViscountException {
    private int index;
    private static final String ERROR_MESSAGE = "Alas, your task list does not contain a task\nnumber %d.";

    public ViscountIndexOutOfBoundsException(int index) {
        super();
        this.index = index;
    }

    @Override
    public String toString() {
        return String.format(ViscountIndexOutOfBoundsException.ERROR_MESSAGE, index);
    }
}
