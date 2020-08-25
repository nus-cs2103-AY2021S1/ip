public class NoThisNumOfTaskException extends DukeException{
    NoThisNumOfTaskException() {
        super(String.format("  ☹ OOPS!!! The number you input is in a wrong format or it does not exist."));
    }
}
