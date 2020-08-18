// Exception for wrong event command format
public class EventException extends DukeException{
    public EventException(){
        super("Please key in the event in the following format:\n" +
                "\'event task name /at date\'");
    }
    public String toString(){
        return "Error";
    }
}
