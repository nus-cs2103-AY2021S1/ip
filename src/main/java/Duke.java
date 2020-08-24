import java.util.*;

public class Duke {
    Ui ui;
    Duke() {
        this.ui = new Ui();
    }

    private void start() {
        ui.showStartScreen();
    }

    public static void main(String[] args) {
        new Duke().start();

        String line = "------------------------";

        System.out.println(line);
        TaskManager tm;
        try {
            tm = new TaskManager();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("Program will close.");
            return;
        }
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
        System.out.println("when is the due date (Day Month Year): ");
        String dueDate = sc.nextLine();
        if (dueDate.isEmpty()) {
            throw new DukeException("You must provide a due date for the deadline task!");
        }
        try {
            tm.add(new Deadline(deadlineName, dueDate));
            System.out.println("*added: " + deadlineName);
        } catch (DukeException e) {
            throw e;
        }
    }

    private static void event(Scanner sc, TaskManager tm) throws DukeException {
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
            tm.add(new Event(eventName, start, end));
            System.out.println("*added: " + eventName);
        } catch (DukeException e) {
            throw e;
        }
        
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
