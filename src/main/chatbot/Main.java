import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {

    private static TaskManager taskManager;
    private static Storage taskStorage;
    private static TaskPrinter ui;
    private static final Path DEFAULT_LOC = Path.of("src","main", "chatbot", "data.txt");

    private static boolean getUserInput(Scanner s) {
        return s.hasNextLine();
    }

    private static void handleUserInput(String userInput) {
        String text = userInput.trim();
        String leading = text.split(" ")[0].trim();
        String trailing = text.substring(leading.length()).trim();
        Command cmd = Command.NONE;

        try {
             cmd = Command.valueOf(leading.toUpperCase());
        } catch (IllegalArgumentException e){
            ui.display("Arghh! I do not know what you mean, are you using the right\n    " +
                    "commands?");
        }

        switch (cmd) {
            case LIST:
                ui.list(taskManager.getTasks());
                break;
            case DONE:
                int ind1 = Integer.parseInt(text.split(" ")[1]) - 1;
                try {
                    if (taskManager.markAsDone(ind1)) {
                        taskStorage.saveTasks(DEFAULT_LOC);
                    }
                } catch (ChatbotException e) {
                    ui.display(e.getMessage());
                }
                break;
            case TODO:
                try {
                    Todo task = Todo.newTodo(trailing);
                    if (taskManager.addTask(task)) {
                        taskStorage.saveTasks(DEFAULT_LOC);
                    }
                } catch (ChatbotException e) {
                    ui.display(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    Deadline deadline = Deadline.newDeadline(trailing);
                    if (taskManager.addTask(deadline)) {
                        taskStorage.saveTasks(DEFAULT_LOC);
                    }
                } catch (ChatbotException e) {
                    ui.display(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    Event event = Event.newEvent(trailing);
                    if (taskManager.addTask(event)) {
                        taskStorage.saveTasks(DEFAULT_LOC);
                    }
                } catch (ChatbotException e) {
                    ui.display(e.getMessage());
                }
                break;
            case DELETE:
                int ind2 = Integer.parseInt(text.split(" ")[1]) - 1;
                try {
                    if (taskManager.removeTask(ind2)) {
                        taskStorage.saveTasks(DEFAULT_LOC);
                    }
                } catch (ChatbotException e) {
                    ui.display(e.getMessage());
                }
                break;
            case DATE:
                try {
                    ArrayList<Task> tasks = taskManager.retrieveTasksOnDate(
                            LocalDate.parse(trailing));
                    ui.list(tasks);
                } catch (DateTimeParseException e) {
                    ui.display("Please enter a valid date (yyyy-mm-dd).");
                }
                break;
            case BYE:
                ui.display("Bye, hope to see you again soon.");
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {

        taskManager = new TaskManager();
        taskStorage = new Storage(taskManager);
        ui = new TaskPrinter();

        Scanner sc = new Scanner(System.in);

        // check for file existence
        if (Files.exists(DEFAULT_LOC)) {
            try {
                // if exists, load tasks
                taskStorage.loadTasks(DEFAULT_LOC);
            } catch (ChatbotException e) {
                ui.display(e.getMessage());
            }
        } else {
            try {
                // if not exists, create new file
                Files.createFile(DEFAULT_LOC);
            } catch (IOException e) {
                ui.display("Failure: couldn't create file");
            }
        }

        System.out.println(
                        "   ####################################################\n" +
                        "   #                                                  #\n" +
                        "   #  Hey there, I'm Hanry Kun the impatient ChatBot. #\n" +
                        "   #  What can I do for you? (-.-)                    #\n" +
                        "   #                                                  #\n" +
                        "   ####################################################"
        );

        while(getUserInput(sc)) {
            String userInput = sc.nextLine();
            handleUserInput(userInput);
        }

        sc.close();
    }
}
