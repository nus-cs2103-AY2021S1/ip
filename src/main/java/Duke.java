import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    //private Ui ui;

    public Duke(String filePath) throws FileNotFoundException, DukeException {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
    }

    private static void processInput(String input, TaskList list) {
        System.out.println("");
        try {
            if (input.equals("list")) {
                System.out.println("Here are your tasks:");
                System.out.println(list);
            } else if (input.startsWith("done")) {
                list.markTaskDone(input);
            } else if (input.startsWith("todo")) {
                ToDo toDoTask = new ToDo(input);
                list.addTask(toDoTask);
            } else if (input.startsWith("deadline")) {
                Deadline deadlineTask = new Deadline(input);
                list.addTask(deadlineTask);
            } else if (input.startsWith("event")) {
                Event eventTask = new Event(input);
                list.addTask(eventTask);
            } else if (input.startsWith("delete")) {
                list.deleteTask(input);
            }
            else {
                //unrecognised command
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.\n");
            }
        } catch (DukeException | IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Hi there! I'm Peanut.\nHow can I be of assistance?\n");
        input = sc.nextLine();
        while (!input.equals("bye")) {
            processInput(input, taskList);
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



