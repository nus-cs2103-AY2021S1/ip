import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Duke class is the main driver class,
 * where the commands are being processed into actions.
 */
public class Duke {

    public static Ui uI;
    private static Parser parser;
    private static Storage storage;
    private static ArrayList<Task> task_list = new ArrayList<>();

    /**
     * Construct a new Duke instance.
     */
    public Duke() {
        uI = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    /**
     * Function to generate an action based on user input.
     * @param input Command entered by the user
     * @throws IOException If the file is corrupted or some error occurred during reading the data.
     */
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


    /**
     * Parses the input entered by the user.
     * @param command Command entered by the user.
     * @throws IOException If the file is corrupted or some error occurred during reading the data
     * @throws DukeException If the command is invalid or the task enquired doesn't exists.
     * @throws DateTimeParseException If the date of the deadline or event is not formatted properly.
     */
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


    /**
     * main method that is being executed when the Duke program is run
     * @param args Input entered in the command line
     */
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
