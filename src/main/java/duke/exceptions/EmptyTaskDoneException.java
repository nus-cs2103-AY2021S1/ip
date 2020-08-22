package duke.exceptions;

public class EmptyTaskDoneException extends DukeException {

   public EmptyTaskDoneException() {
       super("OOPS! Task done cannot be empty!");
   }

}
