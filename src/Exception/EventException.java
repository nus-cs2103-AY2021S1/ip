package Exception;

public class EventException extends DukeException {
    @Override
    public String toString(){
        String s = "OOPS!!! The description of a event cannot be empty.\n";
        return s;
    }
}
