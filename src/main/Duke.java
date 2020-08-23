package main;

import java.io.IOException;
import java.util.Scanner;

import main.ui.Ui;
import main.task.TaskList;
import main.storage.Storage;
import main.command.Command;
import main.parser.Parser;
import main.exception.DukeException;

public class Duke {
    private final Ui ui;
    private final Scanner sc;
    private final TaskList tasks;
    private boolean hasCommand;

    public Duke() {
        sc = new Scanner(System.in);
        hasCommand = false;
        tasks = new TaskList();
        ui = new Ui();
    }

    public void initialise() {
        try {
            hasCommand = true;
            ui.printGreeting();
            Storage.setTasks(tasks);

            while (hasCommand) {
                try {
                    String[] input = ui.getInput(sc);
                    Command command = Parser.parse(input);
                    command.execute(ui, tasks);

                    hasCommand = command.hasCommand();
                } catch (DukeException e) {
                    ui.printExceptionMessage(e);
                }
            }

            Storage.write(tasks);
        } catch (IOException e) {
            ui.printError();
        } finally {
            ui.printExit();
        }
    }

    public static void main(String[] args) {
        new Duke().initialise();
    }
}
