import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    protected List<Task> tasks;
    protected Scanner sc;

    public Duke() {
        this.tasks = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void sayHello() {
        System.out.println("Hello! I'm Clara! :D How may I help you? :)");
    }

    public void sayBye() {
        System.out.println("\tBye! Have a great day and hope to see you soon! :D");
    }

    public void addTodo(String description) {
        ToDo task = new ToDo(description, false);
        this.tasks.add(task);
        printAddedConfirmation(task);
    }

    public void addDeadline(String description, String doBy) {
        Deadline task = new Deadline(description, false, doBy);
        this.tasks.add(task);
        printAddedConfirmation(task);

    }

    public void addEvent(String description, String time) {
        Event task = new Event(description, false, time);
        this.tasks.add(task);
        printAddedConfirmation(task);
    }

    public void printAddedConfirmation(Task task) {
        int size = this.tasks.size();
        System.out.println("\tOkay! I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println(String.format("\tNow you have %d %s in the list. Jiayous! :D", size, size > 1? "tasks":"task"));
    }

    public void markTaskAsDone(int index) {
        String description = this.tasks.get(index).description;
        Task updatedTask = new Task(description, true);

        this.tasks.set(index, updatedTask);

        System.out.println("\tNice! I've marked this task as done:\n\t\t" + updatedTask);
    }

    public void displayTasks() {
        if (tasks.size() > 0) {
            System.out.println("\tThese are the tasks in your list. Jiayous! :)");
        } else {
            System.out.println("\tYou have no task in your list. :D");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t\t%d. %s", i+1, this.tasks.get(i).toString()));
        }
    }

    public String getRestOfString(String[] splitSpace) throws DukeException {
        if (splitSpace.length == 1) {
            throw new DukeException("\tSorry! The description of a todo cannot be empty :')");
        }
        StringBuilder rest = new StringBuilder();
        for (int i = 1; i < splitSpace.length; i++) {
            rest.append(splitSpace[i]);
            if (i != splitSpace.length - 1) {
                rest.append(" ");
            }
        }
        return rest.toString();
    }

    public void serveClient() {
        sayHello();
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] splitSpace = input.split(" ");
                String keyword = splitSpace[0];

                switch (keyword) {
                    case "bye":
                        sayBye();
                        break;
                    case "list":
                        displayTasks();
                        break;
                    case "done":
                        int index = Integer.parseInt(splitSpace[1]) - 1;
                        markTaskAsDone(index);
                        break;
                    case "todo":
                        addTodo(getRestOfString(splitSpace));
                        break;
                    case "deadline":
                        String[] splitSlash = getRestOfString(splitSpace).split(" /by ");
                        addDeadline(splitSlash[0], splitSlash[1]);
                        break;
                    case "event":
                        String[] split = getRestOfString(splitSpace).split(" /at ");
                        addEvent(split[0], split[1]);
                        break;
                    default:
                        throw new DukeException("\tApologies! I do not understand what that means :')");
                }

                if (keyword.equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
