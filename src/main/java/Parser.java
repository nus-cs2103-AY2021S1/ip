import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Parser object.
 * Handles the user input commands and executes them.
 */
public class Parser {
    private static boolean isProgramRunning = true;
    private static TaskList tasks;

    /**
     * Creates a Parser object.
     * It is used for identifying String inputs from user.
     *
     * @param tasks TaskList object, required to interact with it.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a boolean value for others to know if the Parser object is still running.
     *
     * @return boolean value of isRunning.
     */
    public boolean isRunning() {
        return this.isProgramRunning;
    }

    /**
     * Communicates with TaskList class and passes current Task object to TaskList class for storing.
     *
     * @param task to pass over Task object for storage.
     */
    public void store(Task task) {
        this.tasks.store(task);
        System.out.println("     Got it. I've added this task:\n"
                + "       " + task);
        System.out.println("     Now you have " + this.tasks.getLength() + " task(s) in the list.");
    }

    /**
     * Communicates with TaskList class and gets current Task list to print for user to see.
     */
    public void displayList() {
        List<Task> currLst = this.tasks.getTasks();
        int size = currLst.size();
        System.out.println("     Here are the task(s) in your list:");

        int index = 1;
        for (int i = 0; i < size; i++) {
            System.out.println("     " + index + "." + currLst.get(i));
            index++;
        }
    }

    /**
     * Communicates with TaskList class and passes current Task object to TaskList class for marking the Task as done.
     *
     * @param index of the particular Task in Task list.
     */
    public void markDone(int index) {
        Task taskSubject = this.tasks.markDone(index);
        System.out.println("     Nice! I've marked this task as done:\n"
                + "       " + taskSubject);
    }

    /**
     * Communicates with TaskList class and passes current Task object to TaskList class for removal.
     *
     * @param index of the particular Task in Task list.
     */
    public void delete(int index) {
        Task taskSubject = this.tasks.remove(index);
        System.out.println("     Noted. I've removed this task:\n"
                + "       " + taskSubject
                + "\n     Now you have " + this.tasks.getLength() + " task(s) in the list.");
    }

