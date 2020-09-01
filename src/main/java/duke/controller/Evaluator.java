package duke.controller;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.AlertBox;
import duke.ui.DataSaver;
import duke.ui.Messenger;
import duke.utils.Parser;
import duke.utils.Storage;
import javafx.application.Platform;


public class Evaluator {
    private Storage storage;

    /**
     * Constructs an Evaluator object that interacts with the command input.
     *
     * @param storage the storage the evaluator uses to manipulate tasks.
     */
    public Evaluator(Storage storage) {
        this.storage = storage;
    }

    /**
     * Evaluates the command passed through the input text area.
     *
     * @param command a string representing the command passed in.
     * @return a string representing the response from the evaluator.
     */
    public String getResponse(String command) {
        // initialize utilities
        Parser parser = new Parser();
        TaskList list = storage.getTaskList();

        if (command.equals("bye")) {
            DataSaver.save(storage);
            if (DataSaver.isQuitting()) {
                Platform.exit();
            }
            return "";
        }

        if (command.equals("list")) {
            return list.printList();
        }

        try {
            String[] actionExtracted = parser.extractAction(command);
            String status = actionExtracted[0];
            String body = actionExtracted[1];
            switch (status) {
            case "done":
                return list.markTaskAsDone(Integer.parseInt(body));
            case "todo":
                return list.addTask(body, status);
            case "delete":
                return list.deleteTask(Integer.parseInt(body));
            case "find":
                return list.findTask(body);
            default:
                String[] timeExtracted = parser.extractDate(body);
                String content = timeExtracted[0];
                String time = timeExtracted[1];
                return list.addTask(content, status, time);
            }
        } catch (NumberFormatException e) {
            AlertBox.display("Index not valid!", Messenger.INDEX_FORMAT_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            AlertBox.display("Duke does not understand!", Messenger.COMMAND_UNRECOGNIZABLE_ERROR);
        } catch (DukeException e) {
            AlertBox.display("Duke runs into a problem!", e.getMessage());
        } catch (Exception e) {
            AlertBox.display("Something went wrong!", e.toString());
        }
        // error in evaluating
        return "What???";
    }
}
