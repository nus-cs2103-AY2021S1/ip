import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String divider =
            "\t---------------------------------------------------\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void greetings() {
        String logo =
                "\t __\n" +
                "\t/ _|   ___    ___  _   _  ___\n" +
                "\t| |_  / _ \\  / __|| | | |/ __|\n" +
                "\t|  _|| (_) || (__ | |_| |\\__ \\ _\n" +
                "\t|_|   \\___/  \\___| \\__,_||___/(_)\n";
        System.out.println("Welcome to\n" + logo);
        String greetings = divider
                + "\t>~< I am Pocus, your personal assistant!"
                + "\n\tBefore we start, may I know your name?\n"
                + divider;
        System.out.print(greetings);
    }

    private static void addressUser(String name) {
        String address = divider
                + "\tHi there, " + name + "!"
                + "\n\tHow can I help you today?\n"
                + divider;
        System.out.print(address);
    }

    private static void addToDo(String input) throws ArrayIndexOutOfBoundsException {
        String description;
        try {
            description = input.split("todo")[1];
        } catch (ArrayIndexOutOfBoundsException e) { // for no spacing after command eg. "todo"
            DukeErrorsHandler.toDoInvalidDescription();
            return;
        }
        if (description.isBlank()) { // for spacing after command eg. "todo "
            DukeErrorsHandler.toDoInvalidDescription();
            return;
        }
        Task toDo = new ToDo(description);
        tasks.add(toDo);
        String printing = divider
                + "\tGot it. I've added this task:\n\t\t"
                + toDo + "\n\t" + "Now you have "
                + tasks.size() + " tasks in the list.\n"
                + divider;
        System.out.print(printing);
    }

    private static void addDeadline(String input) throws ArrayIndexOutOfBoundsException {
        String information;
        try {
            information = input.split("deadline")[1];
        } catch (ArrayIndexOutOfBoundsException e) { // for no spacing after command eg. "deadline"
            DukeErrorsHandler.deadlineInvalidDescription();
            return;
        }
        if (information.isBlank()) { // for spacing after command eg. "deadline "
            DukeErrorsHandler.deadlineInvalidDescription();
            return;
        }
        int end = information.indexOf("/");
        String description = information.substring(1, end - 1);
        String by = information.substring(end + 4);
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        String printing = divider
                + "\tGot it. I've added this task:\n\t\t"
                + deadline + "\n\t" + "Now you have "
                + tasks.size() + " tasks in the list.\n"
                + divider;
        System.out.print(printing);
    }

    private static void addEvent(String input) throws ArrayIndexOutOfBoundsException {
        String information;
        try {
            information = input.split("event")[1];
        } catch (ArrayIndexOutOfBoundsException e) { // for no spacing after command eg. "event"
            DukeErrorsHandler.eventInvalidDescription();
            return;
        }
        if (information.isBlank()) { // for spacing after command eg. "event "
            DukeErrorsHandler.eventInvalidDescription();
            return;
        }
        int end = information.indexOf("/");
        String description = information.substring(1, end - 1);
        String at = information.substring(end + 4);
        Task event = new Event(description, at);
        tasks.add(event);
        String printing = divider
                + "\tGot it. I've added this task:\n\t\t"
                + event + "\n\t" + "Now you have "
                + tasks.size() + " tasks in the list.\n"
                + divider;
        System.out.print(printing);
    }

    private static void markTaskDone(String input) {
        int index = Integer.parseInt(input.substring(5));
        Task finishedTask = tasks.get(index - 1);
        finishedTask.markAsDone();
        String doneTask = divider
                + "\tGood job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n" + divider;
        System.out.print(doneTask);
    }

    private static void listTasks() {
        System.out.print(divider);
        System.out.print("\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            System.out.print("\t" + number + ". " + tasks.get(i) + "\n");
        }
        System.out.print(divider);
    }

    private static void exitFocus() {
        String exit = divider
                + "\tHopefully I have helped you today. Bye! >~<\n"
                + divider;
        System.out.print(exit);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greetings();
        String name = sc.nextLine();
        addressUser(name);

        String input;
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.contains("todo")) {
                addToDo(input);
            } else if (input.contains("deadline")) {
                addDeadline(input);
            } else if (input.contains("event")) {
                addEvent(input);
            } else if (input.contains("done")) {
                markTaskDone(input);
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.equals("bye")) {
                exitFocus();
                break;
            } else {
                DukeErrorsHandler.invalidInput();
            }
        }
        sc.close();
    }
}