    /**
     * A command to close the Parser object for it to stop running.
     * Sets isProgramRunning to false.
     */
    public void close() {
        this.isProgramRunning = false;
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Communicates with TaskList class and to get the current stored Task list.
     */
    public List<Task> getTasks() {
        return tasks.getTasks();
    }

    /**
     * Communicates with TaskList class and to get the current stored Task list.
     * Filters the tasks based on the keyword searched.
     *
     * @param keyword String will be searched within all tasks.
     * If matched, it will be printed.
     */
    public void findTask(String keyword) {
        List<Task> allTasks = tasks.getTasks();
        int fullSize = tasks.getLength();
        List<Task> filteredTasks = new ArrayList<>();

        for (int i = 0; i < fullSize; i++) {
            Task task = allTasks.get(i);
            String taskString = task.toString();
            if (taskString.contains(keyword)) {
                filteredTasks.add(task);
            }
        }

        System.out.println("     Here are the matching task(s) in your list:");
        int partialSize = filteredTasks.size();
        int index = 1;

        for (int i = 0; i < partialSize; i++) {
            System.out.println("     " + index + "." + filteredTasks.get(i));
            index++;
        }
    }

    /**
     * Processes user input for classification and execution.
     *
     * @param str is the line of String obtained from user input when user is using the JavaFX GUI.
     *
     * Reads and makes sense of user input commands which includes:
     * (bye, list, todo, deadline, event, delete)
     *
     * It changes the output to a String as Duke Response.
     *
     * @return a String representation of Duke output based on user commands.
     *
     * @throws DukeException when command is wrong, unidentifiable or missing.
     */
    public String parseUserInput(String str) throws DukeException {
        String trimmedStr = str.trim();
        int commandSpace = str.indexOf(" ");

        if (trimmedStr.length() < 3) {
            throw new DukeException("*Invalid command.*"
                    + "\n  Duke Commands: bye, list, find, done, delete, todo, "
                    + "\n                 event, deadline");
        }



        if (str.equals("bye")) {
            MainWindow.closeWindow();
            return "See you again!";

        } else if (str.equals("list")) {
            List<Task> currLst = this.tasks.getTasks();
            int size = currLst.size();
            String output = "Here are the task(s) in your list:\n";

            int index = 1;
            for (int i = 0; i < size; i++) {
                output = output + "  " + index + "." + currLst.get(i) + "\n";
                index++;
            }

            return output;

        } else if (commandSpace <= 0) {
            throw new DukeException("*Invalid command.*"
                    + "\n  Duke Commands: bye, list, find, done, delete, todo, "
                    + "\n                 event, deadline");
        }



        if (str.substring(0, 5).equals("done ")) {
            int length = str.length();
            String index = str.substring(5, length);
            int realIndex = Integer.parseInt(index) - 1;

            if (realIndex >= this.tasks.getLength() || realIndex < 0) {
                throw new DukeException("*Invalid task index, please try again.*");
            }

            assert this.tasks.getLength() >= realIndex + 1 : "There should be a task in task list to mark done";

            Task taskSubject = this.tasks.markDone(realIndex);
            return "Nice! I've marked this task as done:\n"
                    + "  " + taskSubject;

        } else if (str.substring(0, 5).equals("todo ")) {
            int length = str.length();
            if (length == 5) {
                throw new DukeException("*Please fill in todo description*");
            }



            Todo newTodo = new Todo(str.substring(5, length));
            this.tasks.store(newTodo);
            return "Got it. I've added this task:\n"
                    + "  " + newTodo + "\n"
                    + "Now you have " + this.tasks.getLength() + " task(s) in the list.";

        } else if (str.substring(0, 5).equals("find ")) {
            int length = str.length();
            if (length == 5) {
                throw new DukeException("*Please fill in a keyword to find*");
            }



            String keyword = str.substring(5, length);
            List<Task> allTasks = tasks.getTasks();
            int fullSize = tasks.getLength();
            List<Task> filteredTasks = new ArrayList<>();

            for (int i = 0; i < fullSize; i++) {
                Task task = allTasks.get(i);
                String taskString = task.toString();
                if (taskString.contains(keyword)) {
                    filteredTasks.add(task);
                }
            }

            String output = "Here are the matching task(s) in your list:\n";
            int partialSize = filteredTasks.size();
            int index = 1;
            for (int i = 0; i < partialSize; i++) {
                output = output + "  " + index + "." + filteredTasks.get(i) + "\n";
                index++;
            }
            return output;

        } else if (commandSpace <= 4) {
            throw new DukeException("*Invalid command.*"
                    + "\n  Duke Commands: bye, list, find, done, delete, todo, "
                    + "\n                 event, deadline");
        }



        if (str.substring(0, 6).equals("event ")) {
            int length = str.trim().length();
            if (length == 5) {
                throw new DukeException("*Please fill in event description*");
            }

            int timePrefix = str.indexOf("/at");
            if (timePrefix < 0) {
                throw new DukeException("*Please fill in event completion time in the following format:*\n"
                        + "  eg. event CCA meeting /at YYYY-MM-DD");
            }

            LocalDate date;
            String dateString = str.substring(timePrefix + 4, length);
            try {
                date = LocalDate.parse(dateString);
            } catch (DateTimeParseException e) {
                throw new DukeException("*Please fill in the time in the YYYY-MM-DD format*");
            }

            Event newEvent = new Event(str.substring(6, timePrefix), date);
            this.tasks.store(newEvent);
            return "Got it. I've added this task:\n"
                    + "  " + newEvent + "\n"
                    + "Now you have " + this.tasks.getLength() + " task(s) in the list.";

        } else if (commandSpace <= 5) {
            throw new DukeException("*Invalid command.*"
                    + "\n  Duke Commands: bye, list, find, done, delete, todo, "
                    + "\n                 event, deadline");
        }



        if (str.substring(0, 7).equals("delete ")) {
            int length = str.length();
            String index = str.substring(7, length);
            int realIndex = Integer.parseInt(index) - 1;

            if (realIndex >= this.tasks.getLength() || realIndex < 0) {
                throw new DukeException("*Invalid task index, please try again.*");
            }



            Task taskSubject = this.tasks.remove(realIndex);
            return "Noted. I've removed this task:\n"
                    + "  " + taskSubject + "\n"
                    + "Now you have " + this.tasks.getLength() + " task(s) in the list.";

        } else if (commandSpace <= 6) {
            throw new DukeException("*Invalid command.*"
                    + "\n  Duke Commands: bye, list, find, done, delete, todo, "
                    + "\n                 event, deadline");
        }



        if (str.substring(0, 9).equals("deadline ")) {
            int length = str.trim().length();
            if (length == 9) {
                throw new DukeException("*Please fill in deadline description*");
            }

            int timePrefix = str.indexOf("/by");
            if (timePrefix < 0) {
                throw new DukeException("*Please fill in deadline completion time in the following format:*\n"
                        + "  eg. deadline return book to Jurong Regional Library /by YYYY-MM-DD");
            }

            LocalDate date;
            String dateString = str.substring(timePrefix + 4, length);
            try {
                date = LocalDate.parse(dateString);
            } catch (DateTimeParseException e) {
                throw new DukeException("*Please fill in the time in the YYYY-MM-DD format*");
            }

            Deadline newDeadline = new Deadline(str.substring(9, timePrefix), date);
            this.tasks.store(newDeadline);
            return "Got it. I've added this task:\n"
                    + "  " + newDeadline + "\n"
                    + "Now you have " + this.tasks.getLength() + " task(s) in the list.";

        } else {
            throw new DukeException("*Invalid command.*"
                    + "\n  Duke Commands: bye, list, find, done, delete, todo, "
                    + "\n                 event, deadline");
        }
    }
}
