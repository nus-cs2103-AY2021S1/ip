package Exception;

public class TaskException extends DukeException {
    @Override
    public String toString(){
        String s = "OOPS!!! The description of a todo cannot be empty.\n";
        return s;
    }
}
