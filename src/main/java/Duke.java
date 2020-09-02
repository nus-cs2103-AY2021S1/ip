import Duke.Ui;
import Duke.Parser;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import Duke.DukeException;

import java.io.IOException;

public class Duke {
    /**
     * Main handles starts the bot and handles the logic.
     *
     * @param args
     */
    public static void main(String[] args) {
        //Destination
        String dest = "data";
        String filename = "duke.txt";

        Ui ui = new Ui();
        Parser parser = new Parser();

        // Show Welcome
        ui.showWelcome();

        try {
            // Initialise Storage
            Storage data = new Storage(dest, filename);

            // Creates list
            TaskList list = new TaskList(data.load());

            // Start process
            boolean exit = false;

            while (!exit) {
                try {
                    String input = ui.readLine();
                    Parser.Mode mode = parser.mode(input);
                    if (mode == Parser.Mode.TODO || mode == Parser.Mode.DEADLINE ||
                            mode == Parser.Mode.EVENT) {
                        // Create and add task to task list
                        Task task = parser.task(input);
                        list.add(task);

                        // Write data
                        data.addData(task);

                        // System output
                        ui.showAddTask(task);
                        ui.showTotalTasks(list.getSize());

                    } else if (mode == Parser.Mode.DONE) {
                        // Mark Task as Done
                        int order = parser.order(input);
                        Task task = list.markDone(order);
                        data.markDoneData(order);
                        ui.showDoneTask(task);

                    } else if (mode == Parser.Mode.DELETE) {
                        // Delete Task
                        int order = parser.order(input);
                        Task task = list.remove(order);
                        data.deleteData(order);
                        ui.showRemovedTask(task);
                        ui.showTotalTasks(list.getSize());

                    } else if (mode == Parser.Mode.LIST) {
                        // List all tasks
                        System.out.print(list.listToString());

                    } else if (mode == Parser.Mode.BYE) {
                        // Exit now
                        exit = true;
                    } else if (mode == Parser.Mode.FIND) {
                        // Find tasks
                        String subName = parser.name(input);
                        String result = list.find(subName);
                        ui.showFindTask(result);
                    }

                } catch (DukeException e) {
                    ui.showError(e);
                }
            }
        } catch (IOException | DukeException e) {
            ui.showError(e);
        } finally {
            ui.goodbye();
            ui.close();
        }
    }
}