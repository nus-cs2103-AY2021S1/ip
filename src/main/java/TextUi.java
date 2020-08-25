import java.util.ArrayList;
import java.util.List;

public class TextUi {
    public static final String DIVIDER = "===================================================\n";

    public static void printHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(DIVIDER + "Hello! I'm Duke\n" + "What can I do for you?\n" + DIVIDER);
    }

    public static void printTaskStatements(String input) {
        String category = Parser.getCategory(input);
        String description = Parser.getDescription(input);
        switch (category) {
            case "todo":
                printTodo(description);
                break;
            case "deadline":
                printDeadline(description);
                break;
            case "event":
                printEvent(description);
                break;
            case "done":
                printDone(description);
                break;
            case "delete":
                printDelete(description);
                break;
            case "list":
                printList(description);
                break;
            case "bye":
                printBye(description);
            default:
                throw new IllegalArgumentException(DIVIDER + "☹ OOPS!!! Invalid input. Try again!\n" + DIVIDER);
        }
    }

    public static void printTodo(String description) {
        if (description == null) {
            throw new IllegalArgumentException(DIVIDER +
                    "☹ OOPS!!! The description of a todo cannot be empty. Try again!\n" + DIVIDER);
        } else {
            System.out.println(DIVIDER + "Got it. I've added this task:");
        }
    }

    public static void printDeadline(String description) {
        String[] descriptionArray = description.split("/by");
        String deadlineName = descriptionArray[0];
        if (deadlineName == null) {
            throw new IllegalArgumentException(DIVIDER +
                    "☹ OOPS!!! The description of a deadline cannot be empty. Try again!\n" + DIVIDER);
        } else if (descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no deadline stated");
        } else if (descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated");
        } else {
            System.out.println(DIVIDER + "Got it. I've added this task:");
        }
    }

    public static void printEvent(String description) {
        String[] descriptionArray = description.split("/at");
        String eventName = descriptionArray[0];
        if (eventName == null) {
            throw new IllegalArgumentException(DIVIDER +
                    "☹ OOPS!!! The description of an event cannot be empty. Try again!\n" + DIVIDER);
        } else if (descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no deadline stated");
        } else if (descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated");
        } else {
            System.out.println(DIVIDER + "Got it. I've added this task:");
        }
    }

        public static void printDone(String description) {
            if (description == null) {
                throw new IllegalArgumentException(DIVIDER +
                        "☹ OOPS!!! Not sure which task is to be indicated as done. Try again!\n" + DIVIDER);
            } else {
                System.out.println(DIVIDER + "Nice! I've marked this task as done:");
            }
        }

        public static void printDelete(String description) {
            if (description == null) {
                throw new IllegalArgumentException(DIVIDER +
                        "☹ OOPS!!! Not sure which task is to be deleted. Try again!\n" + DIVIDER);
            } else {
                System.out.println(DIVIDER + "Noted. I've removed this task:\n");
            }
        }

        public static void printList(String description) {
            if (description != null) {
                throw new IllegalArgumentException(DIVIDER + "☹ OOPS!!! Invalid input. Try again!\n" + DIVIDER);
            } else {
                System.out.println(DIVIDER + "Here are the tasks in your list:");
            }
        }

        public static void printBye(String description) {
            if (description != null) {
                throw new IllegalArgumentException(DIVIDER + "☹ OOPS!!! Invalid input. Try again!\n" + DIVIDER);
            } else {
                System.out.println(DIVIDER + "Bye bye! Hope to see you again soon!\n" + DIVIDER);
            }
        }

}
