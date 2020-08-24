public class DukeException extends Exception {
    private String msg;

//    public DukeException(String msg, Throwable cause) {
//        super(msg, cause);
//    }
//
    public DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        //super(cause);
        if (msg.equals("file not found")) {
            return "no databse found! pls try again ^__^";
        } else {
            return "☹ OOPS!!! The description of a " + msg + " cannot be empty.";
        }
    }
}
