public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) throws DukeEmptyDescException {
        super(name, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), by);
    }
}