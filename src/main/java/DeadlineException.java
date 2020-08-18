// Exception for wrong deadline format
public class DeadlineException extends DukeException{

    public DeadlineException (){
        super("Please key in the deadline in the following format:\n" +
                "\'deadline task name /by date\'");
    }

    public String toString(){
        return "Error";
    }
}
