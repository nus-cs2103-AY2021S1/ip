public class Deadline extends TimeTask {
    public Deadline(String description, String time) throws DukeException {
        super(description, time);
    }
    @Override
    public String getTaskIdentifier() {
        return "D";
    }
    @Override
    public String getDateDescriptor() {
        return "by";
    }

    public static void deadlineCommand(TaskList taskList, String[] args) throws  DukeException {
        String description = args[0].trim();
        if(args.length!=2 || description.equals(""))
            throw DukeException.Errors.DEADLINE_BAD_FORMAT.create();
        String time = args[1];
        taskList.add(new Deadline(description, time));
    }
}
