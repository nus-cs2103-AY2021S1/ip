package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/*
public class Duke {
    public static final String INDENTATION = "    ";
    public static final String DIVIDER = "____________________________________________________________";
    public static final String GREETING = "Hello! I am Smith\n" + "What can I do for you?";
    public static final String EXITMESSAGE = "Bye. Hope to see you again soon!";

    public static String makeBlock(String string) {
        String[] strings = string.split("\n");
        String result = INDENTATION + DIVIDER + "\n";
        for(int i = 0; i < strings.length; i = i + 1) {
            result = result + INDENTATION + strings[i] + "\n";
        }
        result = result + INDENTATION + DIVIDER + "\n";
        return  result;
    }

    public void run() {

    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println(makeBlock(logo + "\n" + GREETING));
        //System.out.println(("    1      ").split(" ").length);
        LevelEight.interact();
   }
}
*/
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String FILE_PATH = "data/duke.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readRecord());
        } catch (FileNotFoundException fileNotFoundException) {
            ui.handle(fileNotFoundException);
            taskList = new TaskList();
            try {
                storage.writeRecord(taskList);
            } catch (IOException ioException) {
            }

        } catch (LoadingException loadingException) {
            ui.handle(loadingException);
            taskList = new TaskList();
            try {
                storage.writeRecord(taskList);
            } catch (IOException ioException) {
            }

        }
    }

    public void run() {
        this.ui.showGreeting();
        boolean isContinuing = true;
        while (isContinuing) {
            try {
                String command = this.ui.readCommand();
                Command parsedCommand = Parser.parseCommand(command);
                parsedCommand.excecute(taskList, ui, storage);
                isContinuing = parsedCommand.isContinuing();
            } catch (CommandNotFoundException commandNotFoundexException) {
                //System.out.println(commandNotFoundexException.getMessage());
                ui.handle(commandNotFoundexException);
            } catch (TaskNotFoundException taskNotFoundException) {
                ui.handle(taskNotFoundException);
            } catch (IOException ioException) {
                ui.handle(ioException);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }
}

/*
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LoadingException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.interact();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
*/