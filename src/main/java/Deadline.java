public class Deadline extends Task {
    private String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }
    public String getTaskIdentifier() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + this.time
                + ")";
    }

    public static void deadlineCommand(TaskList taskList, String[] args) throws  DukeException {
        String description = args[0].trim();
        if(args.length!=2 || description.equals(""))
            throw new DukeException(DukeException.Errors.DEADLINE_BAD_FORMAT);
        String time = args[1];
        taskList.add(new Deadline(description, time));
    }
}
