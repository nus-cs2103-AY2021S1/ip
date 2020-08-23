package exceptions;

public class EmptyTodoException extends DukeException {
    public EmptyTodoException(){
        super("A todo cannot be empty");
    }
}
