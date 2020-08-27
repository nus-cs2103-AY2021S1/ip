public class DukeException extends Exception {
    /**
     * Prints out the correct error message based on error type.
     * 
     * @param errorType Description of error.
     */
    public DukeException(String errorType) {
        String errorMessage = errorType.equals("invalidCommand") 
                ? "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                : errorType.equals("invalidMarkingDone") 
                ? "☹ OOPS!!! Command to mark a task done is formatted wrongly." 
                : errorType.equals("invalidTodo")
                ? "☹ OOPS!!! The description of a todo cannot be empty."
                : errorType.equals("invalidDeadlineTask")
                ? "☹ OOPS!!! The deadline task is formatted wrongly."
                : errorType.equals("invalidDeadlineDateTime")
                ? "☹ OOPS!!! The deadline datetime should be in a valid 'YYYY-MM-DD HH:MM' format."
                : errorType.equals("invalidEventChronology")
                ? "☹ OOPS!!! The event end time should be after start time."
                : errorType.equals("invalidEvent")
                ? "☹ OOPS!!! The event task is formatted wrongly."
                : errorType.equals("invalidEventDateTime")
                ? "☹ OOPS!!! The event datetime should be in a valid 'YYYY-MM-DD HH:MM to YYYY-MM-DD HH:MM' format."
                : errorType.equals("invalidDelete")
                ? "☹ OOPS!!! Command to delete a task is formatted wrongly."
                : errorType.equals("invalidFind")
                ? "☹ OOPS!!! Command to find a task is formatted wrongly."
                : "Unknown error. Try something else.";
        System.out.println(errorMessage);
    }
}
