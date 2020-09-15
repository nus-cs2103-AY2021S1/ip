package model;

import java.util.ArrayList;
import java.util.Scanner;

import commands.Command;
import data.exception.DukeException;
import data.exception.DukeInvalidUserInputException;
import data.task.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

/**
 * Over seeing class of the DukeBunny Chat Bot.
 * Drives the entire process of Duke from start to end behind the GUI.
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke() {
    }

    /**
     * Constructs a model of Duke with a specified file path.
     * @param filePath file path.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList(new ArrayList<>());
        this.storage = new Storage(filePath);
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public ResponseResult getResponse(String userInput) {
        try {
            Command userCommand = this.parser.parseCommand(userInput);
            return new ResponseResult(false, userCommand.execute());
        } catch (DukeException e) {
            return new ResponseResult(true, this.ui.showDukeError(e));
        }
    }

    /**
     * Initialises model.Duke.
     * @return welcome message.
     */
    public String initDuke() {
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (DukeInvalidUserInputException e) {
            return this.ui.showDukeError(e);
        }
        return Ui.showGreeting();
    }

    private void run() {
        System.out.println(initDuke());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            System.out.println(getResponse(userInput).getMsg());
        }
    }

    /**
     * Used for running duke in a terminal.
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/TaskList.txt");
        duke.run();
    }
}
