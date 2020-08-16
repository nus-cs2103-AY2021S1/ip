public class Event extends Task{
    public Event(String description) {
        super(description);
        this.type = "E";
    }
    @Override
    public String getCurrentStatus() throws DukeException{
        if (getDescription() == "") throw new DukeException("☹ OOPS!!! The description of a Event cannot be empty.");
        return super.getCurrentStatus();
    }
}
