package duke;

import duke.parser.Parser;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<Task> list = new ArrayList<>();
        while (!stop) {
            String input = sc.nextLine();
            BotClass bot = new BotClass();
            Parser.parse(input).execute(bot, list);
            ui.say(bot.getLines());
            if (bot.stopped()) {
                stop = true;
            }
        }
        sc.close();
    }
}
