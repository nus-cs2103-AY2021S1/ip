public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by, boolean done) throws DukeException {
        super(name, TaskType.DEADLINE, done);
        this.by = by;
    }

    public static void newDeadline(String inputSuffix, TaskList taskList, boolean done, boolean announce)
            throws DukeException{
        String[] deadlineParts = inputSuffix.split("/by",2);
        String deadlineName = deadlineParts[0];
        if (deadlineParts.length == 1) {
            throw new DukeEmptyDescException(TaskType.EVENT);
        } else {
            String by = deadlineParts[1];
            if (Ui.isBlankString(by)) {
                throw new DukeEmptyDescException(TaskType.EVENT);
            } else {
                taskList.addTask(new Deadline(deadlineName, by, done), announce);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), by);
    }

    public String toData() {
        return String.format("[D]%s/by%s)", super.toString(), by);
    }

}