public class EmptyTaskDoneException extends IllegalArgumentException {

   public EmptyTaskDoneException() {
       super("OOPS! Task done cannot be empty!");
   }

}
