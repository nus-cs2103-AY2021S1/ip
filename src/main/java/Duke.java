import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "------------------------";

        System.out.println(line);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        
        while(true) {
            System.out.print("your input: ");
            String cmd = sc.nextLine();
            System.out.println("\n" + line);
            if (cmd.equals("bye")) {
                end();
                break;
            }
            try {
                handleCmd(cmd, sc, tasks);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println(line);
        }
    }

    private static void handleCmd(String cmd, Scanner sc, List<Task> tasks) throws DukeException {
        switch(cmd) {
            case "list":
                list(tasks);
                break;
            case "complete":
                complete(sc, tasks);
                break;
            case "todo":
                try {
                    todo(sc, tasks);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            case "deadline":
                try {
                    deadline(sc, tasks);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            case "event":
                try {
                    event(sc, tasks);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            case "delete":
                try {
                    delete(sc, tasks);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            default:
                throw new DukeException("That was an invalid command");
        }
    }

    private static void end() {
         System.out.println("Bye. Hope to see you again soon!");       
    }

    private static void list(List<Task> tasks) {
        int i = 1;
        System.out.println("*Here are all your tasks");
        for (Task task: tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    private static void complete(Scanner sc, List<Task> tasks) throws DukeException {
        System.out.println("Which task do you wish to mark complete? ");
        int taskNum = Integer.parseInt(sc.nextLine());
        try {
            tasks.get(taskNum - 1).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You did not provide a valid task number!");
        }
    }

    private static void todo(Scanner sc, List<Task> tasks) throws DukeException {
        System.out.println("what is the name of the todo: ");
        String todoName = sc.nextLine();
        if (todoName.isEmpty()) {
            throw new DukeException("You must provide a name for the todo task!");
        }
        tasks.add(new Todo(todoName));
        System.out.println("*added: " + todoName);
    }

    private static void deadline(Scanner sc, List<Task> tasks) throws DukeException {
        System.out.println("what is the name of the task: ");
        String deadlineName = sc.nextLine();
        if (deadlineName.isEmpty()) {
            throw new DukeException("You must provide a name for the deadline task!");
        }
        System.out.println("when is the due date (Day Month Year): ");
        String dueDate = sc.nextLine();
        if (dueDate.isEmpty()) {
            throw new DukeException("You must provide a due date for the deadline task!");
        }
        try {
            tasks.add(new Deadline(deadlineName, dueDate));
            System.out.println("*added: " + deadlineName);
        } catch (DukeException e) {
            throw e;
        }
    }

    private static void event(Scanner sc, List<Task> tasks) throws DukeException {
        System.out.println("what is the name of the event: ");
        String eventName = sc.nextLine();
        if (eventName.isEmpty()) {
            throw new DukeException("You must provide a name for the event!");
        }
        System.out.println("when does the event start (Day Month Year Hour:Minute): ");
        String start = sc.nextLine();
        if (start.isEmpty()) {
            throw new DukeException("You must provide a start time for the event!");
        }
        System.out.println("when does the event end (Day Month Year Hour:Minute): ");
        String end = sc.nextLine();
        if (end.isEmpty()) {
            throw new DukeException("You must provide an end time for the event!");
        }
        try {
            tasks.add(new Event(eventName, start, end));
            System.out.println("*added: " + eventName);
        } catch (DukeException e) {
            throw e;
        }
        
    }

    private static void delete(Scanner sc, List<Task> tasks) throws DukeException {
        System.out.println("Which task would you like to remove: ");
        int taskNum = Integer.parseInt(sc.nextLine());
        try {
            Task task = tasks.remove(taskNum - 1);
            System.out.println(String.format("Successfully removed the following task:\n %s", task));
            System.out.println(String.format("You have a total of %d tasks left", tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You did not provide a valid task number!");
        }
    }
}
