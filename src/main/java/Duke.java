import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    
    public static void main(String[] args) {
        
        UI ui = new UI();
        TaskList taskList = new TaskList();
        Path filePath = Paths.get("data","duke.txt");
        Storage storage;
        
        try {
            storage = Storage.loadStorage(filePath);
            taskList.loadDataFromStorage(filePath);
        }  catch (IOException e) {
            ui.printToConsole("File System Error");
            return;
        } catch (DukeException e) {
            ui.printToConsole("Stored file format is invalid\n" + e.getMessage());
            return;
        }
        
        ui.greet();

        while (ui.hasNextCommand()) {
            String nextCommand = ui.readCommand();
            String[] inputList = nextCommand.split(" ", 2);
            String argument = inputList.length > 1 ? inputList[1] : "";
            Command command;
            
            try {
                command = Command.valueOf(inputList[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                command = Command.INVALID;
            }

            try {
                switch (command) {
                case BYE:
                    ui.close();
                    return;
                case LIST:
                    ui.printToConsole(taskList.convertTaskListToString());
                    break;
                case DATE:
                    ui.printToConsole(taskList.taskListToDateFilteredString(argument));
                    break;
                case DONE:
                    ui.printToConsole(taskList.markTaskAsDone(Integer.parseInt(inputList[1]), storage));
                    break;
                case DELETE:
                    ui.printToConsole(taskList.deleteTask(Integer.parseInt(inputList[1]), storage));
                    break;
                case TODO:
                    ToDo newTodo = ToDo.createNewToDo(argument);
                    storage.writeLineToStorage(newTodo.generateStorageString());
                    ui.printToConsole(taskList.addTaskToList(newTodo));
                    break;
                case EVENT:
                    Event newEvent = Event.createNewEvent(argument);
                    storage.writeLineToStorage(newEvent.generateStorageString());
                    ui.printToConsole(taskList.addTaskToList(newEvent));
                    break;
                case DEADLINE:
                    Deadline newDeadline = Deadline.createNewDeadline(argument);
                    storage.writeLineToStorage(newDeadline.generateStorageString());
                    ui.printToConsole(taskList.addTaskToList(newDeadline));
                    break;
                case INVALID:
                    throw new DukeException("Invalid Command.");
                }
            } catch (DukeException e) {
                ui.printToConsole(e.getMessage());
            } catch (IOException e) {
                ui.printToConsole("Error: Task could not be saved.");
            }
        }
    }
}