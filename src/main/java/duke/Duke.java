package duke;

import ui.Ui;

import storage.Storage;

import tasklist.TaskList;

import parser.Parser;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    Duke(String filePath) {
        taskList = new TaskList();
        storage = new Storage(filePath, taskList);
        parser = new Parser(taskList, storage);
        ui = new Ui(parser);
        try {
            storage.getTodoList();
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Duke duke = new Duke("src/data/duke.txt");
        duke.run();
    }
}
