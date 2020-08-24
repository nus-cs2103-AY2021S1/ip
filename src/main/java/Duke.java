import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        }  catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String[] processedCommand = parser.parse(fullCommand);
                switch(processedCommand[0].toLowerCase()) {
                case("bye"):
                    isExit = true;
                    break;
                case("list"):
                    ui.showListTasks(tasks.getAllTasks());
                    break;
                case("print"):
                    ui.showRequiredTasks(tasks.getSameDateTasks(processedCommand[1]));
                    break;
                case("done"):
                    Task doneTask = tasks.markDone(Integer.parseInt(processedCommand[1]));
                    ui.showDoneTask(doneTask);
                    break;
                case("delete"):
                    Task deletedTask = tasks.deleteTask(Integer.parseInt(processedCommand[1]));
                    ui.showDeleteTask(deletedTask);
                    break;
                case("todo"):
                    Task todoTsk = new Todo(processedCommand[1]);
                    tasks.addTask(todoTsk);
                    ui.showAddTask(todoTsk);
                    break;
                case("deadline"):
                    Task deadlineTsk = new Deadline(processedCommand[1],
                            LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")));
                    tasks.addTask(deadlineTsk);
                    ui.showAddTask(deadlineTsk);
                    break;
                case("event"):
                    Task eventTsk = new Event(processedCommand[1],
                            LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")),
                            LocalTime.parse(processedCommand[4], DateTimeFormatter.ofPattern("HHmm")));
                    tasks.addTask(eventTsk);
                    ui.showAddTask(eventTsk);
                    break;
                case "find":
                    ui.showRequiredTasks(tasks.getTasksWithKeyWord(processedCommand[1]));
                }
                ui.showTotalTasks(tasks.getNumTasks());
                //reload the file
                storage.updateFile(tasks.toString());
            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (IOException e) {
                ui.showLoadingError();
            }
        }
        ui.byeMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
