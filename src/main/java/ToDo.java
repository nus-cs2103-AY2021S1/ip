public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
