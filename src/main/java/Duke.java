import java.util.Scanner;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.FindCommand;
import commands.ListCommand;
import exceptions.DukeException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import parser.CommandParser;
import parser.TaskParser;
import service.DeadlineTask;
import service.DukeResponse;
import service.DukeService;
import service.EventTask;
import service.TodoTask;
import storage.Storage;
import ui.MyStage;

public class Duke extends Application {
    private static final String SEPARATOR = "__________________________________________";
    private static final String STORAGE = "./data/duke.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/friend.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/totoro.png"));

    private static void printMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public static void main(String[] args) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        MyStage stage = new MyStage(service, parser, taskParser, storage);
        stage.setImage(user, duke);
        stage.start(primaryStage);
    }
}
