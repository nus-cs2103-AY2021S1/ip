import java.text.ParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Duke {
    protected List<Task> tasks;
    protected Scanner sc;
    protected Storage storage;

    public Duke() {
        this.storage = new Storage();
        this.tasks = storage.loadTasks();
        this.sc = new Scanner(System.in);
    }

    public void sayHello() {
        System.out.println("Hello! I'm Clara! :D How may I help you? :)");
        this.displayTasks();
    }

    public void sayBye() {
        System.out.println("\tBye! Have a great day and hope to see you soon! :D");
    }


    public void addTask(String keyword, String description) throws DukeException {
        switch (keyword) {
        case "todo":
            Task task = new ToDo(description, false);
            this.tasks.add(task);
            printAddedConfirmation(task);
            break;
        case "deadline":
            String[] splitSlash = description.split(" /by ");
            if (splitSlash.length != 2) {
                throw new DukeException("\tPaise! :') Please use the format: deadline <task> /by <time>" +
                        "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>");
            }
            task = new Deadline(splitSlash[0], false, parseDate(splitSlash[1]));
            this.tasks.add(task);
            printAddedConfirmation(task);
            break;
        case "event":
            splitSlash = description.split(" /at ");
            if (splitSlash.length != 2) {
                throw new DukeException("\tPaise! :') Please use the correct format: event <task> /at <time> +" +
                        "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>");
            }
            task = new Event(splitSlash[0], false, parseDate(splitSlash[1]));
            this.tasks.add(task);
            printAddedConfirmation(task);
            break;
        default:
            break;
        }
    }

    public void printAddedConfirmation(Task task) {
        int size = this.tasks.size();
        System.out.println("\tOkay! I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println(String.format("\tNow you have %d %s in the list. Jiayous! :D", size, size > 1 ? "tasks" : "task"));
    }

    public void markTaskAsDone(int index) throws DukeException {
        Task task = this.tasks.get(index);
        task.toggleIsDone();
        try {
            this.tasks.set(index, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! THe index is out of bounds! :')");
        }
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + task);
    }

    public void deleteTask(int index) throws DukeException {
        Task task = this.tasks.get(index);
        int size = this.tasks.size();
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! The index is out of bounds! :')");
        }
        System.out.println("\tOkay! I've removed this task:\n\t\t" + task.toString());
        System.out.println(String.format("\tNow you have %d %s in the list. Jiayous! :D", size - 1, size - 1 > 1 ? "tasks" : "task"));

    }

    public void displayTasks() {
        if (tasks.size() > 0) {
            System.out.println("\tThese are the tasks in your list. Jiayous! :)");
        } else {
            System.out.println("\tYou have no task in your list. :D");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t\t%d. %s", i + 1, this.tasks.get(i).toString()));
        }
    }

    public Date parseDate(String strDate) throws DukeException{
        String formatWithMin = "y-M-d HH:mm";
        String formatWithoutMin = "y-M-d";
        try {
            return new SimpleDateFormat(strDate.length() > 11 ? formatWithMin : formatWithoutMin).parse(strDate);
        } catch (ParseException e) {
            throw new DukeException("Sorry! Wrong time format!");
        }
    }


    public void serveClient() {
        sayHello();
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] split = input.split(" ", 2);

                switch (split[0]) {
                case "bye":
                    sayBye();
                    break;
                case "list":
                    displayTasks();
                    break;
                case "done":
                    try {
                        int index = Integer.parseInt(split[1]) - 1;
                        markTaskAsDone(index);
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tPaise! :') Please use the correct format: done <order of task in the list>");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("\tPaise! :') The index is out of bounds!");
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    if (split.length < 2) {
                        throw new DukeException("\tSorry! The description of a todo cannot be empty :')");
                    }
                    addTask(split[0], split[1]);
                    break;
                case "delete":
                    try {
                        int index = Integer.parseInt(split[1]) - 1;
                        deleteTask(index);
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tPaise! :') Please use the correct format: delete <order of task in the list>");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("\tPaise! :') The index is out of bounds!");
                    }
                    break;
                default:
                    throw new DukeException("\tApologies! I do not understand what that means :')");
                }

                if (split[0].equals("bye")) {
                    break;
                }

                storage.saveTasks(tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
