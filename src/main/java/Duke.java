package main.java;

import java.io.FileNotFoundException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public void run() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;
        
        while (!isExit) {
            String userInput = sc.nextLine();
            isExit = Parser.execute(tasks, ui, storage, userInput);
        }
    }
    
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Duke("./data/duke.txt").run();
    }
}
