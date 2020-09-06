package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

//javaFX
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is the main class in this todo app.
 */
public class Duke {
    private Storage storage;
    public TaskList tasks;
    private Ui ui;

    /**
     * constructor of Duke
     * @param filePath path where saved data of todo is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // need empty tasks to load properly
            tasks = new TaskList();
            tasks = new TaskList(storage.load(this));
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * load tasks from saved text file
     * takes user input and execute until program terminates
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                /*
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                 */
                user_input_handler(fullCommand, false);
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * run todo app
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * takes user input and execute tasks according to it
     * @param user_input input from user, run tasks according to this
     * @param loading indicates whether input is from saved data or user
     */
    public String user_input_handler(String user_input, boolean loading) throws DukeException, IOException {
        String instructionType = Parser.parseInstruction(user_input);
        String response = "";

        if (instructionType.equals("bye")) {
            // quit
            response = ui.showGoodBye();
            System.exit(0);

        } else if (instructionType.equals("list")) {
            // list task
            response = ui.showListOfTask(tasks.taskList);

        } else if (instructionType.equals("done")) {
            // mark done
            // parse instruction
            int index = Parser.parseMarkDoneInstr(user_input);
            // execute
            Task chosenTask = tasks.getTask(index);
            chosenTask.markAsDone();
            if (!loading){
                response = ui.showMarkedDoneTask(chosenTask);
            }

        } else if (instructionType.equals("delete")) {
            // delete task
            // parse instruction
            int index = Parser.parseDeleteInstr(user_input);
            // execute
            Task chosenTask = tasks.getTask(index);
            tasks.deleteTask(index);
            if (!loading){
                response = ui.showDeletedTask(chosenTask, tasks.taskList);
            }

        } else if (instructionType.equals("find")) {
            // find task
            // parse instruction
            String keyword = Parser.parseFindInstr(user_input);
            // execute
            ArrayList<Task> foundTasks = tasks.find(keyword);
            if (!loading){
                response = ui.showFoundTask(foundTasks);
            }

        } else if (instructionType.equals("todo")) {
            // make todo
            try {
                // parse instruction
                String description = Parser.parseAddTodoInstr(user_input);
                // execute
                Task todo = new Todo(description);
                tasks.addTask(todo);
                if (!loading){
                    response = ui.showAddedTask(todo, tasks.taskList);
                }
            } catch (DukeException e) {
                response = ui.showError(e.getMessage());
            }

        } else if (instructionType.equals("deadline")) {
            // make deadline
            try {
                // parse instruction
                HashMap<String, Object> parsedData = Parser.parseAddDeadlineInstr(user_input);
                String description = (String) parsedData.get("description");
                LocalDate l_time = (LocalDate) parsedData.get("time");
                // execute
                Task deadline = new Deadline(description, l_time);
                tasks.addTask(deadline);
                if (!loading) {
                    response = ui.showAddedTask(deadline, tasks.taskList);
                }
            } catch (DukeException e) {
                response = ui.showError(e.getMessage());
            }

        } else if (instructionType.equals("event")){
            // make event
            try {
                // parse instruction
                HashMap<String, Object> parsedData = Parser.parseAddEventInstr(user_input);
                String description = (String) parsedData.get("description");
                LocalDate l_time = (LocalDate) parsedData.get("time");
                // execute
                Task event = new Event(description, l_time);
                tasks.addTask(event);
                if (!loading){
                    response = ui.showAddedTask(event, tasks.taskList);
                }
            } catch (DukeException e) {
                response = ui.showError(e.getMessage());
            }

        } else {
            // invalid input
            if (!loading){
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        // save Data for every user input
        Storage.save(tasks.taskList);
        return response;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Duke duke = new Duke("data/duke.txt");
        String response = "";
        try{
            response = duke.user_input_handler(input, false);
        } catch (DukeException | IOException e) {
            response = duke.ui.showError(e.getMessage());
        }
        return "Duke heard: \n"
                + response;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Duke() {

    }
}
