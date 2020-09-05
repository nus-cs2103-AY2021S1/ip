package duke;

import duke.storage.Storage;
import duke.storage.Storage.StorageOperationException;
import duke.ui.Parser;
import duke.ui.Ui;
import duke.command.Command;

import java.util.List;

import static duke.storage.Storage.DEFAULT_STORAGE_FILEPATH;


public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = storage.loadTasks();
            ui = new Ui();
            parser = new Parser(taskList, ui);
        } catch (StorageOperationException e ) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        String input = ui.getUserInput();
            while (!input.equals("bye")) {
            try {
                Command command = parser.getCommandFromInput(input);
                command.execute();
            } catch (DukeException e) {
                ui.outputBlockToUser(e.getMessage());
            }
            input = ui.getUserInput();
        }
        ui.showGoodbyeMessage();
        try {
            storage.saveTasks(taskList);
        } catch (StorageOperationException soe) {
            System.out.println(soe.getMessage());
        }
    }

    public static void main(String[] args) {

        new Duke(DEFAULT_STORAGE_FILEPATH).run();

    }


    private static int checkDoneDeleteException(String[] inputList, TaskList taskList) {
        if (inputList.length < 2)
        {
            throw new DukeException("☹ BLEHHHHHH. Tell me which task??");
        }
        int index = Integer.parseInt(inputList[1]) - 1;
        if (index < 0 || index > taskList.getSize() - 1) {
            throw new DukeException(String.format("☹ BLEHHHHHH. duke.Task no. %d does not exist. Please try again.", (index + 1)));
        }
        return index;
    }


    private static int getRemainingTaskCount(List<Task> taskList)
    {
        return (int) taskList.stream().filter(x -> !x.isDone()).count();
    }

    private static void printTaskList(TaskList taskList) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.print("    " + (i+1) + ". ");
            System.out.println(taskList.get(i));
        }
    }

}