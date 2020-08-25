package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tList;
    private Ui ui;
    static boolean running = true;

    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcomeMessage();
        storage = new Storage(filePath);
        try {
            tList = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tList = new TaskList();
        } catch (IOException | ClassNotFoundException e) {
            ui.showUnexpectedLoadingError();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine() && running) {
            try {
                Parser p = new Parser(sc.nextLine(), ui, storage, tList);
                p.parseCommand();
            } catch (StringIndexOutOfBoundsException e) {
                ui.showUnexpectedCommandMessage();
            } catch (IOException e) {
                ui.showUnexpectedSavingError();
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Duke("./task_list.txt").run();
    }
}