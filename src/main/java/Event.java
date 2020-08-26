public class Event extends Task {

    protected String at;

    public Event(String name, String at) throws DukeEmptyDescException {
        super(name, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), at);
    }

}