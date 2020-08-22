import commands.*;
import exceptions.DukeException;
import parser.CommandParser;
import parser.TaskParser;
import service.*;
import storage.Storage;

import java.util.Scanner;

public class Duke {
    private final static String SEPARATOR = "___________________________________________________________";
    private final static String STORAGE = "./data/duke.txt";

    private static void printMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        DukeService service = new DukeService();

        ///register commands
        CommandParser parser = new CommandParser();
        parser.registerCommand(AddCommand::new, AddCommand.commandWord);
        parser.registerCommand(ByeCommand::new, ByeCommand.commandWord);
        parser.registerCommand(ListCommand::new, ListCommand.commandWord);
        parser.registerCommand(DoneCommand::new, DoneCommand.commandWord);
        parser.registerCommand(DeleteCommand::new, DeleteCommand.commandWord);
        parser.registerCommand(FindCommand::new, FindCommand.commandWord);

        ///register tasks
        TaskParser taskParser = new TaskParser();
        taskParser.registerTask(TodoTask::new, TodoTask.taskWord);
        taskParser.registerTask(DeadlineTask::new, DeadlineTask.taskWord);
        taskParser.registerTask(EventTask::new, EventTask.taskWord);
        ///set task parser
        AddCommand.setTaskParser(taskParser);
        ///initialize storage
        Storage storage = new Storage(STORAGE, taskParser);
        try {
            storage.readFromFile(service);
        } catch (DukeException e) {
            System.out.println("Sorry I cannot load data from file");
        }


        ///start execution
        while (true) {
            String command = sc.nextLine();

            try {
                Command newCommand = parser.parse(command);
                newCommand.parse();
                DukeResponse response = newCommand.execute(service);
                printMessage(response.toString());
            } catch (Exception e) {
                 printMessage(e.toString());
            }

            if (command.equals("bye")) {
                break;
            }
        }

        try {
            storage.writeToFile(service);
        } catch (DukeException ignored) {
        }
    }
}
