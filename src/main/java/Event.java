public class Event extends Task {

    protected String at;

    public Event(String name, String at, boolean done) throws DukeEmptyDescException {
        super(name, TaskType.EVENT, done);
        this.at = at;
    }

    public static void newEvent(String inputSuffix, TaskList taskList, boolean done, boolean announce)
            throws DukeException {
        String[] eventParts = inputSuffix.split("/at",2);
        String eventName = eventParts[0];
        if (eventParts.length == 1) {
            throw new DukeEmptyDescException(TaskType.EVENT);
        } else {
            String at = eventParts[1];
            if (Ui.isBlankString(at)) {
                throw new DukeEmptyDescException(TaskType.EVENT);
            } else {
                taskList.addTask(new Event(eventName, at, done), announce);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), at);
    }

    public String toData() {
        return String.format("[E]%s/at%s", super.toString(), at);
    }

}