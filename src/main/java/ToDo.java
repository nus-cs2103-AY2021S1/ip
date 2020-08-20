public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
        this.setType("todo");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
