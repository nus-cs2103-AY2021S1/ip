import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;

public class Main {

    private static TaskList taskList;
    private static Storage taskStorage;
    private static Ui ui;

    private static boolean getUserInput(Scanner s) {
        return s.hasNextLine();
    }

    private static void handleUserInput(String userInput) {

        boolean tasklistChanged = false;
        String text = userInput.trim();
        String leading = text.split(" ")[0].trim();
        String trailing = text.substring(leading.length()).trim();
        Command cmd = Command.NONE;

        try {
             cmd = Command.valueOf(leading.toUpperCase());
        } catch (IllegalArgumentException e){
            ui.showErrorMessage("Arghh! I do not know what you mean, are you using the right\n    " +
                    "commands?");
        }

        switch (cmd) {
            case LIST:
                ui.list(taskList.getTasks());
                break;
            case DONE:
                int ind1 = Integer.parseInt(text.split(" ")[1]) - 1;
                try {
                    Task taskDone = taskList.markAsDone(ind1);
                    ui.markDoneSuccess(taskDone);
                    tasklistChanged = true;
                } catch (ChatbotException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                break;
            case TODO:
                try {
                    Todo task = Todo.newTodo(trailing);
                    if (taskList.addTask(task)) {
                        ui.addSuccess(task, taskList.count());
                        tasklistChanged = true;
                    }
                } catch (ChatbotException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    Deadline deadline = Deadline.newDeadline(trailing);
                    if (taskList.addTask(deadline)) {
                        ui.addSuccess(deadline, taskList.count());
                        tasklistChanged = true;
                    }
                } catch (ChatbotException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    Event event = Event.newEvent(trailing);
                    if (taskList.addTask(event)) {
                        ui.addSuccess(event, taskList.count());
                        tasklistChanged = true;
                    }
                } catch (ChatbotException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                break;
            case DELETE:
                int ind2 = Integer.parseInt(text.split(" ")[1]) - 1;
                try {
                    Task deletedTask = taskList.removeTask(ind2);
                    ui.deleteSuccess(deletedTask, taskList.count());
                    tasklistChanged = true;
                } catch (ChatbotException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                break;
            case DATE:
                try {
                    ArrayList<Task> tasks = taskList.retrieveTasksOnDate(
                            LocalDate.parse(trailing));
                    ui.list(tasks);
                } catch (DateTimeParseException e) {
                    ui.showErrorMessage("Please enter a valid date (yyyy-mm-dd).");
                }
                break;
            case BYE:
                ui.showErrorMessage("Bye, hope to see you again soon.");
                System.exit(0);
                break;
            default:
                break;
        }

        if (tasklistChanged) {
            return;
        }

        try {
            taskStorage.saveTasks(taskList.getTasks());
        } catch (ChatbotException e) {
            ui.showErrorMessage(e.getMessage());
        }

    }

    public static void main(String[] args) {

        System.out.println(
                        "   ####################################################\n" +
                        "   #                                                  #\n" +
                        "   #  Hey there, I'm Hanry Kun the impatient ChatBot. #\n" +
                        "   #  What can I do for you? (-.-)                    #\n" +
                        "   #                                                  #\n" +
                        "   ####################################################"
        );

        final Path dataLocation = Path.of("src","main", "chatbot", "data.txt");

        ui = new Ui();
        taskStorage = new Storage(dataLocation);
        try {
            taskList = new TaskList(taskStorage.loadTasks());
        } catch (ChatbotException e) {
            ui.showErrorMessage(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        while(getUserInput(sc)) {
            String userInput = sc.nextLine();
            handleUserInput(userInput);
        }

        sc.close();
    }
}
