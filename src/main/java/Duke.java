import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "    ________________________________________________________\n";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";

    private static void greet() {
        String welcomeMessage = "     Konnichiwa!\n"
                + "     What can I do for you?\n";
        System.out.println(DIVIDER + welcomeMessage + DIVIDER);
    }

    private static void exit() {
        String exitMessage = "     Ja ne!\n";
        System.out.println(DIVIDER + exitMessage + DIVIDER);
    }

    private static void chat() {
        Store storage = new Store();
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();
        while (!input.equals(EXIT_COMMAND)) {
            System.out.print(DIVIDER);
            String[] rawInput = input.split(" ", 2);
            String command = rawInput[0];
            switch(command) {
            case LIST_COMMAND:
                storage.listItems();
                break;
            case DONE_COMMAND:
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                storage.markAsDone(index);
                break;
            case TODO_COMMAND:
                ToDo newToDo = new ToDo(rawInput[1]);
                storage.add(newToDo);
                break;
            case DEADLINE_COMMAND:
                String deadlineDescription = rawInput[1].split("/")[0].trim();
                String due = rawInput[1].split("/", 2)[1].split(" ", 2)[1].trim();
                Deadline newDeadline = new Deadline(deadlineDescription, due);
                storage.add(newDeadline);
                break;
            case EVENT_COMMAND:
                String eventDescription = rawInput[1].split("/")[0].trim();
                String time = rawInput[1].split("/", 2)[1].split(" ", 2)[1].trim();
                Event newEvent = new Event(eventDescription, time);
                storage.add(newEvent);
                break;
            }
            System.out.println(DIVIDER);
            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        chat();
        exit();
    }
}
