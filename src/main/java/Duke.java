import commands.Command;
import data.exception.DukeException;
import data.exception.DukeInvalidUserInputException;
import data.task.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Entry point of the Posh Duke Chat Bot.
 * Drives the entire process of Duke from start to end.
 */
public class Duke {

    private boolean isChatting;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke() {
    }

    public Duke(String file) {
        this.isChatting = true;
        this.ui = new Ui();
        this.taskList = new TaskList(new ArrayList<>());
        this.storage = new Storage(file);
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }

    /**
     * Runs Duke until termination.
     */
    private void startChat() {
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (DukeInvalidUserInputException e) {
            this.ui.showDukeError(e);
        }

        while (this.isChatting) { //isChatting is always true, System.exit() is used to terminate programme.
            String user_input = this.ui.getUserInput();
            try {
                Command user_command = this.parser.parseCommand(user_input);
                user_command.execute();
            } catch (DukeException e) {
                this.ui.showDukeError(e);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command user_command = this.parser.parseCommand(input);
            return user_command.execute();
        } catch (DukeException e) {
            return this.ui.showDukeError(e);
        }
    }

    public String initDuke() {
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (DukeInvalidUserInputException e) {
            return this.ui.showDukeError(e);
        }
        return Ui.showGreeting();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke("data/duke.txt");
        chatBot.startChat();
    }
}
