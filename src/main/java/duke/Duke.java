package duke;

import duke.command.Command;

public class Duke {


    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList(storage.readFile());
    }


    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = new Parser().parse(userInput);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printString(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke("/data/duke.txt");
        duke.run();
    }


}
