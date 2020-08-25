package duke;

import duke.parser.Parser;
import duke.storage.DeserializingException;
import duke.storage.FileMissingException;
import duke.storage.FileReadingException;
import duke.storage.TaskListStorage;
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

        final String savePath = "data/tasks.txt";
        final String createNewListMessage = "I'll create a new list of tasks.";
        TaskList list;
        TaskListStorage storage = new TaskListStorage(savePath);
        try {
            list = storage.open();
            ui.say(String.format("Loaded tasks from %s.", savePath));
        } catch (FileMissingException e) {
            ui.say(String.format("Couldn't find %s. %s", savePath, createNewListMessage));
            list = new TaskList();
        } catch (FileReadingException e) {
            ui.say(String.format("Couldn't read the file %s. %s", savePath, createNewListMessage));
            list = new TaskList();
        } catch (DeserializingException e) {
            ui.say(String.format("I don't understand the data in %s. %s", savePath,
                    createNewListMessage));
            list = new TaskList();
        }
        list.connectStorage(storage, (e) -> {
            ui.say(String.format("Couldn't save task list to %s!", savePath));
        });

        boolean stop = false;
        Scanner sc = new Scanner(System.in);
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
