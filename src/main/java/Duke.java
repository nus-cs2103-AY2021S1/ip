import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke{

    Ui ui;
    Storage storage, archiveStorage;
    TaskList tasks, archives;
    Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        archiveStorage = new Storage("data/duke.txt.achive");
        tasks = new TaskList(storage.load());
        archives = new TaskList(archiveStorage.load());
        parser = new Parser(tasks, archives);
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        archiveStorage = new Storage(filePath+".achive");
        tasks = new TaskList(storage.load());
        archives = new TaskList(archiveStorage.load());
        parser = new Parser(tasks, archives);
    }

    public String getResponse(String input){
        String tmp = ui.read(input, parser);
        storage.save(tasks.getTodoList());
        archiveStorage.save(tasks.getTodoList());
        return tmp;
    }

    public void save(){
        storage.save(tasks.getTodoList());
        archiveStorage.save(archives.getTodoList());
    }

    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.getResponse("");
        duke.save();
    }
}
