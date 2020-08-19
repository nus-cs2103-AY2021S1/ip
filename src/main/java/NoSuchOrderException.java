public class NoSuchOrderException extends Exception{
    NoSuchOrderException() {
        super(String.format("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }
}
