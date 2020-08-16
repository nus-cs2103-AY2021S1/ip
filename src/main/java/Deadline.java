public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
        this.type = "D";
    }
    @Override
    public String getCurrentStatus() throws DukeException{
        if (getDescription() == "") throw new DukeException("☹ OOPS!!! The description of a Deadline cannot be empty.");
        return super.getCurrentStatus();
    }
}
