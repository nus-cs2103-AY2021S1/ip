package duke;

import java.io.File;
import java.util.Scanner;

public class Duke {
    public TaskList tasks;

    public Duke() {
        this.tasks = new TaskList();
    }

    /**
     * op() is the main driver of Duke operations,
     * with a while loop that only terminates when user types bye;
     * Invoked in main()
     */
    public void op() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);

        while (!end) {
            String input = Ui.getInput(sc);
            String output_msg = "";
            if (Parser.isBye(input)) {
                end = true;
            } else if (Parser.isList(input)) {
                output_msg = tasks.summarize();
            } else if (Parser.isDone(input)) {
                output_msg = tasks.markDone(Parser.getIndex(input));
            } else if (Parser.isDelete(input)) {
                output_msg = tasks.deleteTask(Parser.getIndex(input));
            } else if (Parser.isFind(input)) {
                output_msg = tasks.findTasksWith(Parser.getKeyword(input));
            } else {
                Task taskInput;
                try {
                    taskInput = Parser.parseTask(input); // catch duke exception from getTask(input)
                } catch (Exception e) {
                    System.out.println(Formatter.formatResponse(e.getMessage()));
                    continue;
                }
                output_msg = tasks.addTask(taskInput);
            }
            if (!end) {
                FormatPrinter.print(output_msg);
            }
        }
        System.out.println(Formatter.formatResponse("Bye. Hope to see you again soon!\n"));
        sc.close();
    }

    /**
     * main() reads prevTasks.txt file and create
     * a Duke object with the tasks previously saved
     * Invokes op() function
     * After all are done, save undeleted tasks and exit.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String logo =
            "   __ _____   __  ___  ___  ___  ___\n"
            + "  / // / _ | / / / _ \\/ _ \\/ _ \\/ _ \\\n"
            + " / _  / __ |/ /__\\_, / // / // / // /\n"
            + "/_//_/_/ |_/____/___/\\___/\\___/\\___/\n";

        // Intro message
        System.out.println(logo);
        FormatPrinter.print(
            "Hello! I'm Hal9000\nWhat can I do for you?\n"
        );

        Duke hal9000 = new Duke();
        File prevTasks = FileOpener.openFile("prevTasks.txt");
        TaskLoader.loadTasks(prevTasks, hal9000.tasks);
        hal9000.op();
        TaskStorage.saveTask(prevTasks, hal9000.tasks);
    }
}
