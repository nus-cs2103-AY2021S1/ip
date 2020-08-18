public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getStatusIcon() {
        return String.format("[T]%s ", super.getStatusIcon());
    }
}
