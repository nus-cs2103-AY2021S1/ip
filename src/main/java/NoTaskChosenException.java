public class NoTaskChosenException extends Exception{
    NoTaskChosenException(String task) {
        super(String.format("  ☹ OOPS!!! The task of a %s operation cannot be empty.", task));
    }
}
