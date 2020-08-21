package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
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
            route(input).execute(bot, list);
            ui.say(bot.getLines());
            if (bot.stopped()) {
                stop = true;
            }
        }
        sc.close();
    }

    private Command route(String input) {
        input = input.strip();
        String[] split = input.split("\\s+", 2); // guranteed to contain at least ""
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";

        try {
            if (command.equals("bye")) {
                ensureNoArgs(args, command);
                return new ByeCommand();
            } else if (command.equals("list")) {
                ensureNoArgs(args, command);
                return new ListCommand();
            } else if (command.equals("done")) {
                int index = parseTaskNumber(args, "you have completed", "done 1") - 1;
                return new DoneCommand(index);
            } else if (command.equals("delete")) {
                int index = parseTaskNumber(args, "you want to remove", "delete 1") - 1;
                return new DeleteCommand(index);
            } else if (command.equals("todo")) {
                if (args.isEmpty()) {
                    return new ErrorCommand(
                            "Couldn't add todo! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Task(args));
            } else if (command.equals("deadline")) {
                String[] argsSplit = parseDeadlineEventArgs(args, "/by", command,
                        "deadline <description> /by <date>");
                return new AddCommand(new Deadline(argsSplit[0], argsSplit[1]));
            } else if (command.equals("event")) {
                String[] argsSplit = parseDeadlineEventArgs(args, "/at", command,
                        "event <description> /at <date>");
                return new AddCommand(new Event(argsSplit[0], argsSplit[1]));
            } else if (input.isBlank()) {
                return new ErrorCommand("You need to tell me what you want me to do!");
            } else {
                return new ErrorCommand("Sorry, I don't understand that!");
            }
        } catch (DukeRoutingException e) {
            return new ErrorCommand(e.getDukeMessage());
        }
    }

    // helpers for route function

    private void ensureNoArgs(String args, String commandName) throws DukeRoutingException {
        if (!args.isEmpty()) {
            throw new DukeRoutingException(
                    String.format("I don't understand that. Did you mean %s?", commandName));
        }
    }

    private int parseTaskNumber(String args, String taskDescription, String example)
            throws DukeRoutingException {
        try {
            return Integer.valueOf(args);
        } catch (NumberFormatException e) {
            throw new DukeRoutingException(
                    Arrays.asList(String.format("You need to tell me the number of the task %s.",
                            taskDescription), "Eg. " + example));
        }
    }

    private String[] parseDeadlineEventArgs(String args, String splitAround, String taskType,
            String example) throws DukeRoutingException {
        String[] argsSplit = args.split("\\s+" + splitAround + "\\s+", 2);
        if (argsSplit.length != 2 || argsSplit[0].isBlank() || argsSplit[1].isBlank()) {
            String n = taskType.matches("^a|e|i|o|u") ? "n" : ""; // starts with vowel
            throw new DukeRoutingException(Arrays
                    .asList(String.format("Couldn't add %s! To add a%s %s, talk to me using the",
                            taskType, n, taskType), "format: " + example));
        }
        return argsSplit;
    }

}
