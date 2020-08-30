import java.time.LocalDate;

public class Parser {
    protected int location;
    protected String description;
    protected String date;
    protected LocalDate time;
    protected String type;
    protected TaskList tasks;
    protected Ui ui;
    protected boolean isFinished;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        ui = new Ui(tasks);
        isFinished = false;
    }

    public void parse(String[] command) throws DukeException {
        type = command[0];
        description = command[1];

        if(type.equals("list")) {
            ui.list();
        } else if(type.equals("bye")) {
            ui.showEnd();
            isFinished = true;
        } else {
            switch (type) {
                case "todo":
                    if (!(description.length() > 1)) {
                        throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        tasks.addTask(new Todo(description.substring(1)));
                        ui.printPart("Got it. I've added this task:\n"
                                + "  " + tasks.get(tasks.count() - 1).toString()
                                + "\nNow you have " + (tasks.count()) + " tasks in the list.");
                    }
                    break;

                case "deadline":
                    if (!(description.length() > 1)) {
                        throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        location = description.indexOf("/");
                        date = description.substring(location + 4);
                        time = LocalDate.parse(date);
                        description = description.substring(1, location - 1);
                        tasks.addTask(new Deadline(description, time));
                        ui.printPart("Got it. I've added this task:\n"
                                + "  " + tasks.get(tasks.count() - 1).toString()
                                + "\nNow you have " + (tasks.count()) + " tasks in the list.");
                    }
                    break;

                case "event":
                    if (!(description.length() > 1)) {
                        throw new DukeException("\u2639 OOPS!!! The description of a event cannot be empty.");
                    } else {
                        location = description.indexOf("/");
                        date = description.substring(location + 4);
                        time = LocalDate.parse(date);
                        description = description.substring(1, location - 1);
                        tasks.addTask(new Event(description, time));
                        ui.printPart("Got it. I've added this task:\n"
                                + "  " + tasks.get(tasks.count() - 1).toString()
                                + "\nNow you have " + (tasks.count()) + " tasks in the list.");
                    }
                    break;

                case "find":
                    if (!(description.length() > 1)) {
                        throw new DukeException("\u2639 OOPS!!! The key word cannot be empty.");
                    } else {
                        TaskList matchingTasks = tasks.findTasks(description.substring(1));
                        ui.find(matchingTasks);
                    }
                    break;

                case "done":
                    if (!(description.length() > 1)) {
                        throw new DukeException("\u2639 OOPS!!! The number to be marked done cannot be empty.");
                    } else {
                        int n = Integer.parseInt(description.substring(1));
                        tasks.get(n - 1).markAsDone();
                        ui.printPart("Nice! I've marked this task as done:\n" + "  " + tasks.get(n - 1).toString());
                    }
                    break;

                case "delete":
                    if (!(description.length() > 1)) {
                        throw new DukeException("\u2639 OOPS!!! The number to be deleted cannot be empty.");
                    } else {
                        int selected = Integer.parseInt(description.substring(1));
                        ui.printPart("Noted. I've removed this task:\n"
                                + "  " + tasks.get(selected - 1).toString()
                                + "\nNow you have " + (tasks.count() - 1) + " tasks in the list.");
                        tasks.deleteTask(selected - 1);
                    }
                    break;

                default:
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
