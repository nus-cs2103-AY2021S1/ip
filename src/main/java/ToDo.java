public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public ToDo markAsDone() {
        ToDo doneToDo = new ToDo(this.description, true);
        System.out.println(" ____________________________________________________________\n " +
                "Nice! I've marked this task as done:\n    " +
                doneToDo.toString() +
                "\n ____________________________________________________________");
        return doneToDo;
    }

    @Override
    public String toTxtFileFormat() {
        return "T" + super.toTxtFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
