package duke.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the Duke Program. Helps manage user's tasks and keeps them in check.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    public Duke() throws FileNotFoundException {
        ui = new UI();
        tasks = new TaskList();
        parser = new Parser();
        try {
            storage = new Storage(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Here is where the Duke program is run and commands are executed.
     * @throws IOException File containing list of task may not be found.
     */
    public void run() throws IOException {
        UI.intro();
        boolean isExit = false;
        while(!isExit) {
            String toPrint = ui.nextInput();
            ui.dividerLine();

            Command c = parser.parse(toPrint);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();

            ui.dividerLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }


}

//    public static boolean isValidDate(String dateStr) {
//        try {
//            LocalDate.parse(dateStr);
//        } catch (DateTimeParseException e){
//            return false;
//        }
//        return true;
//    }
