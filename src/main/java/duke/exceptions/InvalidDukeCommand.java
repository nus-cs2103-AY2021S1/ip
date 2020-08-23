package duke.exceptions;

public class InvalidDukeCommand extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " I'm sorry, but I don't know what that means :-(";
    }
}
