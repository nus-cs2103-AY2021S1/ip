package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

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
    public void user_input_handler(String user_input, boolean loading) throws DukeException, IOException {
        String instructionType = Parser.parseInstruction(user_input);

        if (instructionType.equals("bye")) {
            // quit
            ui.showGoodBye();
            System.exit(0);

        } else if (instructionType.equals("list")) {
            // list task
            ui.showListOfTask(tasks.taskList);

        } else if (instructionType.equals("done")) {
            // mark done
            // parse instruction
            int index = Parser.parseMarkDoneInstr(user_input);
            // execute
            Task chosenTask = tasks.getTask(index);
            chosenTask.markAsDone();
            if (!loading){
                ui.showMarkedDoneTask(chosenTask);
            }

        } else if (instructionType.equals("delete")) {
            // delete task
            // parse instruction
            int index = Parser.parseDeleteInstr(user_input);
            // execute
            Task chosenTask = tasks.getTask(index);
            tasks.deleteTask(index);
            if (!loading){
                ui.showDeletedTask(chosenTask, tasks.taskList);
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
                    ui.showAddedTask(todo, tasks.taskList);
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
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
                    ui.showAddedTask(deadline, tasks.taskList);
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
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
                    ui.showAddedTask(event, tasks.taskList);
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }

        } else {
            // invalid input
            if (!loading){
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        // save Data for every user input
        Storage.save(tasks.taskList);
    }
}
