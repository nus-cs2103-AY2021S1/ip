import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static Ui uI;
    private static Parser parser;
    private static Storage storage;
    private static ArrayList<Task> task_list = new ArrayList<>();

    public Duke() {
        uI = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    protected void getResponse(String input) throws IOException {
        task_list = storage.loadFile();

        if (input.contains("bye")) {
            uI.bye();
        } else {
            try {
                run(input);
            } catch (DukeException d) {
                uI.printError(d);
            } catch (DateTimeParseException d) {
                uI.printInvalidDateFormatError();
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Please enter the command in the correct format");
                System.out.println("Please try again\n");
            }

        }
    }

    private static void run(String command) throws IOException, DukeException, DateTimeParseException {
        TaskList taskList = new TaskList(task_list);
        if (command.equals("list")) {
            uI.displayTaskList(task_list);
        }
        else {
            switch (parser.checkCommand(command)) {
                case "done":
                    task_list = taskList.doneTask(command);
                    break;
                case "delete":
                    task_list = taskList.deleteTask(command);
                    break;
                default:
                    task_list = taskList.addTask(command);
                    break;
            }
        }
        storage.createFile(task_list);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        uI.hello();
        while (sc.hasNextLine()) {
            try{
                duke.getResponse(sc.nextLine());
            }
            catch (IOException ioException) {
                System.out.println(ioException);
            }
        }
    }

}
