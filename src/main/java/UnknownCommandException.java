public class UnknownCommandException extends IndexOutOfBoundsException {
    UnknownCommandException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    }
}