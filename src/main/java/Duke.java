<<<<<<< HEAD
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
=======
import java.time.LocalDate;
>>>>>>> branch-Level-8
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SPACING = "         ";
    private static final String DIVIDER = "_______________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greeting();
        loadFromFile();
        Scanner scanner = new Scanner(System.in);
        File f = new File("data.txt");
        try {
            f.createNewFile();
        } catch (IOException ioException) {
            System.out.println("An error occured");
            ioException.printStackTrace();
        }
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
                    saveToFile(task);
                    printMessage("Added: " + task + String.format("\nNow you have %d tasks in the list", tasks.size()));
                }
            } else if (input.startsWith("deadline")) {
                if (input.contains("/by")) {
                    String description = input.replace("deadline ", "").split("/by")[0].trim();
                    String dueDate = input.replace("deadline", "").split("/by")[1].trim();

                    Task task = new Deadline(description, LocalDate.parse(dueDate));
                    tasks.add(task);
                    saveToFile(task);
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
                    saveToFile(task);
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

    private static void loadFromFile() {
        File dataFile = new File("data.txt");
        if (dataFile.exists()) {
            try {
                Scanner scanner = new Scanner(dataFile);
                while (scanner.hasNextLine()) {
                    String taskEntry = scanner.nextLine();
                    String[] taskInformation = taskEntry.split("\\|");
                    if (taskEntry.startsWith("[T]")) {
                        String description = taskEntry.split("\\|")[1];
                        Task task = new ToDo(description);
                        if (taskInformation[0].contains("1")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    } else if (taskEntry.startsWith("[D]")) {
                        String description = taskEntry.split("\\|")[1];
                        Task task = new Deadline(description, "duedate");
                        if (taskInformation[0].contains("1")) {
                            task.markAsDone();
                        }
                    } else if (taskEntry.startsWith("[E]")) {
                        String description = taskEntry.split("\\|")[1];
                        Task task = new Event(description, "time");
                        if (taskInformation[0].contains("1")) {
                            task.markAsDone();
                        }
                    }

                }
            } catch (IOException ioException) {
                System.out.println("An error has occurred");
                ioException.printStackTrace();
            }
        }
    }

    private static void saveToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter("data.txt", true);
            String[] strings = task.toString().split("\\|");
            String isDone = task.getIsDone() ? "1" : "0";
            String description = strings[2];
            fileWriter.write(strings[0] + " | " + isDone + " | " + description + "\n");
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println("An error has occurred");
            ioException.printStackTrace();
        }
    }

    private static void greeting() {
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
<<<<<<< HEAD
        System.out.println(spacing + divider);
=======
        System.out.println(SPACING + DIVIDER);

>>>>>>> branch-Level-8
    }
}
