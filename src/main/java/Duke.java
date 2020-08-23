import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    //private Ui ui;

    public Duke(String filePath) throws FileNotFoundException, DukeException {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
        this.parser = new Parser(taskList);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Hi there! I'm Peanut.\nHow can I be of assistance?\n");
        input = sc.nextLine();
        while (!input.equals("bye")) {
            parser.parseUserInput(input);
            input = sc.nextLine();
        }
        System.out.println("\nBye! Sad to see you go :(");
    }

    public static void main(String[] args) throws IOException, DukeException {

        if (!Files.exists(Paths.get("data"))) {
            Files.createDirectory(Paths.get("data"));
        }
        if (!Files.exists(Paths.get("data/Duke.txt"))) {
            Files.createFile(Paths.get("data/Duke.txt"));
        }

        new Duke("data/Duke.txt").run();

    }

}



