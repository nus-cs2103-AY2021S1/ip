package duke.exceptions;

public class NoSuchTaskException extends DukeException {

   public NoSuchTaskException() {
       super("OOPS! No such task exists!");
   }
}
