public class DukeException extends Throwable {

    String s;

    DukeException(String s) {
        this.s = s;
    }

    public String getMessage() {
        if (this.s.equals("")) {
            return "Please enter your command.";
        } else if (this.s.equals("todo")) {
            return "Please enter the description of your todo with the command.";
        } else if (this.s.equals("deadline")) {
            return "Please enter the description and timing of the deadline with the command.";
        } else if (this.s.equals("event")) {
            return "Please enter the description and timing of the event with the command.";
        } else {
            return "Sorry I do not understand that command.";
        }
    }
}
