import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class that handles user input
 */
public class Parser {
    TaskList tasks;

    /**
     * @param tasks TaskList to use.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user command.
     *
     * @param input input from the user.
     * @return
     */
    String parse(String input) {
        try {
            assert !input.isEmpty() : "error: input is null";
            String[] words = input.split(" ");
            String command = words[0];
            Task newTask;
            String[] subst;
            LocalDate date;
            switch (command) {
            case "list":
                tasks.print_tasks();
                break;
            case "done":
            case "delete":
                int i = Integer.parseInt(words[1]) - 1;
                Task task = tasks.get(i);
                if (command.equals("done")) {
                    tasks.setDone(i, true);
                    UI.print("Nice! I've marked this task as done: \n" + task);
                } else if (command.equals("delete")) {
                    tasks.remove(i);
                    UI.print("Noted. I've removed this task: \n" + task + tasks.numTasks());
                }
                break;
            case "todo":
                if (input.length() < 6) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                String text = input.substring(5);
                newTask = new Todo(text);
                tasks.addTask(newTask);
                break;
            case "deadline":
                if (input.length() < 10) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                subst = input.substring(9).split(" /by ");
                if (subst.length < 2) {
                    throw new DukeException("The due date must be specified.");
                }
                try {
                    date = LocalDate.parse(subst[1]);
                    newTask = new Deadline(subst[0], date);
                    tasks.addTask(newTask);
                } catch (DateTimeParseException e) {
                    throw new DukeException(e.getMessage());
                }
                break;
            case "event":
                if (input.length() < 7) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                subst = input.substring(6).split(" /at ");
                if (subst.length < 2) {
                    throw new DukeException("The event date must be specified.");
                }
                try {
                    date = LocalDate.parse(subst[1]);
                    newTask = new Event(subst[0], date);
                    tasks.addTask(newTask);
                } catch (DateTimeParseException e) {
                    throw new DukeException(e.getMessage());
                }
                break;
            case "find":
                if (input.length() < 6) {
                    throw new DukeException("Text to search for cannot be empty.");
                }
                tasks.find(input.substring(5));
                break;
            case "sort":
                tasks.sort();
                break;
            case "bye":
                System.exit(0);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            UI.print(e.getMessage() + "\n");
        }
        return UI.getResponse();
    }

    public String welcome() {
        UI.welcome(tasks);
        return UI.getResponse();
    }
}