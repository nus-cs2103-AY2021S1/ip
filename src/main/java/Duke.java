import java.util.Scanner;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.FindCommand;
import commands.ListCommand;
import exceptions.DukeException;
import parser.CommandParser;
import parser.TaskParser;
import service.DeadlineTask;
import service.DukeResponse;
import service.DukeService;
import service.EventTask;
import service.TodoTask;
import storage.Storage;

public class Duke {
    private static final String SEPARATOR = "___________________________________________________________";
    private static final String STORAGE = "./data/duke.txt";

    private static void printMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    /**
     * Runs main method
     * @param args arguments
     */
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
        parser.registerCommand(AddCommand::new, AddCommand.COMMAND_WORD);
        parser.registerCommand(ByeCommand::new, ByeCommand.COMMAND_WORD);
        parser.registerCommand(ListCommand::new, ListCommand.COMMAND_WORD);
        parser.registerCommand(DoneCommand::new, DoneCommand.COMMAND_WORD);
        parser.registerCommand(DeleteCommand::new, DeleteCommand.COMMAND_WORD);
        parser.registerCommand(FindCommand::new, FindCommand.COMMAND_WORD);

        ///register tasks
        TaskParser taskParser = new TaskParser();
        taskParser.registerTask(TodoTask::new, TodoTask.TASK_WORD);
        taskParser.registerTask(DeadlineTask::new, DeadlineTask.TASK_WORD);
        taskParser.registerTask(EventTask::new, EventTask.TASK_WORD);
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
            printMessage(ignored.getMessage());
        }
    }
}
