public class DukeException extends Exception {
    public DukeException(String errorType) {
        String errorMessage = errorType.equals("invalidCommand") 
                ? "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                : errorType.equals("invalidMarkingDone") 
                ? "☹ OOPS!!! Command to mark a task done is formatted wrongly." 
                : errorType.equals("invalidTodo")
                ? "☹ OOPS!!! The description of a todo cannot be empty."
                : errorType.equals("invalidDeadline")
                ? "☹ OOPS!!! The deadline is formatted wrongly."
                : errorType.equals("invalidEvent")
                ? "☹ OOPS!!! The event is formatted wrongly."
                : errorType.equals("invalidDelete")
                ? "☹ OOPS!!! Command to delete a task done is formatted wrongly."
                : "Unknown error. Try something else.";
        System.out.println(errorMessage);
    }
}
