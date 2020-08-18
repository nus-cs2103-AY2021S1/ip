public class ToDo extends Task{

    protected String dateAndOrTime;

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
