import java.util.Arrays;
import java.util.Scanner;

/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various tasks.
 * Contains static attribute stored_task which stores task input from user.
 **/
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private final String line = "____________________________________________________________";

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
    }

    public void run() {
        boolean isInProgram = true;
        String greeting_message = line +
                "\n Quack! I am Duck" +
                "\n How can I help you today?\n" + line;
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting_message);
        while (isInProgram) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parse(input);
                command.execute(taskList);
                isInProgram = command.isInProgram();
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
        sc.close();
    }

    /**
     * Prints greeting message.
     * Scans for commands entered by the user, then stores input task into stored_task for 3 types of tasks:
     * ToDo, Deadline and Event.
     * Upon user command input "done " followed by the task number, task will be marked as done.
     * Upon user command input "list", stored task will be listed.
     * Upon user command input "bye", system is exited.
     * Upon user command input "delete", task is deleted.
     **/
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
