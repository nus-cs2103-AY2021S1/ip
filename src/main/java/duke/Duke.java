package duke;

import java.util.Scanner;

import duke.command.CommandType;
import duke.command.ResetCommand;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Starts Duke which a user can give
 * text commands to.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    public Duke() {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage();
        taskList = new TaskList();
    }

    /**
     * Creates a Duke object
     *
     * @param filePath  Location of file where data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(filePath);
        taskList = TaskList.generateTaskList(storage);
    }

    /**
     * Starts the Duke application.
     *
     * @param args Array of command-line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/tasks.txt");
        duke.start();
        duke.runUntilExitCommand();
        duke.exit();
    }

    private void start() {
        ui.start(taskList);
    }

    private void runUntilExitCommand() {
        boolean isExitCommand = false;
        boolean isFirstCommand = true;
        while (!isExitCommand) {
            if (isFirstCommand) {
                isFirstCommand = false;
            } else {
                ui.printAdditionActionMessage();
            }
            String input = ui.readCommand();
            Command command;
            try {
                ui.printDivider();
                command = Parser.parse(input);
                CommandType commandType = command.getCommandType();
                switch (commandType) {
                case ADD:
                    command.execute(ui, taskList);
                    command = Parser.parseTaskType(input);
                    assert command.getCommandType().equals(CommandType.TASKTYPE)
                            : "Command type should be TASKTYPE";
                    command.execute(ui, taskList);
                    command = Parser.parseTask(input, command);
                    assert command.getCommandType().equals(CommandType.TASK)
                            : "Command type should be TASK";
                    command.execute(ui, taskList);
                    break;
                case DELETE:
                    command.execute(ui, taskList);
                    command = Parser.parseDelete(input);
                    assert command.getCommandType().equals(CommandType.DELETETASK)
                            : "Command type should be DELETETASK";
                    command.execute(ui, taskList);
                    break;
                case DONE:
                    command.execute(ui, taskList);
                    command = Parser.parseDone(input);
                    assert command.getCommandType().equals(CommandType.DONETASK)
                            : "Command type should be DONETASK";
                    command.execute(ui, taskList);
                    break;
                case FIND:
                    command.execute(ui, taskList);
                    command = Parser.parseFind(input);
                    assert command.getCommandType().equals(CommandType.FINDTASK)
                            : "Command type should be FINDTASK";
                    command.execute(ui, taskList);
                    break;
                default:
                    command.execute(ui, taskList);
                }
                isExitCommand = command.isExitCommand();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            }
        }
    }

    private void exit() {
        ui.printGoodbyeMessage();
    }

    /**
     * Gets response for a user input.
     *
     * @param input User input.
     * @return Returns a Duke response as a String.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            Parser.setPrevCommand(command);
            return command.execute(ui, taskList);
        } catch (DukeException e) {
            Parser.setPrevCommand(new ResetCommand());
            return ui.displayError(e.getMessage());
        }
    }
}
