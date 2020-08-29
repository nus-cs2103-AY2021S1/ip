package duke;

import java.util.Scanner;

/**
 * The Duke program can record down todos, deadlines and events and save it on your computer.
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Duke constructor to initialize a Duke object, initializes a Ui, Storage and TaskList object.
     * @exception DukeException On input error and file path error.
     */
    public Duke() throws DukeException{
        ui = new Ui();
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.drawLine();
        storage = new Storage();
        taskList = new TaskList(storage.loadFile());

    }

    /**
     * Main method which runs the bot
     */
    public static void main(String[] args) throws  DukeException{
        new Duke().bot();
    }

    /**
     * Bot method which handles the inputs and responds to the user while calling the appropriate classes
     * @exception DukeException On input error and file path error.
     */
    public void bot() throws DukeException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            // splits the input into the different words in order to understand what the user wants
            String first = input.split(" ")[0];
            // user exits the program
            if (input.equals("bye")) {
                ui.bye();
                break;
            // user sees the list of tasks
            } else if (input.equals("list")) {
                ui.printList(taskList.getList());
                ui.drawLine();
            // user sets a specific task as completed
            } else if (input.split(" ")[0].equals("done")) {
                ui.doneTask(taskList.done(Integer.parseInt(input.split(" ")[1])));
                ui.listCount(taskList.countList());
                ui.drawLine();
                storage.saveFile(taskList.getList());
            // user creates a new task
            } else if (first.equals("todo")|| first.equals("deadline") || first.equals("event")) {
                ui.addTask(taskList.add(input));
                ui.listCount(taskList.countList());
                ui.drawLine();
                storage.saveFile(taskList.getList());
            // user deletes a task
            } else if (first.equals("delete")){
                ui.deleteTask(taskList.delete(input));
                ui.listCount(taskList.countList());
                ui.drawLine();
                storage.saveFile(taskList.getList());
            // user searches for a keyword
            } else if (first.equals("find")) {
                ui.foundWord(taskList.findWord(input));
            // user types something the bot does not understand
            } else {
                throw new DukeException("Sorry I don't know what you mean");
            }
        }
    }
}
