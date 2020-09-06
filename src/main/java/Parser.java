import java.time.LocalDate;

/**
 * deals with making sense of the user command.
 */
public class Parser {
    protected int location;
    protected String description;
    protected String date;
    protected LocalDate time;
    protected String type;
    protected TaskList tasks;
    protected Ui ui;
    protected boolean isFinished;

    /**
     * creates a new parser and initialized the state to false.
     * @param tasks the task list that will be implemented
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        ui = new Ui(tasks);
        isFinished = false;
    }

    public String parse(String input) throws DukeException {
        if(input.equals("list")) {
            return ui.list();
        } else if(input.equals("bye")) {
            isFinished = true;
            return ui.showEnd();
        } else if(input.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if(input.equals("deadline")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if(input.equals("event")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if(input.startsWith("todo ")) {
            description = input.substring(5);
            tasks.addTask(new Todo(description));
            return "Got it. I've added this task:\n"
                    + "  " + tasks.get(tasks.count() - 1).toString()
                    + "\nNow you have " + (tasks.count()) + " tasks in the list.";
        } else if(input.startsWith("deadline ")) {
            description = input.substring(9);
            location = description.indexOf("/");
            date = description.substring(location + 4);
            time = LocalDate.parse(date);
            description = description.substring(0, location - 1);
            tasks.addTask(new Deadline(description, time));
            return "Got it. I've added this task:\n"
                    + "  " + tasks.get(tasks.count() - 1).toString()
                    + "\nNow you have " + (tasks.count()) + " tasks in the list.";
        } else if(input.startsWith("event ")) {
            description = input.substring(6);
            location = description.indexOf("/");
            date = description.substring(location + 4);
            time = LocalDate.parse(date);
            description = description.substring(0, location - 1);
            tasks.addTask(new Event(description, time));
            return "Got it. I've added this task:\n"
                    + "  " + tasks.get(tasks.count() - 1).toString()
                    + "\nNow you have " + (tasks.count()) + " tasks in the list.";
        } else if(input.startsWith("done ")) {
            description = input.substring(5);
            int selected = Integer.parseInt(description);
            tasks.get(selected - 1).markAsDone();
            return "Nice! I've marked this task as done:\n" + "  "
                    + tasks.get(selected - 1).toString();
        } else if(input.startsWith("delete ")) {
            description = input.substring(7);
            int selected = Integer.parseInt(description);
            Task task = tasks.get(selected - 1);
            tasks.deleteTask(selected - 1);
            return "Noted. I've removed this task:\n"
                    + "  " + task.toString()
                    + "\nNow you have " + (tasks.count()) + " tasks in the list.";
        } else if(input.startsWith("find ")) {
            description = input.substring(5);
            TaskList matchingTasks = tasks.findTasks(description);
            return ui.find(matchingTasks);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
