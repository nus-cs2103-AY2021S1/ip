/**
 * Represents an Illegal Task Type Exception. An <code>Illegal Task Type Exception
 * Exception</code> object is used when invalid task type is detected
 */
public class IllegalTaskTypeException extends Exception {
    IllegalTaskTypeException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Cannot detect task type";
    }
}

