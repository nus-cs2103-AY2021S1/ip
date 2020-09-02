package dukechatbot.ui;

import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.parser.CommandParser;
import dukechatbot.command.Command;
import dukechatbot.constant.DukeConstants;
import dukechatbot.storage.Storage;
import dukechatbot.tasklist.TaskList;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents Ui where the input is parsed and 
 * the appropriate command is executed and output is given.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    private final TaskList taskList = new TaskList(Storage.load());

    private boolean isOn = true;

    /**
     * Runs the while loop of Duke until exit input given.
     */
    public void run() {
        printGreetingMessage();
        while (isOn) {
            if (scanner.hasNextLine()) {
                this.handleInput();
            }
        }
    }
    
    private void handleInput() {
        String input = this.getInput();
        if (CommandParser.isExit(input)) {
            this.exit();
        } else {
            this.respond(input);
        }
    }

    private void exit() {
        DukeOutput.output(DukeConstants.EXIT_RESPONSE);
        Storage.save(this.taskList.getList());
        this.isOn = false;
    }

    private void respond(String input) {
        try {
            Command command = CommandParser.parse(input);
            if (Objects.isNull(command)) {
                this.handleInvalidInput();
            } else {
                command.getCommandExecutor().execute(command, taskList);
            }
        } catch (IndexOutOfBoundsException exception) {
            DukeOutput.output(exception.getMessage());
        }
    }

    private void handleInvalidInput() {
        DukeOutput.output("\u2639 OOPS!!! I'm sorry,"
                + " but I don't know what that means :-(");
    }

    private void printGreetingMessage() {
        DukeOutput.outputGreeting();
        DukeOutput.output(DukeConstants.INITIAL_RESPONSE);
    }

    private String getInput() {
        return scanner.nextLine();
    }
}
