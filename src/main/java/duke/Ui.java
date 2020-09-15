package duke;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

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

        //Start the Scanner for user input.
        Scanner scan = new Scanner(System.in);

        String intro = "-------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "Here are some of the commands i take:\n"
                + "list - to view a list of your current tasks\n"
                + "todo <task> - to add a todo to your task list\n"
                + "deadline <task> /by <date and time (YYYY-MM-DD TTTT)> - to add a deadline to your task list\n"
                + "event <task> /by <date and time (YYYY-MM-DD TTTT)> - to add an event to your task list\n"
                + "done <index of task to be deleted> - to mark a task as done\n"
                + "delete <index of task to be deleted> - to delete a specific task\n"
                + "bye - to exit the program\n"
                + "Also, to make a task recurring, simply add \"recurring\" after todo/deadline/event\n"
                + "-------------------------";
        System.out.println(intro);

        String next = scan.nextLine();
        Storage store = storage;
        TaskList tasks = new TaskList(store.load());
        Parser parser = new Parser(tasks);

        while (next != null) {
            assert next.contains("todo") | next.contains("deadline")
                    | next.contains("event") : "Please provide a valid keyword";

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


    /**
     * Returns the intro message as a string.
     * @return the intro message
     */
    public String introMessage() {
        String intro = "-------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "Here are some of the commands i take:\n"
                + "list - to view a list of your current tasks\n"
                + "todo <task> - to add a todo to your task list\n"
                + "deadline <task> /by <date and time (YYYY-MM-DD TTTT)> - to add a deadline to your task list\n"
                + "event <task> /by <date and time (YYYY-MM-DD TTTT)> - to add an event to your task list\n"
                + "done <index of task to be deleted> - to mark a task as done\n"
                + "delete <index of task to be deleted> - to delete a specific task\n"
                + "bye - to exit the program\n"
                + "Also, to make a task recurring, simply add \"recurring\" after todo/deadline/event\n"
                + "-------------------------";
        return intro;
    }
}
