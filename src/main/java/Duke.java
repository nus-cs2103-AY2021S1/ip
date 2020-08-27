import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SPACING = "         ";
    private static final String DIVIDER = "_______________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Greeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String input = scanner.nextLine();
            if (input.startsWith("bye")) {
                scanner.close();
                printMessage("Bye! See you next time :)");
                System.exit(0);
            } else if (input.startsWith("list")) {
                String message = "";
                for (int i = 0; i < tasks.size(); i++) {
                    message += (i + 1) + ": " + tasks.get(i) + "\n";
                }
                printMessage(message);
            } else if (input.matches("done ([0-9]+)")) {
                int number = Integer.parseInt(input.split(" ")[1]);
                if (number > tasks.size()) {
                    printMessage("Task not found please choose another number!");
                }
                else if (number < 100 && number > 0) {
                    tasks.get(number - 1).markAsDone();
                    printMessage("This task is done, great job!\n" + tasks.get(number - 1));
                }
            } else if (input.startsWith("todo")) {
                String description = input.replace("todo", "").trim();
                if (description.length() == 0) {
                    System.out.println(new ToDoMissingDescriptionException());
                } else {
                    Task task = new ToDo(description);
                    tasks.add(task);
                    printMessage("Added: " + task + String.format("\nNow you have %d tasks in the list", tasks.size()));
                }
            } else if (input.startsWith("deadline")) {
                if (input.contains("/by")) {
                    String description = input.replace("deadline ", "").split("/by")[0].trim();
                    String dueDate = input.replace("deadline", "").split("/by")[1].trim();

                    Task task = new Deadline(description, LocalDate.parse(dueDate));
                    tasks.add(task);
                    printMessage("Added " + task + String.format("\nNow you have %d tasks in the list", tasks.size()));
                } else {
                    System.out.println(new DeadlineMissingDateException());
                }

            } else if (input.startsWith("event")) {
                if (input.contains("/at")) {
                    String description = input.replace("event ", "").split("/at")[0].trim();
                    String time = input.replace("event ", "").split("/at")[1].trim();
                    System.out.println(time);
                    Task task = new Event(description, LocalDate.parse(time));
                    tasks.add(task);
                    printMessage("Added " + task + String.format("\nNow you have %d tasks in the list", tasks.size()));
                } else {
                    System.out.println(new EventMissingDateException());
                }
            } else if (input.startsWith("delete")) {
                int taskToDelete = Integer.parseInt(input.replace("delete ", "")) - 1;

                printMessage("I have removed this task:\n" + tasks.get(taskToDelete) +
                        String.format("\nNow you have %d tasks in the list", tasks.size() - 1));
                tasks.remove(taskToDelete);
            } else {
                System.out.println(new InvalidInputException());
            }
        }
    }

    private static void Greeting() {
        String logo = "Dash";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you today?");
        System.out.println(DIVIDER);
    }

    private static void printMessage(String message) {
        System.out.println(SPACING + DIVIDER);
        String[] messages = message.split("\n");
        for (String str : messages) {
            System.out.println(SPACING + str);
        }
        System.out.println(SPACING + DIVIDER);

    }
}
