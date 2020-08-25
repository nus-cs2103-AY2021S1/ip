import java.nio.file.Path;

public class Main {

    private static TaskList taskList;
    private static Storage taskStorage;
    private static Ui ui;

    public static void main(String[] args) {

        boolean exitProgram = false;
        final Path dataLocation = Path.of("src","main", "chatbot", "data.txt");

        // initialization
        ui = new Ui();
        taskStorage = new Storage(dataLocation);
        try {
            taskList = new TaskList(taskStorage.loadTasks());
        } catch (ChatbotException e) {
            ui.showErrorMessage(e.getMessage());
        }

        ui.greet();

        while(!exitProgram) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, taskStorage);
                exitProgram = command.isExit();
            } catch (ChatbotException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
