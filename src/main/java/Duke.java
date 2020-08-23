import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("data/storage/duke.txt");
        Parser parser = new Parser();
        String input = "";
        List<Task> list = storage.load();
        String output;

        ui.showWelcome();
        

        while (!input.equals("bye")) {
            input = ui.readInput();

            try {
                output = parser.parse(input, list, storage);
            } catch (DukeException e) {
                output = e.getMessage();
            }

            ui.showOutput(output);
        }
    }
}
