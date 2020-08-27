public class IllegalTaskTypeException extends Exception {
    IllegalTaskTypeException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Cannot detect task type";
    }
}

