public class AddEventCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public AddEventCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 6 || !input.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] splitStr = input.split("/at ");
        String description = splitStr[0].substring(6).trim();
        Event event = new Event(description, splitStr[1]);
        tasks.addTask(event);
//        total++;
        storage.writeNewDataToFile("E", "0", event.getDescription(), event.getTime());
        System.out.println("    Got it. I've added this task:\n      " + event + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
