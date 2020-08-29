package duke;

import duke.commands.Commands;
import duke.dukehelper.Parser;
import duke.dukehelper.Storage;
import duke.dukehelper.TaskList;
import duke.dukehelper.Ui;
import duke.exception.DukeException;
import duke.helper.DateTimeHelper;
import duke.task.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Pane root = new Pane();
    private Scene scene;


    /**
     * Constructor
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/save_file.txt");
        this.tasks = new TaskList();
        this.parser = new Parser();
    }
    private int getNumTasks() {
        return this.tasks.getTaskList().size();
    }

    /**
     * Distributes each command based on its type, then return a string that will be shown to the user.
     * @param commandType
     * @param tokens
     * @param isLoaded
     * @return message to user
     * @throws DukeException
     */
    protected String furtherProcessing(Commands commandType, String[] tokens, boolean isLoaded) throws DukeException {
        Task parsedTask = new Task("");
        if (commandType == Commands.DEADLINE || commandType == Commands.EVENT || commandType == Commands.TODO) {
            parsedTask = parser.parseCommand(commandType, tokens, isLoaded, getNumTasks());
        } else if (commandType == Commands.DELETE) {

            int markNumber = Integer.parseInt(tokens[1]);
            storage.saveTasks(this.tasks.getTaskList());
            String result = tasks.deleteTask(markNumber, getNumTasks());
            storage.saveTasks(this.tasks.getTaskList());
            return result;

        } else if (commandType == Commands.DONE) {

            int markNumber = Integer.parseInt(tokens[1]);
            String result = tasks.doneTask(markNumber);
            storage.saveTasks(this.tasks.getTaskList());
            return result;

        }  else if (commandType == Commands.LIST) {

            String[] extractedData = parser.extractData(isLoaded, tokens);
            String content = extractedData[0];
            DateTimeHelper dtHelper = DateTimeHelper.processDateTime(content);
            if (dtHelper != null) {
                LocalDate deadline = dtHelper.getDate();
                return tasks.filteredTaskList(deadline);
            } else {
                throw new DukeException("Wrong format\n   "
                        + " Your date and time(optional) should be in this format:\n      "
                        + "yyyy-mm-dd HHmm\n    e.g: 2019-10-15 1800 or 2019-10-15");
            }

        } else if (commandType == Commands.FIND) {
            return tasks.findTasks(tokens);
        } else if (commandType == Commands.BYE || commandType == Commands.HELP) {
            throw new DukeException("Wrong format of command " + "'" + commandType.getAction() + "'");
        }

        if (!parsedTask.getContent().equals("")) {
            this.tasks.addTask(parsedTask);
        }
        storage.saveTasks(this.tasks.getTaskList());
        return parsedTask.getUiOutput();
    }

    /**
     * Strips trailing whitespaces and tokenizes each command before further processing
     * @param command
     * @param isLoaded
     * @return message to user
     * @throws DukeException
     */
    protected String processedCommand(String command, boolean isLoaded) throws DukeException {
        command = command.strip();
        if (command.equals("")) return "";
        String[] tokens = command.split(" ");
        try {
            return furtherProcessing(Commands.valueOf(tokens[0].toUpperCase()), tokens, isLoaded);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! CAN YOU PLEASE TYPE SOMETHING MEANINGFUL?");
        }
    }

    /**
     * Reads input and return output to the user
     */
    protected void run() {
        Ui.printDialog("Hello! I'm the Riddler. Type 'help' if you know nothing HAHAHA\n    "
                + "Your tasks will be saved at /data\n    What can WE do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> savedTasks = storage.loadSavedTasks();
        if (savedTasks.size() > 0 && savedTasks.get(0).equals("000")) {
            Ui.printDialog("This is the first time you use Duke!");
        } else {
            try {
                for (String task : savedTasks) {
                    processedCommand(task, true);
                }
            } catch (DukeException e) {
                Ui.printDialog("Something wrong happened while loading saved tasks");
            }
        }
        while (true) {
            String content = sc.nextLine();
            content = content.strip();
            if (content.equals(Commands.BYE.getAction())) {
                Ui.printDialog("Bye. Hope to see you again soon!");
                break;//exit the program
            }
            if (content.equals(Commands.HELP.getAction())) {
                String res = "";
                for(Commands command: Commands.values()) {
                    res += command.getAction() + ": " + command.getDescription();
                    res += "\n    ";
                }
                Ui.printDialog(res);
            } else if (content.equals(Commands.LIST.getAction())) {
                Ui.printStoredTasks(this.tasks.getTaskList());
            } else {
                try {
                    String result = processedCommand(content, false);
                    if (!result.equals("")) Ui.printDialog(result);
                } catch (DukeException e) {
                    Ui.printDialog(e.getMessage());
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.initChatBox();
        Label label1 = new Label("Command:");
        TextField textField = new TextField();
        root.getStylesheets().add(getClass().getResource("../style/chatbox.css").toExternalForm());
        root.getChildren().addAll(ui.getContainer(),ui.getAdd(), label1, textField);
        scene = new Scene(root,300,450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.run();
    }
}

