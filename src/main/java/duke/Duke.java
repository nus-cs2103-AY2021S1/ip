package main.java.duke;

import main.java.duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    Duke(String filepath) {
        try {
            this.storage = new Storage(filepath);
            this.taskList = this.storage.readTasks();
            this.ui = new Ui();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parse(this.ui.readCommand());
                if (command != null) {
                    command.execute(this.taskList, this.ui, this.storage);
                    isExit = command.isDone();
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String filepath = "/data/taskList.txt";
        Duke duke = new Duke(filepath);
        duke.run();
    }
}
