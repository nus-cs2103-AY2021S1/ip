package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Duke {

    private static String horizontalLine = "    ____________________________________________________________";
    private static String textIndentation = "     ";
    private static String greetMessage = Duke.textIndentation + "Hello! I'm Duke\n" +
            Duke.textIndentation + "What can I do for you?";
    private static String exitMessage = Duke.textIndentation + "Bye. Hope to see you again soon!";
    private static String addTaskMessage = Duke.textIndentation + "Got it. I've added this task:";
    private static String completeTaskMessage = Duke.textIndentation + "Nice! I've marked this task as done:";
    private static String invalidSyntaxMessage = Duke.textIndentation + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static String deleteTaskMessage = Duke.textIndentation + "Noted. I've removed this task:";

    public static void main(String[] args) {

        String pathName = "./data/duke_data.csv";

        Storage storage = new Storage(pathName);
        TaskList tasks = storage.loadTasks();
        Ui ui = new Ui();
        ui.printHello();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Command command = Parser.parse(input);
            command.execute(storage, tasks, ui);
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }
}
