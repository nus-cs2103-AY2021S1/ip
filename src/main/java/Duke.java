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

    public String getResponse(String input) {
        assert(tasks != null); //ensure tasklist is initialised by Duke
        StringBuilder response = new StringBuilder();
        try {
            String[] processedCommand = parser.parse(input);
            switch (processedCommand[0].toLowerCase()) {
            case "bye":
                return ui.byeMessage();
            case "list":
                response.append(ui.showListTasks(tasks.getAllTasks()));
                break;
            case "print":
                response.append(ui.showRequiredTasks(tasks.getSameDateTasks(processedCommand[1])));
                break;
            case "done":
                Task doneTask = tasks.markDone(Integer.parseInt(processedCommand[1]));
                response.append(ui.showDoneTask(doneTask));
                break;
            case "delete":
                Task deletedTask = tasks.deleteTask(Integer.parseInt(processedCommand[1]));
                response.append(ui.showDeleteTask(deletedTask));
                break;
            case "todo":
                Task todoTsk = new Todo(processedCommand[1]);
                tasks.addTask(todoTsk);
                response.append(ui.showAddTask(todoTsk));
                break;
            case "deadline":
                Task deadlineTsk = new Deadline(processedCommand[1],
                        LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")));
                tasks.addTask(deadlineTsk);
                response.append(ui.showAddTask(deadlineTsk));
                break;
            case "event":
                Task eventTsk = new Event(processedCommand[1],
                        LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")),
                        LocalTime.parse(processedCommand[4], DateTimeFormatter.ofPattern("HHmm")));
                tasks.addTask(eventTsk);
                response.append(ui.showAddTask(eventTsk));
                break;
            case "find":
                response.append(ui.showRequiredTasks(tasks.getTasksWithKeyWord(processedCommand[1])));
                break;
            default:
                break;
            }
            response.append("\n" + ui.showTotalTasks(tasks.getNumTasks()));
            storage.updateFile(tasks.toString()); //reload the file
        } catch (DukeException e) {
            response.append(ui.showDukeError(e));
        } catch (IOException e) {
            response.append(ui.showLoadingError());
        }
        return response.toString();
    }
}
