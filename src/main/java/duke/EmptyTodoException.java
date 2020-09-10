package duke;

public class EmptyTodoException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MESSAGE = "OOPS!!! The description of a todo cannot be empty.";

    public EmptyTodoException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }

}
