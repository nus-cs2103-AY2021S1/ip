public class Todo extends Task {

    public String type;

    public Todo(String desc, boolean isDone) {
        super(desc, isDone, null);
        type = "T";
    }

    public static Todo makeToDo(String desc, boolean isDone) throws DukeException {
        if (desc.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(desc, isDone);
    }

    @Override
    public String getType() {
        return this.type;
    }

}
