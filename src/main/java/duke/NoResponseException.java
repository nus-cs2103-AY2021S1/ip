package duke;

public class NoResponseException extends Exception {
    public NoResponseException() {
        super();
    }
    public String toString() {
        return "____________________________________________________________\n" +
                " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________";
    }
}
