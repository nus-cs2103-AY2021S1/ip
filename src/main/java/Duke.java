import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The main class that directs the required actions to the respective classes.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this("data/duke.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the reply for the respective commands after processing them
     * @param processedCommand
     * @return the reply for the respective commands
     * @throws DukeException if user inputs invalid commands
     */
    public String generateReply(String[] processedCommand) throws DukeException {
        switch (processedCommand[0].toLowerCase()) {
        case "bye":
            return ui.showByeMessage();
        case "list":
            return ui.showListTasks(tasks.getAllTasks()) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "print":
            return ui.showRequiredTasks(tasks.getSameDateTasks(processedCommand[1]))
                    + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "done":
            Task doneTask = tasks.markDone(Integer.parseInt(processedCommand[1]));
            return ui.showDoneTask(doneTask) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "delete":
            Task deletedTask = tasks.deleteTask(Integer.parseInt(processedCommand[1]));
            return ui.showDeleteTask(deletedTask) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "todo":
            Task todoTsk = new Todo(processedCommand[1]);
            tasks.addTask(todoTsk);
            return ui.showAddTask(todoTsk) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "deadline":
            Task deadlineTsk = new Deadline(processedCommand[1],
                    LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")));
            tasks.addTask(deadlineTsk);
            return ui.showAddTask(deadlineTsk) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "event":
            Task eventTsk = new Event(processedCommand[1],
                    LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")),
                    LocalTime.parse(processedCommand[4], DateTimeFormatter.ofPattern("HHmm")));
            tasks.addTask(eventTsk);
            return ui.showAddTask(eventTsk) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "find":
            return ui.showRequiredTasks(tasks.getTasksWithKeyWord(processedCommand[1]))
                    + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        default:
            throw new NotTaskException();
        }
    }

    /**
     * Processes the command and return bot reply
     * @param input the user command
     * @return the response from the bot
     */
    public String getResponse(String input) {
        try {
            String[] processedCommand = parser.parse(input);
            String reply = generateReply(processedCommand);
            storage.updateFile(tasks.toString()); //reload the file
            return reply;
        } catch (DukeException e) {
            return ui.showDukeError(e);
        } catch (IOException e) {
            return ui.showLoadingError();
        }
    }
}
