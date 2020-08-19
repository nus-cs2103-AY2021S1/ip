public class DukeUnknownArgumentException extends DukeException {

    DukeUnknownArgumentException(String message) {
        super(message);
    }
    
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
    
}
