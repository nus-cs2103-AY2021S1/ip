package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.exceptions.DukeException;


/**
 * Duke helps you manage tasks through a chatbot.
 * Duke also saves your list of tasks and will load
 * where you left off.
 */
public class Duke {
    protected Storage storage;
    protected Ui ui;
    protected String inquiry;
    protected TaskList tasks;

    /**
     * Duke constructor
     */
    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.reply(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Executes the chatbot. The chatbot will respond
     * based on specific keywords.
     */
    public void chat() {
        ui.intro();
        boolean endLoop = false;
        while (!endLoop) {
            try {
                this.inquiry = ui.nextInquiry();
                Command command = Parser.parse(inquiry);
                endLoop = command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.reply(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method that executes Duke chatbot.
     *
     * @param args String array of arguments.
     */
    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.chat();
    }
}
    //    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//}

