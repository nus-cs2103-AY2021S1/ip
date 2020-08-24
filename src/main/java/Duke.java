package main.java;

import main.java.exception.*;
import main.java.parser.Parser;
import main.java.tasklist.*;
import main.java.storage.*;
import main.java.ui.*;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    public final static String pathDirectory = System.getProperty("user.dir");
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        System.out.println(filePath);
        storage = new Storage(filePath);

        try {
            File f = new File(filePath);
            tasks = new TaskList(storage.load());

        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            try {
                newFile.createNewFile();

                tasks = new TaskList(storage.load());
            } catch (IOException ex) {
                System.out.println("An error occurred, file could not be created.");
            }
        }
    }

    public void run() {
        ui.greeting();
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
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
        new Duke(pathDirectory + "/data/duke.txt").run();
    }

}

