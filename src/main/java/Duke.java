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

        TaskManager tm = new TaskManager();
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.print("your input: ");
            String cmd = sc.nextLine();
            System.out.println("\n" + line);
            if (cmd.equals("bye")) {
                end();
                break;
            }
            try {
                handleCmd(cmd, sc, tm);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println(line);
        }
    }

    private static void handleCmd(String cmd, Scanner sc, TaskManager tm) throws DukeException {
        switch(cmd) {
            case "list":
                tm.listTasks();
                break;
            case "complete":
                complete(sc, tm);
                break;
            case "todo":
                try {
                    todo(sc, tm);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            case "deadline":
                try {
                    deadline(sc, tm);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            case "event":
                try {
                    event(sc, tm);
                } catch (DukeException e) {
                    throw e;
                }
                break;
            case "delete":
                try {
                    delete(sc, tm);
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

    private static void complete(Scanner sc, TaskManager tm) throws DukeException {
        System.out.println("Which task do you wish to mark complete? ");
        int taskNum = Integer.parseInt(sc.nextLine());
        try {
            tm.markDone(taskNum);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You did not provide a valid task number!");
        }
    }

    private static void todo(Scanner sc, TaskManager tm) throws DukeException {
        System.out.println("what is the name of the todo: ");
        String todoName = sc.nextLine();
        if (todoName.isEmpty()) {
            throw new DukeException("You must provide a name for the todo task!");
        }
        tm.add(new Todo(todoName));
        System.out.println("*added: " + todoName);
    }

    private static void deadline(Scanner sc, TaskManager tm) throws DukeException {
        System.out.println("what is the name of the task: ");
        String deadlineName = sc.nextLine();
        if (deadlineName.isEmpty()) {
            throw new DukeException("You must provide a name for the deadline task!");
        }
        System.out.println("when is the due date: ");
        String dueDate = sc.nextLine();
        if (dueDate.isEmpty()) {
            throw new DukeException("You must provide a due date for the deadline task!");
        }
        tm.add(new Deadline(deadlineName, dueDate)); // TODO: handle exception
        System.out.println("*added: " + deadlineName);
        
    }

    private static void event(Scanner sc, TaskManager tm) throws DukeException {
        System.out.println("what is the name of the event: ");
        String eventName = sc.nextLine();
        if (eventName.isEmpty()) {
            throw new DukeException("You must provide a name for the event!");
        }
        System.out.println("when does the event start: ");
        String start = sc.nextLine();
        if (start.isEmpty()) {
            throw new DukeException("You must provide a start time for the event!");
        }
        System.out.println("when does the event end: ");
        String end = sc.nextLine();
        if (end.isEmpty()) {
            throw new DukeException("You must provide an end time for the event!");
        }
        tm.add(new Event(eventName, start, end));
        System.out.println("*added: " + eventName);
    }

    private static void delete (Scanner sc, TaskManager tm) throws DukeException {
        System.out.println("Which task would you like to remove: ");
        int taskNum = Integer.parseInt(sc.nextLine());
        try {
            tm.deleteTask(taskNum);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You did not provide a valid task number!");
        }
    }
}
