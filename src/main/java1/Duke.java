import java1.exception.*;
import java1.parser.Parser;
import java1.tasklist.*;
import java1.storage.*;
import java1.ui.*;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();

        storage = new Storage(filePath);

        try {
            File f = new File(filePath);
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            try {
                boolean success = newFile.createNewFile();

                tasks = new TaskList(storage.load());
            } catch (IOException ex) {
                System.out.println("An error occurred, file could not be created.");
                e.printStackTrace();
            }
        }
    }

    public void run() {
        ui.greeting();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            ui.showLine();
            try {
                String[] splitInput = input.split(" ");
                if (splitInput[0].equals("bye")) {
                    ui.sayBye();
                    return;
                } else {
                    Parser.commandParser(input, tasks);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("/Users/joshua/Desktop/ip/data/duke.txt").run();
    }

}

