import exceptions.InvalidCommandException;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> taskList = new ArrayList<>();
    private Database database;


    private void getUserInput(ArrayList<Task> taskList) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Output output = new Output();
            try {
                output.response(input, taskList);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
            if (input.equals("bye")) break;
        }
        this.database.updateDatabase(taskList);
        sc.close();
    }

    private void printWelcomeMessage() {
        String welcome = "_____________________________________\n"
                + "Hello! I'm Ray\n" + "Please input a command\n"
                + "_____________________________________\n";
        System.out.println(welcome);
    }

    public void start() {
        this.database = Database.dbInstance();
        //this.taskList = new TaskList();
        printWelcomeMessage();
        getUserInput(this.taskList);
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
