package exception;

public class DukeException extends Exception {
    String line = "___________________________________________________________________________________";
    String smallSpace = "    ";
    String bigSpace = "     ";

    public DukeException(String message) {
        super(message);
    }

    public String toString() {
        return smallSpace + line + "\n" + bigSpace + "☹ OOPS!!! " +
                this.getMessage() + "\n" + smallSpace + line + "\n";
    }
}
