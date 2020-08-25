import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greet();
        
        boolean shouldLoop = true;
        while (shouldLoop) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                shouldLoop = command.shouldLoop();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }
}
