public class DukeException extends Exception {
    public DukeException(String s){
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n Please restart!";
    }
}
