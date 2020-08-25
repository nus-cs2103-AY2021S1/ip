public class EmptyTodoException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final static String errorMessage = "OOPS!!! The description of a todo cannot be empty.";

    public EmptyTodoException() {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }

}