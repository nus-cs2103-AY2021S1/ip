package duke;
import java.util.Scanner;

public class DukeMan {

    private Storage storage;
    private taskList tasks;
    private Ui ui;

    public DukeMan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new taskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new taskList();
        }
    }

    public void run() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");

        boolean isEnded = false;
        Scanner sc= new Scanner(System.in);

        while(!isEnded) {
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________");

            Parser parser = new Parser();
            parser.parsing(userInput);
            String command = parser.getCommand();

            System.out.println(command);

            switch(command) {
                case "todo":
                    String todoName = parser.getTaskName();
                    tasks.addTodo(todoName, true);
                    break;
                case "deadline":
                    String deadlineName = parser.getTaskName();
                    String deadline = parser.getTimeline();
                    tasks.addDeadline(deadlineName, deadline, true);
                    break;
                case "event":
                    String eventName = parser.getTaskName();
                    String timeline = parser.getTimeline();
                    tasks.addEvent(eventName, timeline, true);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isEnded = true;
                    break;
                case "list":
                    tasks.printList();
                    break;
                case "done":
                    int doneRank = Integer.parseInt(parser.getTaskName());
                    tasks.updateTaskStatus(doneRank, true);
                    break;
                case "remove":
                    int removeRank = Integer.parseInt(parser.getTaskName());
                    tasks.removeTask(removeRank - 1 );
                    break;
                default:
                    System.out.println("Please give an appropriate response.");
                    throw new DukeException("generic");

            }

            System.out.println("____________________________________________________________");
        }

    }

    public static void main(String[] args) {
        new DukeMan("src/main/memory.txt").run();
    }
}