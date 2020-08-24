import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (Exception e){
            Ui.showLoadingError();
        }
    }

    public void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        Ui.greeting();
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                Parser.parse(input, DukeCommandType.EXIT, tasks);
                break;
            } else if (input.equals("help")) {
                Parser.parse(input, DukeCommandType.HELP, tasks);
            } else if (input.equals("list")) {
                Parser.parse(input, DukeCommandType.LIST, tasks);
            } else if (input.startsWith("todo")) {
                Parser.parse(input, DukeCommandType.TODO, tasks);
            } else if (input.startsWith("deadline")) {
                Parser.parse(input, DukeCommandType.DEADLINE, tasks);
            } else if (input.startsWith("event")) {
                Parser.parse(input, DukeCommandType.EVENT, tasks);
            } else if (input.startsWith("done")) {
                Parser.parse(input, DukeCommandType.DONE, tasks);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("delete")) {
                Parser.parse(input, DukeCommandType.DELETE, tasks);
                input = sc.nextLine();
                continue;
            } else {
                Parser.parse(input, DukeCommandType.UNKNOWN, tasks);
                input = sc.nextLine();
                continue;
            }
            input = sc.nextLine();
        }
        storage.save(TaskList.tasks);
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
