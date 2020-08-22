package duke.exceptions;

public class EmptyTaskDoneException extends IllegalArgumentException {

   public EmptyTaskDoneException() {
       super("OOPS! duke.tasks.Task done cannot be empty!");
   }

}
