package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }

    protected Storage storage;
    protected Ui ui;
    protected String inquiry;
    protected TaskList tasks;
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

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.chat();
    }
}
