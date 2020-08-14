public class TimeEmptyException extends Exception {
    TimeEmptyException(String type) {
        super(String .format(" ☹ OOPS!!! The time of a %s cannot be empty.",type));
    }
}
