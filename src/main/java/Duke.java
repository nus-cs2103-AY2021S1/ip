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
    Storage storage;
    TaskList tasks;
    Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage.load());
        parser = new Parser(tasks);
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(tasks);
    }

    public String getResponse(String input){
        return ui.read(input, parser);
    }

    public void save(){
        storage.save(tasks.getTodoList());
    }

    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.getResponse("");
        duke.save();
    }
}
