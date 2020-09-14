package duke;

import duke.commands.Command;
import duke.errors.DukeException;
import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

import java.io.IOException;

/**
 * This Duke class is the main class that prints out the relevant outputs based on input given by user
 */
public class Duke {
    private boolean isExit = false;
    //checks whether the Duke has been given the user command, true if exit command is given and false otherwise
    private Storage storage; //deals with loading tasks from the file, saving tasks and shortcuts in the file
    private TaskList tasks; //contains the task list e.g., it has operations to add/delete tasks in the list
    private Ui ui = new Ui(); //deals with interactions with the user

    /**
     * A constructor used to initialise Duke.
     */
    public Duke(){}

    /**
     * Assigns the above member variables with the appropriate values, and throws certain exceptions if file in
     * the filePath mentioned is empty or absent
     *
     * @param filePath represents where the filepath of where the file may exist.
     * @param shortFormPath contains all the pre user defined short forms
     */
    public Duke(String filePath, String shortFormPath) {
        ui = new Ui();
        storage = new Storage(filePath, shortFormPath);
        try {
            tasks = new TaskList(storage.load());
            storage.setShortForm();
        } catch (DukeException e) {
            ui.setDukeException(e);
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Assigns the above member variables with the appropriate values, and throws certain exceptions if file in
     * the filePath mentioned is empty or absent
     *
     * @param filePath represents where the filepath of where the file may exist.
     * @param input filepath for the inputs
     * @param shortFormPath filepath for file containing short cuts
     */
    public Duke(String filePath, String input, String shortFormPath) {
        storage = new Storage(filePath, shortFormPath);
        try {
            ui = new Ui(input);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.setDukeException(e);
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * This gives the boolean value of isExit
     *
     * @return returns the value of isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * This gives the respective output based on the input
     *
     * @param inputs what the user inputs in the GUI, can be multiple inputs.
     * @return the output based on the output in the form of an array, where
     */
    public Response[] getResponse(String... inputs) {
        Response[] responses = new Response[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            String input = inputs[i];
            Response response = getResponse(input); //receives input depending on input depending on
            responses[i] = response;
        }
        return responses;
    }

    /**
     * Returns a Response depending on the input given by the user
     *
     * @param input given by user
     * @return Response with a boolean value true if the user input is wrong, leading to an exception
     */
    private Response getResponse(String input) {
        Command c = Parser.parse(input); //respective command depending on input
        Response response;
        String output;
        try {
            isExit = c.isExit();
            output = c.execute(tasks, new Ui(), storage); //concatenates output message
            response = new Response(output, false); //since user input is in correct
        } catch (DukeException e) {
            output = e.getMessage(); //concatenates error message
            response = new Response(output, true); //since user input is wrong
        }
        return response;
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
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit(); //if true exits program as bye is mentioned
            } catch (DukeException e) {
                ui.showLoadingError();
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
        Duke duke = new Duke("src/main/java/tasks.txt", "src/main/java/shortCuts.txt");
        duke.run();
        //String s = duke.getResponse("ToDo read book");
        /*PrintStream fileOut = new PrintStream("src/main/java/output.txt");
        System.setOut(fileOut);
        FileWriter fw = new FileWriter("src/main/java/tasks.txt");
        fw.write("");
        fw.close();
        Duke duke = new Duke("src/main/java/tasks.txt", "src/main/java/input.txt", "src/main/java/shortCuts.txt");
        duke.run();*/
    }
}
