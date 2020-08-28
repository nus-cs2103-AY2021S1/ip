package duke.components;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.views.Messenger;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Evaluator {
    private Storage storage;
    private Label label;
    private Stage window;

    /**
     * Constructs an Evaluator object that interacts with the command input.
     *
     * @param storage the storage the evaluator uses to manipulate tasks.
     * @param label the Label object which the evaluator should manipulate.
     * @param window the Stage object which the evaluator operates on.
     */
    public Evaluator(Storage storage, Label label, Stage window) {
        this.storage = storage;
        this.label = label;
        this.window = window;
    }

    /**
     * Evaluates the command passed through the input text area.
     *
     * @param command a string representing the command passed in.
     */
    public void handle(String command) {
        // initialize utilities
        Parser parser = new Parser();
        TaskList list = storage.getTaskList();

        if (command.equals("bye")) {
            DataSaver.save(storage);
            if (DataSaver.isQuitting()) {
                window.close();
            }
            return;
        }

        if (command.equals("list")) {
            label.setText(list.printList());
            return;
        }

        try {
            String[] actionExtracted = parser.extractAction(command);
            String status = actionExtracted[0];
            String body = actionExtracted[1];
            switch (status) {
            case "done":
                label.setText(list.markTaskAsDone(Integer.parseInt(body)));
                break;
            case "todo":
                label.setText(list.addTask(body, status));
                break;
            case "delete":
                label.setText(list.deleteTask(Integer.parseInt(body)));
                break;
            case "find":
                list.findTask(body);
                break;
            default:
                String[] timeExtracted = parser.extractDate(body);
                String content = timeExtracted[0];
                String time = timeExtracted[1];
                label.setText(list.addTask(content, status, time));
                break;
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
    }
}
