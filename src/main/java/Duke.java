import java.io.*;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a Duke object
     * @param filePath
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        } catch(DukeException | IOException e){
            tasks = new TaskList();
        }

    }

    /**
     * runs the process until terminated
     * @throws IOException
     */
    public void run() throws IOException {
        ui.greet();
        boolean isBye = false;
        while (!isBye){
            try {
                String command = ui.read();
                Command cmd = Parser.parse(command);
                cmd.execute(tasks, ui, storage);
                isBye = cmd.isBye();
            } catch (DukeException e){
                System.err.println(e);
            }
        }
    }


    public static void main(String[] args) throws IOException, DukeException {

        new Duke("./data\\duke.txt").run();

    }

}
