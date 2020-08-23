import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {

    private static TaskManager tskManager;

    private static boolean getUserInput(Scanner s) {
        return s.hasNextLine();
    }

    private static void handleUserInput(String userInput) {

        TaskPrinter tskPrint = new TaskPrinter();
        String text = userInput.trim();
        String leading = text.split(" ")[0].trim();
        String trailing = text.substring(leading.length()).trim();
        Command cmd = Command.NONE;

        try {
             cmd = Command.valueOf(leading.toUpperCase());
        } catch (IllegalArgumentException e){
            tskPrint.display("Arghh! I do not know what you mean, are you using the right\n    " +
                    "commands?");
        }

        switch (cmd) {
            case LIST:
                tskManager.listAll();
                break;
            case DONE:
                int ind1 = Integer.parseInt(text.split(" ")[1]) - 1;
                tskManager.markAsDone(ind1);
                break;
            case TODO:
                try {
                    Todo task = Todo.newTodo(trailing);
                    tskManager.addTask(task);
                } catch (ChatbotException e) {
                    tskPrint.display(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    Deadline deadline = Deadline.newDeadline(trailing);
                    tskManager.addTask(deadline);
                } catch (ChatbotException e) {
                    tskPrint.display(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    Event event = Event.newEvent(trailing);
                    tskManager.addTask(event);
                } catch (ChatbotException e) {
                    tskPrint.display(e.getMessage());
                }
                break;
            case DELETE:
                int ind2 = Integer.parseInt(text.split(" ")[1]) - 1;
                tskManager.removeTask(ind2);
                break;
            case BYE:
                tskPrint.display("Bye, hope to see you again soon.");
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {

        tskManager = TaskManager.getInstance();

        System.out.println(
                "   ####################################################\n" +
                "   #                                                  #\n" +
                "   #  Hey there, I'm Hanry Kun the impatient ChatBot. #\n" +
                "   #  What can I do for you? (-.-)                    #\n" +
                "   #                                                  #\n" +
                "   ####################################################"
        );

        // program setup
        try {
            Path path = Path.of("src","main", "chatbot", "data.txt");
            if (Files.exists(path)) {
                Stream<Task> taskStream = Files.lines(path).map(line -> {
                        String[] lineData = line.split("\\|");

                        String type = lineData[0].trim();
                        boolean isDone = lineData[1].trim().equals("1");
                        String description = lineData[2].trim();
                        String timestamp = lineData[3].trim();

                        Task task = null;
                        switch (type) {
                            case "T":
                                task = new Todo(description, isDone);
                                break;
                            case "D":
                                task = new Deadline(description, isDone, timestamp);
                                break;
                            case "E":
                                task = new Event(description, isDone, timestamp);
                                break;
                            default:
                                break;
                        }
                        return task;
                });
                tskManager.loadTasks(taskStream);
            } else {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        while(getUserInput(sc)) {
            String userInput = sc.nextLine();
            handleUserInput(userInput);
        }

        sc.close();
    }
}
