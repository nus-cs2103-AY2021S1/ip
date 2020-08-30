package duke;

import duke.parser.Parser;
import duke.storage.TaskListStorage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * This is the main class. Start Duke by running the main method.
 */
public class Duke implements Bot {
    private boolean isStopped;
    private Ui ui;
    private StringBuilder message;

    private Duke() {
        isStopped = false;
        ui = new Ui();
        message = new StringBuilder();
    }

    /**
     * Starts Duke.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        sayLine(logo);
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskListStorage("data/tasks.txt").load(this);
        sayLine("Hello, I'm Duke. What can I do for you?");
        flushMessage();
        while (!isStopped) {
            String input = sc.nextLine();
            Parser.parse(input).execute(this, list);
            flushMessage();
        }
        sc.close();
    }

    @Override
    public void stop() {
        isStopped = true;
    }

    @Override
    public void sayLine(String string) {
        if (message.length() != 0) {
            message.append("\n");
        }
        message.append(string);
    }

    private void flushMessage() {
        ui.say(message.toString());
        message = new StringBuilder();
    }
}
