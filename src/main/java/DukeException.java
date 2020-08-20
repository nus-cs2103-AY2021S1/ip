public class DukeException extends Exception{
    DukeException(String msg){
        super(msg);
    }

    public String toString() {
        return this.getMessage();
    }
}
