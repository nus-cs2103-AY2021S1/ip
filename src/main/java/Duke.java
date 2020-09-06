import javax.swing.text.html.Option;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
     * Returns the reply for the respective commands after processing them.
     * @param processedCommand the command after processing by parser.
     * @return the reply for the respective commands.
     * @throws DukeException if user inputs invalid commands.
     */
    private String generateReply(String[] processedCommand) throws DukeException {
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
            boolean hasTodoNotes = processedCommand.length == 3;
            Task todoTsk = new Todo(processedCommand[1],
                    (hasTodoNotes)? Optional.of(processedCommand[2]) : Optional.empty());
            tasks.addTask(todoTsk);
            return ui.showAddTask(todoTsk) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "deadline":
            boolean hasDeadlineNotes = processedCommand.length == 5;
            Task deadlineTsk = new Deadline(processedCommand[1],
                    LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")),
                    (hasDeadlineNotes)? Optional.of(processedCommand[4]): Optional.empty());
            tasks.addTask(deadlineTsk);
            return ui.showAddTask(deadlineTsk) + "\n" + ui.showTotalTasks(tasks.getNumTasks());
        case "event":
            boolean hasEventNotes = processedCommand.length == 6;
            Task eventTsk = new Event(processedCommand[1],
                    LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")),
                    LocalTime.parse(processedCommand[4], DateTimeFormatter.ofPattern("HHmm")),
                    (hasEventNotes)? Optional.of(processedCommand[5]) : Optional.empty());
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
     * Processes the command and returns bot reply.
     * @param input the user command.
     * @return the response from the bot.
     */
    public String getResponse(String input) {
        assert(tasks != null); //ensure tasklist is initialised by Duke
        StringBuilder response = new StringBuilder();
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
