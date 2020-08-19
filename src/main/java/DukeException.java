public class DukeException extends Exception {

    public DukeException(String error){
        super(error);
    }

    @Override
    public String getMessage() {
        String breakline = "\n    ___________________________________________________________________";
        return breakline + "\n" + super.getMessage() + breakline;
    }
}
