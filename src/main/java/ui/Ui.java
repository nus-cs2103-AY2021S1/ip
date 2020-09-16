package ui;

import storage.Storage;
import parser.Parser;
import exception.DukeException;
import exception.NumberOutOfRangeException;
import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;
import task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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
        int priority;
        toReturn += LINE;

        try {
            Parser parser = new Parser(input);
            parser.parse();
            commandType = parser.getCommandType();
            taskDetails = parser.getTaskDetails();
            date = parser.getDate();
            priority = parser.getPriority();

            if (commandType.equals("help")) {
                toReturn += help();

            } else if (commandType.equals("bye")) {
                toReturn += end();

            } else if (commandType.equals("list")) {
                toReturn += list();

            } else if (commandType.equals("todo")) {
                toReturn += todo(taskDetails, priority);

            } else if (commandType.equals("event")) {
                toReturn += event(taskDetails, date, priority);

            } else if (commandType.equals("deadline")) {
                toReturn += deadline(taskDetails, date, priority);

            } else if (commandType.equals("done")) {
                toReturn += done(Integer.parseInt(taskDetails));

            } else if (commandType.equals("delete")) {
                toReturn += delete(Integer.parseInt(taskDetails));

            } else if (commandType.equals("find")) {
                toReturn += find(taskDetails);

            } else {
                assert false;
            }

            storage.addData(list.getList());

        } catch (DukeException e) {
            toReturn += e.getMessage();
            toReturn += "\n";

        } catch (DateTimeParseException e) {
            toReturn += "Wrong date format. Please follow this format: YYYY-MM-DD.\n";
        }

        toReturn += LINE;
        return toReturn;
    }

    /**
     * Greeting of users to signify the start of the program.
     */
    public String greet() {
        String start = "Woof! I'm Nugget, your personal doggo.\nWhat can I do for you today?\n" +
                "FYI: You can type 'help' for the commands available.\n";
        String text = LINE + start + LINE;
        return text;
    }

    /**
     * Good bye to signify the end of the program.
     */
    public String end() {
        String text = "Goodbye! Let's play fetch again soon. :)\n";
        return text;
    }

    public String help() {
        String text = "Here are the list of commands you can type:\n" +
                "list - Shows the complete list of tasks you have\n" +
                "find <keyword> - Shows tasks in your task list that match the keyword\n" +
                "done <task number> - Marks the task as completed\n" +
                "delete <task number> - Deletes the task from your list\n" +
                "todo <task>- Adds a todo task\n" +
                "event <event details> /at <date in YYYY-MM-DD> - Adds an event\n" +
                "deadline <deadline details> /by <date in YYYY-MM-DD> -  Adds a deadline\n" +
                "\nOptional: Adding a '/p <priority level>' tag at the end of a task command\n" +
                "adds a priority level to your task! The default priority level is 0.\n";
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
    public String done(int taskNumber) throws NumberOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > list.getTotalTasks()) {
            throw new NumberOutOfRangeException();
        } else {
            list.getList().get(taskNumber - 1).completed();
            String header = "You've completed this task:\n";
            String text = header + (list.getList().get(taskNumber - 1)).toString() + "\n";
            return text;
        }
    }

    /**
     * Removes the task indicated from the list and prints out the task.
     *
     * @param taskNumber Task's number in the list.
     */
    public String delete(int taskNumber) throws NumberOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > list.getTotalTasks()) {
            throw new NumberOutOfRangeException();
        } else {
            String deleted = (list.getList().get(taskNumber - 1)).toString();
            list.remove(taskNumber - 1);
            String header = "Ok, this task has been kicked off your to-do list:\n";
            String text = header + deleted + "\n" + list.toString() + "\n";
            return text;
        }
    }

    /**
     * Creates a new todo task.
     *
     * @param task Task details.
     */
    public String todo(String task, int priority) throws NumberOutOfRangeException {
        if (priority < 0) {
            throw new NumberOutOfRangeException();
        } else {
            Todo todo = new Todo(task, false, priority);
            list.add(todo);
            String header = "I've added this task:\n";
            String text = header + todo.toString() + "\n" + list.toString() + "\n";
            return text;
        }
    }

    /**
     * Creates a new event.
     *
     * @param task Event details.
     * @param date Date of event.
     */
    public String event(String task, String date, int priority) throws DateTimeParseException, NumberOutOfRangeException {
        if (priority < 0) {
            throw new NumberOutOfRangeException();
        } else {
            Event event = new Event(task, date, false, priority);
            list.add(event);
            String header = "I've added this task:\n";
            String text = header + event.toString() + "\n" + list.toString() + "\n";
            return text;
        }
    }

    /**
     * Creates a new deadline.
     *
     * @param task Deadline details.
     * @param date Date of deadline.
     */
    public String deadline(String task, String date, int priority) throws DateTimeParseException, NumberOutOfRangeException {
        if (priority < 0) {
            throw new NumberOutOfRangeException();
        } else {
            Deadline deadline = new Deadline(task, date, false, priority);
            list.add(deadline);
            String header = "I've added this task:\n";
            String text = header + deadline.toString() + "\n" + list.toString() + "\n";
            return text;
        }
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
