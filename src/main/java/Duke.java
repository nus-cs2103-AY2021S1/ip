import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
    }

    public void run() {
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList(new ArrayList<Task>());
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){
            new Duke("data/tasks.txt").run();
    }
}
