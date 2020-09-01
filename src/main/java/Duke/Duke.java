package Duke;

import Duke.Commands.Command;
import Duke.Errors.DukeException;
import Duke.Helpers.Parser;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

import java.io.IOException;
/**
 * This Duke class is the main class that prints out the relevant outputs by including all the subclasses of Task and
 * taking in the input.
 */
public class Duke {
    private boolean isExit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    public Duke(){}

    /**
     * This gives the boolean value of isExit
     *
     * @return returns the value of isExit.
     */
    public boolean isExit(){
        return isExit;
    }

    /**
     * This gives the respective output based on the input
     *
     * @param inputs what the user inputs in the GUI, can be multiple inputs.
     * @return the output based on the output.
     */
    public String getResponse(String... inputs)  {
        String output = "";
        for(int i = 0; i < inputs.length; i++) {
            String input = inputs[i];
            Command c = Parser.parse(input);
            try {
                isExit = c.isExit();
                output = output + "\n" + (i + 1) + ". " + c.execute(tasks, new Ui(), storage);
            } catch (DukeException e) {
                output = output + "\n" + (i + 1) + ". " + e.getMessage();
            }
        }
        return output.substring(1);
    }
    /**
     * Assigns the above member variables with the appropriate values, and throws certain exceptions if file in
     * the filePath mentioned is empty or absent
     *
     * @param filePath represents where the filepath of where the file may exist.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    public Duke(String filePath, String input){
        storage = new Storage(filePath);
        try {
            ui = new Ui(input);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    /**
     * gives main logic of the App,
     * where exceptions are caught and printed and if bye is there code stops. also starts with hello
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     *
     * @param args of type String[]
     * reads input using scan() and adds it to todos.
     *  Then, prints out relevant information using the output() func.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("src/main/java/tasks.txt");
        duke.run();
        //String s = duke.getResponse("ToDo read book");
        /*PrintStream fileOut = new PrintStream("src/main/java/output.txt");
        System.setOut(fileOut);
        FileWriter fw = new FileWriter("src/main/java/tasks.txt");
        fw.write("");
        fw.close();
        Duke duke = new Duke("src/main/java/tasks.txt", "src/main/java/input.txt");
        duke.run();*/
    }
}

