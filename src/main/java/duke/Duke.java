package duke;

import duke.Command.Command;

import duke.Exception.DukeException;

import duke.Parser.Parser;
import duke.Task.Task;
import duke.Task.TaskList;

import duke.Ui.Ui;

import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke("/Users/doraheng/Documents/CS 2103T/Duke/ip/data/duke.txt").run();

    }

<<<<<<< HEAD
    private static void run() {
        print(Message.MESSAGE_WELCOME);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("bye")) {
            print(input);
        }

        scanner.close();
        print(input);
    }

    private static void print(String message) {
        String messageB = Message.BORDERS;
        try {
            if (message.equals(Message.MESSAGE_WELCOME)) {
                System.out.println(messageB + "\n"
                        + Message.MESSAGE_WELCOME + "\n"
                        + messageB);
            } else {
                System.out.println(messageB + "\n"
                        + Command.parse(message) + "\n"
                        + messageB);
=======
    public void run() {
        ui.showWelcome();
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
>>>>>>> master
            }
        }
    }
}