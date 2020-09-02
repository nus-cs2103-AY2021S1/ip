import java.io.IOException;

/**
 * <h1>User Interface class</h1>
 * Deals with interaction with the users.
 */
public class Ui {
    private static final String LINE = "______________________________________\n";
    private TaskList list;
    private Storage storage;

    /**
     * Creates a Ui object.
     *
     * @param taskList
     * @param storage
     */
    public Ui(TaskList taskList, Storage storage) {
        this.list = taskList;
        this.storage = storage;
    }

    /**
     * Users will be greeted by Duke at the start.
     * A parser will be used to determine the command type based on the input.
     * Based on the command type, its respective method will be called to
     * carry out the command.
     * The updated list of tasks will be stored upon every command.
     * Duke will end when users type "bye".
     *
     * @throws IOException
     */
    public String runProgram(String input) throws IOException {
        String commandType = "";
        String taskDetails = "";
        String date = "";
        String toReturn = "";
        toReturn += LINE;

        try {
            Parser parser = new Parser(input);
            parser.parse();
            commandType = parser.getCommandType();
            taskDetails = parser.getTaskDetails();
            date = parser.getDate();

            if (commandType.equals("bye")) {
                toReturn += end();

            } else if (commandType.equals("list")) {
                toReturn += list();

            } else if (commandType.equals("todo")) {
                toReturn += todo(taskDetails);

            } else if (commandType.equals("event")) {
                toReturn += event(taskDetails, date);

            } else if (commandType.equals("deadline")) {
                toReturn += deadline(taskDetails, date);

            } else if (commandType.equals("done")) {
                toReturn += done(Integer.parseInt(taskDetails));

            } else if (commandType.equals("delete")) {
                toReturn += delete(Integer.parseInt(taskDetails));

            } else if (commandType.equals("find")) {
                toReturn += find(taskDetails);

            } else {

            }

            storage.addData(list.getList());

        } catch (DukeException e) {
            toReturn += e.getMessage();
            toReturn += "\n";
        }

        toReturn += LINE;
        return toReturn;
    }

    /**
     * Greeting of users to signify the start of the program.
     */
    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String start = "Hello! I'm Duke, your personal assistant. \nWhat can I do for you?";
        String text = LINE + logo + start + LINE;
        return text;
    }

    /**
     * Good bye to signify the end of the program.
     */
    public String end() {
        String text = "Goodbye! Hope to see you again soon. :)\n";
        return text;
    }

    /**
     * Prints out the current list of tasks.
     */
    public String list() {
        if (list.isEmpty()) {
            return "You have no tasks, go out and have fun!\n";
        } else {
            String header = "Here is your to-do list:\n";
            String text = header;
            for (int i = 1; i <= list.getTotalTasks(); i++) {
                text += i + ". " + list.get(i - 1).toString() + "\n";
            }
            return text;
        }
    }

    /**
     * Marks the task indicated as completed and prints out the task.
     * that was completed.
     *
     * @param taskNumber Task's number in the list.
     */
    public String done(int taskNumber) {
        list.getList().get(taskNumber - 1).completed();
        String header = "You've completed this task:\n";
        String text = header + (list.getList().get(taskNumber - 1)).toString() + "\n";
        return text;
    }

    /**
     * Removes the task indicated from the list and prints out the task.
     *
     * @param taskNumber Task's number in the list.
     */
    public String delete(int taskNumber) {
        String deleted = (list.getList().get(taskNumber - 1)).toString();
        list.remove(taskNumber - 1);
        String header = "Ok, this task has been kicked off your to-do list:\n";
        String text = header + deleted + "\n" + list.toString() + "\n";
        return text;
    }

    /**
     * Creates a new todo task.
     *
     * @param task Task details.
     */
    public String todo(String task) {
        Todo todo = new Todo(task, false);
        list.add(todo);
        String header = "I've added this task:\n";
        String text = header + todo.toString() + "\n" + list.toString() + "\n";
        return text;
    }

    /**
     * Creates a new event.
     *
     * @param task Event details.
     * @param date Date of event.
     */
    public String event(String task, String date) {
        Event event = new Event(task, date, false);
        list.add(event);
        String header = "I've added this task:\n";
        String text = header + event.toString() + "\n" + list.toString() + "\n";
        return text;
    }

    /**
     * Creates a new deadline.
     *
     * @param task Deadline details.
     * @param date Date of deadline.
     */
    public String deadline(String task, String date) {
        Deadline deadline = new Deadline(task, date, false);
        list.add(deadline);
        String header = "I've added this task:\n";
        String text = header + deadline.toString() + "\n" + list.toString() + "\n";
        return text;
    }

    /**
     * Looks for tasks in the list that contains the keyword
     *
     * @param keyword Word that user wants to look out for in list of tasks.
     */
    public String find(String keyword) {
        int counter = 1;
        String header = "Here are the matching tasks in your list:\n";
        String text = header;
        for (Task t : list.getList()) {
            if (t.getTask().contains(keyword)) {
                String toAdd = counter + ". " + t.toString();
                text += toAdd + "\n";
                counter++;
            }
        }
        return text;
    }

}
