package duke;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

// TODO: setup code style check
// TODO: consider whether Commands should throw Exceptions, or stay as they are now
// TODO: make Duke implement Bot

public class Duke {
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        Ui ui = new Ui();

        String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        ui.say("Hello, I'm Duke. What can I do for you?");

        boolean stop = false;
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        while (!stop) {
            String input = sc.nextLine();
            BotClass bot = new BotClass();
            Parser.parse(input).execute(bot, list);
            ui.say(bot.getMessage());
            if (bot.stopped()) {
                stop = true;
            }
        }
        sc.close();
    }
}
