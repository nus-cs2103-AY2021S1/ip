import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static Scanner sc;
    private static List<Task> itemsList;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        itemsList = new ArrayList<>();
        executeProgramme();
    }

    private static void executeProgramme() {
        final String TERMINATION_PHRASE = "bye";
        final String LIST_PHRASE = "list";
        final String COMPLETE_TASK_PHRASE = "done";
        final String TODO_PHRASE = "todo";
        final String DEADLINE_PHRASE = "deadline";
        final String EVENT_PHRASE = "event";

        displayStart();

        String command = sc.next();

        while (!command.equals(TERMINATION_PHRASE)) {

            Task newTask;

            switch (command) {
                case LIST_PHRASE:
                    printList();
                    break;
                case COMPLETE_TASK_PHRASE:
                    displayCompleteTask();

                    int index = sc.nextInt() - 1;
                    itemsList.get(index).doTask();
                    break;
                case TODO_PHRASE:
                    newTask = new ToDo(sc.nextLine());
                    itemsList.add(newTask);
                    displayAddTask(newTask);
                    break;
                case DEADLINE_PHRASE:
                    // String array whose first element is the task and second element is the deadline
                    // substring(1) to remove the starting space
                    String[] taskAndDeadline = sc.nextLine().substring(1).split(" /by ");
                    newTask = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
                    itemsList.add(newTask);
                    displayAddTask(newTask);
                    break;
                case EVENT_PHRASE:
                    // String whose first element is task and second element is time of event
                    String[] eventAndTime = sc.nextLine().substring(1).split(" /at ");
                    newTask = new Event(eventAndTime[0], eventAndTime[1]);
                    itemsList.add(newTask);
                    displayAddTask(newTask);
                    break;
                default:
                    System.out.println("Invalid request");
                    break;
            }

            command = sc.next();
        }

        displayExit();
    }

    private static void displayStart() {
        final String HELLO_MESSAGE = "Hello, and welcome to my humble abode. \n" +
                "I'm supposed to help you but I'll be the judge of that. Anyway what do you want.";

        System.out.println(HORIZONTAL_RULE);
        System.out.println(HELLO_MESSAGE);
        System.out.println(HORIZONTAL_RULE);
    }

    private static void displayExit() {
        final String BYE_MESSAGE = "Thank you for that utter waste of time.\n" +
                "Can't wait to see you again...";

        System.out.println(HORIZONTAL_RULE);
        System.out.println(BYE_MESSAGE);
        System.out.println(HORIZONTAL_RULE);
    }

    private static void printList() {
        final String CHECKMARK = "[✓]";
        final String CROSS = "[✗]";

        System.out.println(HORIZONTAL_RULE);
        System.out.println("Here are all your burdens");

        for (int i = 1; i <= itemsList.size(); i++) {
            Task item = itemsList.get(i - 1);
            String fullItem = i + ". " + item.toString();

            System.out.println(fullItem);
        }

        System.out.println(HORIZONTAL_RULE);
    }

    private static void displayCompleteTask() {
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Congratulations, you actually did something");
        System.out.println(HORIZONTAL_RULE);
    }

    private static void displayAddTask(Task task) {
        System.out.println(HORIZONTAL_RULE);
        System.out.println("One more task added for you sire");
        System.out.println(task);
        System.out.println("You have " + itemsList.size() + " tasks. Enjoy!");
        System.out.println(HORIZONTAL_RULE);
    }
}
