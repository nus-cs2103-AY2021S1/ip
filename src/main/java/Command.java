import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Command {
    private final String[] inputLine;
    private boolean bye = false;

    /**
     * Constructor for Command object
     * @param inputLine
     */
    public Command(String[] inputLine){
        this.inputLine = inputLine;
    }

    /**
     * @return
     */
    public boolean isBye(){
        return this.bye;
    }

    /**
     * method to execute the commands as inputted by user
     * @param tasks TaskList object which contains the list of tasks
     * @param ui Ui object for ui
     * @param storage Storage object for reading and writing onto hard disk
     * @throws DukeException for invalid commands
     * @throws IOException for reading and writing
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        switch (inputLine[0]){
            case "bye" :
                this.bye = true;
                return ui.bye();
            case "list" :
                return ui.uiForList(tasks);
            case "done" : {
                int index;
                if (inputLine.length < 2) {
                    throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.DONE);
                }
                try {
                    index = Integer.parseInt(inputLine[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.DONE);
                }
                if (index < 1 || index > tasks.getSize()) {
                    throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.DONE);
                }
                tasks.markDone(index);
                storage.save(tasks);
                return ui.uiForDone(tasks.getTask(index));
            }
            case "delete" : {
                int index;
                if (inputLine.length < 2) {
                    throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.DELETE);
                }
                try {
                    index = Integer.parseInt(inputLine[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.DELETE);
                }
                if (index < 1 || index > tasks.getSize()) {
                    throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.DELETE);
                }
                Task task = tasks.getTask(index);
                tasks.deleteTask(index);
                storage.save(tasks);
                return ui.uiForDelete(tasks, task);
            }
            case "todo" : {
                if (inputLine.length < 2) {
                    throw new DukeException("todo needs description", DukeExceptionType.NO_DESCRIPTION, Commands.TODO);
                } else {
                    StringBuilder description = new StringBuilder();
                    for(int i = 1; i < inputLine.length; i++){
                        description.append(inputLine[i]).append(" ");
                    }
                    Task task = new TodoTask(description.toString(), false);
                    tasks.addTask(task);
                    storage.save(tasks);
                    return ui.uiForAdd(tasks,task);
                }
            }
            case "deadline" : {
                Task task;
                StringBuilder description = new StringBuilder();
                StringBuilder deadline = new StringBuilder();
                if (inputLine.length < 2) {
                    throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.DEADLINE);
                }
                int index = 1;
                while(!inputLine[index].equals("/by")) {
                    description.append(inputLine[index]).append(" ");
                    index++;
                    if(index == inputLine.length) {
                        throw new DukeException("", DukeExceptionType.EMPTY_TIME, Commands.DEADLINE);
                    }
                    if(description.length() < 1) {
                        throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.DEADLINE);
                    }
                }
                index++;
                while(index < inputLine.length) {
                    deadline.append(inputLine[index]).append(" ");
                    index++;
                }
                if(deadline.length() < 1) {
                    throw new DukeException("", DukeExceptionType.EMPTY_TIME, Commands.DEADLINE);
                }
                deadline.deleteCharAt(deadline.length() - 1);
                try{
                    task = new DeadlineTask(description.toString(),false, deadline.toString());
                    tasks.addTask(task);
                    storage.save(tasks);
                    return ui.uiForAdd(tasks, task);
                } catch (DateTimeParseException e){
                    System.err.println("Please use yyyy-mm-dd");
                    throw new DukeException("Please use dd-MM-yyyy", DukeExceptionType.WRONG_TIME, Commands.DEADLINE);
                }
            }
            case "event" : {
                Task task;
                StringBuilder description = new StringBuilder();
                StringBuilder time = new StringBuilder();
                if (inputLine.length < 2) {
                    throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.EVENT);
                }
                int index = 1;
                while(!inputLine[index].equals("/at")) {
                    description.append(inputLine[index]).append(" ");
                    index++;
                    if(index == inputLine.length){
                        throw new DukeException("", DukeExceptionType.EMPTY_TIME, Commands.EVENT);
                    }
                }
                if(description.length() < 1) {
                    throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.EVENT);
                }
                index++;
                while(index < inputLine.length) {
                    time.append(inputLine[index]).append(" ");
                    index++;
                }
                if(time.length() < 1) {
                    throw new DukeException("", DukeExceptionType.EMPTY_TIME, Commands.EVENT);
                }
                time.deleteCharAt(time.length() - 1);
                try{
                    task = new EventTask(description.toString(),false, time.toString());
                    tasks.addTask(task);
                    storage.save(tasks);
                    return ui.uiForAdd(tasks, task);
                } catch (DateTimeParseException e){
                    System.err.println("Please use yyyy-mm-dd format for time");
                    throw new DukeException("Please use dd-MM-yyyy HHmm format", DukeExceptionType.WRONG_TIME, Commands.EVENT);
                }
            }
            case "find" : {
                if (inputLine.length < 2) {
                    throw new DukeException("find needs keyword", DukeExceptionType.NO_DESCRIPTION, Commands.FIND);
                } else {
                    StringBuilder description = new StringBuilder();
                    for (int i = 1; i < inputLine.length; i++) {
                        description.append(inputLine[i]).append(" ");
                    }
                    List<Task> filteredList = tasks.search(description.toString());
                    return ui.uiForFind(new TaskList(filteredList));
                }
            }
            default:
                throw new DukeException("", DukeExceptionType.INVALID_TASK);
        }
    }

}
