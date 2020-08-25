import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke() {
        try {
            storage = new Storage();
            tasks = new TaskList(storage);
            parser = new Parser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() {
        Scanner scanner = new Scanner(System.in);
        String s;
        Ui.welcomeMessage();
        Parser.Command command;
        while((command = Parser.parse(s = scanner.nextLine(), tasks.size())) != Parser.Command.BYE) {
            switch (command) {
                default:
                    try {
                        Task task = Parser.createTask(s);
                        tasks.addTask(task);
                        Ui.addTaskMessage(tasks.get(tasks.size()-1), tasks.size());
                    } catch (DukeException e) {
                        if (e.getMessage().equals(DukeException.IGNORE)) {
                            Ui.ignoreMessage();
                        } else if (e.getMessage().equals(DukeException.EMPTY_TODO)) {
                            Ui.emptyTodoMessage();
                        }
                    } catch (DateTimeParseException e) {
                        Ui.dateFormatReminder();
                    }
                    break;
                case DONE:
                    tasks.setCompleted(parser.whichTask);
                    Ui.doneMessage(tasks.get(parser.whichTask));
                    break;
                case DELETE:
                    Task deleted = tasks.get(parser.whichTask);
                    tasks.remove(parser.whichTask);
                    Ui.deleteMessage(deleted, tasks.size());
                    break;
                case LIST:
                    Ui.list(tasks);
                    break;
            }
        }
        Ui.exitMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
