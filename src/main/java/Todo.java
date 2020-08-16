public class Todo extends Task{

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String getCurrentStatus() throws DukeException{
        if (getDescription().equals("")) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        return super.getCurrentStatus();
    }
}
