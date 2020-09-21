package data;

import command.Command;
import command.CommandType;
import exception.InvalidInputException;
import dukemain.Ui;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * data.TaskList object contains a list of Task objects that are added and edited
 * by the user. This object has a data.Storage object to manage saving and
 * loading of data.
 *
 * @author Hakiem Rasid
 */
public class TaskList {

    private Storage storage;
    private ArrayList<Task> list;

    /**
     * Constructor for data.TaskList object.
     * @param filePath Destination file for saving and loading of data.
     */
    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        this.list = storage.loadData();
    }

    /**
     * Reads input from user and executes the appropriate commands to manipulate
     * the list of Task objects or provide instructions to the program.
     */
    public void runCommands() {
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        while (true) {
            Ui.horizontalLine();
            try {
                String input = sc.nextLine();
                Ui.horizontalLine();
                Command cmd = parser.parseCommand(input);
                this.list = cmd.executeCommand(this.list);
                if (cmd.getType().equals(CommandType.BYE)) {
                    // exit program if user inputs "bye"
                    break;
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                Ui.invalidInputMessage();
            } catch (IndexOutOfBoundsException obe) {
                Ui.invalidIndexMessage();
            }
        }
    }

    // Returns list of Tasks

    /**
     * Returns List of Task objects.
     * @return List of Task objects.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Saves list of Task obejcts onto specified txt file.
     */
    public void save() {
        storage.saveData(this.list);
    }
}