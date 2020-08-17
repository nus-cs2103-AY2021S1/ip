public class NoSuchTaskException extends IllegalArgumentException {

   public NoSuchTaskException() {
       super("OOPS! No such task exists!");
   }
}
