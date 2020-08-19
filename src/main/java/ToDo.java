public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}
