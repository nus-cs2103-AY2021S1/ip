public class Event extends Task {
    private String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    public String getTaskIdentifier() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString()
                + " (at: "
                + this.time
                + ")";
    }

    public static void eventCommand(TaskList taskList, String[] args) throws  DukeException {
        String description = args[0].trim();
        if(args.length!=2 || description.equals(""))
            throw new DukeException(DukeException.Errors.EVENT_BAD_FORMAT);
        String time = args[1];
        taskList.add(new Event(description, time));
    }
}
