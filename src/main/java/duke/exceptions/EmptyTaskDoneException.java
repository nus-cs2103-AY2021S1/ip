package duke.exceptions;

/** Thrown to indicate that the user input done without the task. */
public class EmptyTaskDoneException extends DukeException {

    /** Constructs the EmptyTaskDoneException with the relevant detail message. */
   public EmptyTaskDoneException() {
       super("OOPS! Task done cannot be empty!");
   }

}
