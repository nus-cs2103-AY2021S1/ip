package exceptions;

public class InvalidEventException extends DukeException {
    public InvalidEventException(){
        super("Oops you have not entered a valid event, maybe check you /at tag again");
    }
}
