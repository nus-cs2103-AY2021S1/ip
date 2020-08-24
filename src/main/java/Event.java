public class Event extends TimeTask {
    public Event(String description, String time) throws DukeException {
        super(description, time);
    }
    @Override
    public String getTaskIdentifier() {
        return "E";
    }
    @Override
    public String getDateDescriptor() {
        return "at";
    }

    public static void eventCommand(TaskList taskList, String[] args) throws  DukeException {
        String description = args[0].trim();
        if(args.length!=2 || description.equals(""))
            throw new DukeException(DukeException.Errors.EVENT_BAD_FORMAT);
        String time = args[1];
        taskList.add(new Event(description, time));
    }
}
