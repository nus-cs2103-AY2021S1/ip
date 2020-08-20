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
        } else if (this.s.equals("list")) {
            return "The list is currently empty, add items with the todo, deadline or event command.";
        } else if (this.s.equals("delete")) {
            return "Please enter the number position of the item that you wish to delete.";
        } else if (this.s.equals("delete2")) {
            return "That item has already been deleted or it does not exist.";
        } else if (this.s.equals("done")) {
            return "Please enter the number position of the item that you wish to mark done.";
        }
        else {
            return "Sorry I do not understand that command.";
        }
    }
}
