package duke.components;

import duke.exceptions.DukeException;

import java.io.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws FileNotFoundException, DukeException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser();

    }

    public void run() throws IOException {

        System.out.println("Hi, this is duke.components.Duke, what can I do for you?");

        parser.parse(ui.waitForNextInput());
        while(!parser.isBye) {

            if (parser.isList) {

                ui.printTaskList(tasks.getMyList());

            } else if (parser.isDone) {

                ui.printDoneTask(
                        tasks.finishTaskNum(
                                parser.getDoneTaskNum()
                        )
                );

            } else if (parser.isTask) {

                ui.printAddTask(
                        tasks.addTask(parser.getDescription(), parser.getDate(), parser.getTime()),
                        tasks.getMyList()
                );

            } else if (parser.isDelete) {

                ui.printDeleteTask(tasks.deleteTask(parser.getDeleteTaskNum()), tasks.getMyList());

            } else if (parser.isValid) {

                ui.printNotValid();

            }
            parser = new Parser();
            parser.parse(ui.waitForNextInput());
            storage.overwriteWith(tasks.getMyList());

        }
        ui.printBye();

    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("duke.txt").run();
    }
}
