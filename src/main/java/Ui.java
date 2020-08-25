import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Storage storage;
    private TaskList tasks;

    /**
     * Class Constructor with no arguments.
     */
    public Ui() {}

    /**
     * Starts the User Interface to accept user input.
     * @param storage the Storage used to store Tasks
     * @throws DukeException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void start(Storage storage) throws DukeException, IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        String intro = "-------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "-------------------------";
        System.out.println(intro);
        String next = scan.nextLine();
        Storage store = storage;
        TaskList tasks = new TaskList(store.load());
        Parser parser = new Parser(tasks);
        while (next != null) {
            if (next.equals("bye")) {
                System.out.println("-------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------");
                scan.close();
                Storage.store(tasks.getList());
                break;
            }
            parser.parse(next);
            next = scan.nextLine();
        }
    }
}

