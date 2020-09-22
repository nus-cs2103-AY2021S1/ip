package data;

import command.Command;
import command.CommandType;
import exception.InvalidInputException;
import ui.Ui;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskList object contains a list of Task objects that are added and edited
 * by the user. This object has a Storage object to manage saving and
 * loading of data.
 *
 * @author Hakiem Rasid
 */
public class TaskList {

    private final Storage storage;
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList object.
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
            StringBuilder sb = new StringBuilder();
            System.out.println(Ui.horizontalLine());
            try {
                String input = sc.nextLine();
                System.out.println(Ui.horizontalLine());
                Command cmd = parser.parseCommand(input);
                this.list = cmd.executeCommand(this.list, sb);
                if (cmd.getType().equals(CommandType.BYE)) {
                    // exit program if user inputs "bye"
                    break;
                }
            } catch (InvalidInputException e) {
                sb.append(e.getMessage() + "\n");
                sb.append(Ui.invalidInputMessage());
            } catch (IndexOutOfBoundsException obe) {
                sb.append(Ui.invalidIndexMessage());
            } finally {
                System.out.println(sb.toString());
            }
        } // end while loop
    }

    public String runSingleCommand(String input) {
        Parser parser = new Parser();
        StringBuilder sb = new StringBuilder();
        try {
            Command cmd = parser.parseCommand(input);
            this.list = cmd.executeCommand(this.list, sb);
        } catch (InvalidInputException e) {
            sb.append(e.getMessage());
            sb.append("\n");
            sb.append(Ui.invalidInputMessage());
        } catch (IndexOutOfBoundsException obe) {
            sb.append(Ui.invalidIndexMessage());
        }
        return sb.toString();
    }

    /**
     * Returns List of Task objects.
     * @return List of Task objects.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Saves list of Task objects onto specified txt file.
     */
    public void save() {
        storage.saveData(this.list);
    }
}