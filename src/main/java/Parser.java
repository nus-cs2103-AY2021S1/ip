import java.time.LocalDate;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    protected int location;
    protected String description;
    protected String date;
    protected LocalDate time;
    protected TaskList tasks;
    protected Ui ui;
    protected boolean isFinished;

    /**
     * Creates a new parser and initialized the state to false.
     * @param tasks the task list that will be implemented
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        ui = new Ui(tasks);
        isFinished = false;
    }

    /**
     * Makes sense of the user input.
     * @param input the user command
     * @return Duke's response to the user command
     * @throws DukeException exceptions when processing the command.
     */
    public String parse(String input) throws DukeException {
        if(input.equals("list")) {
            return ui.list();
        } else if(input.equals("bye")) {
            isFinished = true;
            return Ui.showEnd();
        } else if (input.equals("help")) {
            return Ui.printHelpInformation();
        } else if(input.equals("todo") || input.equals("deadline") || input.equals("event")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");

        } else if(input.startsWith("todo ")) {
            return handleAddCommand("todo ", input);

        } else if(input.startsWith("deadline ")) {
            return handleAddCommand("deadline ", input);

        } else if(input.startsWith("event ")) {
            return handleAddCommand("event ", input);

        } else if(input.startsWith("done ")) {
            return handleDoneCommand(input);

        } else if(input.startsWith("delete ")) {
            return handleDeleteCommand(input);

        } else if(input.startsWith("find ")) {
            return handleFindCommand(input);

        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String handleAddCommand(String type, String input) {
        description = input.substring(type.length());
        if(type.equals("todo ")) {
            tasks.addTask(new Todo(description));

        } else if(type.equals("deadline ")) {
            location = description.indexOf("/");
            date = description.substring(location + 4);
            time = LocalDate.parse(date);
            description = description.substring(0, location - 1);
            tasks.addTask(new Deadline(description, time));

        } else {
            location = description.indexOf("/");
            date = description.substring(location + 4);
            time = LocalDate.parse(date);
            description = description.substring(0, location - 1);
            tasks.addTask(new Event(description, time));
        }
        return "Got it. I've added this task:\n"
                + "  " + tasks.get(tasks.count() - 1).toString()
                + "\nNow you have " + (tasks.count()) + " tasks in the list.";
    }

    private String handleDoneCommand(String input) {
        description = input.substring(5);
        int selected = Integer.parseInt(description);
        tasks.get(selected - 1).markAsDone();
        return "Nice! I've marked this task as done:\n" + "  "
                + tasks.get(selected - 1).toString();
    }

    private  String handleDeleteCommand(String input) {
        description = input.substring(7);
        int selected = Integer.parseInt(description);
        Task task = tasks.get(selected - 1);
        tasks.deleteTask(selected - 1);
        return "Noted. I've removed this task:\n"
                + "  " + task.toString()
                + "\nNow you have " + (tasks.count()) + " tasks in the list.";
    }

    private String handleFindCommand(String input) {
        description = input.substring(5);
        TaskList matchingTasks = tasks.findTasks(description);
        return ui.find(matchingTasks);
    }

    public boolean isFinished() {
        return isFinished;
    }
}
