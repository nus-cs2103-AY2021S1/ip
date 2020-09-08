package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Duke is the main class in this todo app.
 */
public class Duke {
    public TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor of Duke
     *
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
     * Loads tasks from saved text file.
     * Takes user input and execute until program terminates.
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
                handleUserInput(fullCommand, false);
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
     * Return response and take action according to the user input.
     *
     * @param userInput input from user, run tasks according to this.
     * @param loading indicates whether input is from saved data or user.
     */
    public String handleUserInput(String userInput, boolean loading) throws DukeException, IOException {
        String instructionType = Parser.parseInstruction(userInput);
        String response = "";
        assert instructionType != "" : "instructionType cannot be empty string";

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
            int index = Parser.parseMarkDoneInstr(userInput);

            // execute
            Task chosenTask = tasks.getTask(index);
            chosenTask.markAsDone();
            assert chosenTask.isDone : "task is not marked as done";
            if (!loading){
                response = ui.showMarkedDoneTask(chosenTask);
            }

        } else if (instructionType.equals("delete")) {
            // delete task
            // parse instruction
            int index = Parser.parseDeleteInstr(userInput);

            // execute
            Task chosenTask = tasks.getTask(index);
            tasks.deleteTask(index);
            assert !tasks.taskList.contains(chosenTask) : "task is not deleted deleted from taskList";
            if (!loading){
                response = ui.showDeletedTask(chosenTask, tasks.taskList);
            }

        } else if (instructionType.equals("find")) {
            // find task
            // parse instruction
            String keyword = Parser.parseFindInstr(userInput);

            // execute
            ArrayList<Task> foundTasks = tasks.find(keyword);
            if (!loading) {
                response = ui.showFoundTask(foundTasks);
            }

        } else if (instructionType.equals("todo")) {
            // make todo
            try {
                // parse instruction
                String description = Parser.parseAddTodoInstr(userInput);

                // execute
                Task todo = new Todo(description);
                tasks.addTask(todo);
                assert tasks.taskList.contains(todo) : "todo is not added to taskList";
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
                HashMap<String, Object> parsedData = Parser.parseAddDeadlineInstr(userInput);
                String description = (String) parsedData.get("description");
                LocalDate localTime = (LocalDate) parsedData.get("time");

                // execute
                Task deadline = new Deadline(description, localTime);
                tasks.addTask(deadline);
                assert tasks.taskList.contains(deadline) : "deadline is not added to taskList";
                if (!loading) {
                    response = ui.showAddedTask(deadline, tasks.taskList);
                }
            } catch (DukeException e) {
                response = ui.showError(e.getMessage());
            }

        } else if (instructionType.equals("event")) {
            // make event
            try {
                // parse instruction
                HashMap<String, Object> parsedData = Parser.parseAddEventInstr(userInput);
                String description = (String) parsedData.get("description");
                LocalDate localTime = (LocalDate) parsedData.get("time");

                // execute
                Task event = new Event(description, localTime);
                tasks.addTask(event);
                assert tasks.taskList.contains(event) : "event is not added to taskList";
                if (!loading) {
                    response = ui.showAddedTask(event, tasks.taskList);
                }
            } catch (DukeException e) {
                response = ui.showError(e.getMessage());
            }

        } else if (instructionType.equals("priority")) {
            // add priority to the task
            // ex) priority 1 to task 1 (priority levels are 1, 2, and 3)
            try {
                // parse instruction
                HashMap<String, Object> parsedData = Parser.parseSetPriorityInstr(userInput);
                Integer priorityLevel = (Integer) parsedData.get("priorityLevel");
                Integer taskIndex = (Integer) parsedData.get("taskIndex");

                // execute
                Task task = tasks.getTask(taskIndex);
                task.setPriorityLevel(priorityLevel);
                assert task.getPriorityLevel() != null : "priority is not added to the task";
                if (!loading) {
                    response = ui.showSetPriorityOfTask(task);
                }
            } catch (DukeException e) {
                response = ui.showError(e.getMessage());
            }

        } else {
            // invalid input
            if (!loading) {
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
        try {
            response = duke.handleUserInput(input, false);
        } catch (DukeException | IOException e) {
            response = duke.ui.showError(e.getMessage());
        }
        assert response != "" : "response should not be empty";
        return "Duke heard: \n"
                + response;
    }

    public Ui getUi() {
        return this.ui;
    }

}
