package duke;

public class NoResponseException extends Exception {
    public NoResponseException() {
        super();
    }

    /**
     * Returns a String which indicates the input is not understood
     *
     * @return String
     */
    public String toString() {
        return "_____________________________________________\n"
                + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "_____________________________________________";
    }
}
