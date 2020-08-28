import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Acts as an interface for all other Classes with the Non-Persistent DataStorage Class.
 * Ensures that none of the Classes have a direct dependency on the DataStorage itself.
 */
public class DataStorageInterface {

    /**
     * Initialises the DataStorage at start up of ChatBot.
     *
     * @throws DukeException For the different Commands Initialising, will never be thrown.
     */
    public static void initStorage() throws DukeException{
        DataStorage.init();
    }

    /**
     * Returns String representation of list of commands for User to know.
     *
     * @return String representation of list of commands with Usage syntax.
     */
    public static String listCommands(){
        StringBuilder allCommands = new StringBuilder();
        for(Command c:DataStorage.commandInit){
            allCommands.append(c.getName()).append(": ").append(c.getUsage()).append("\n");
        }
        return allCommands.toString();
    }

    /**
     * Marks Task as Done in the task list.
     *
     * @param index Index of Task in list.
     * @return Task that is marked Done.
     * @throws CustomException If Task IsDone.
     */
    public static Task markDone(int index) throws CustomException {
        Task curr = DataStorage.taskList.get(index);
        if(curr.isDone()){
            throw new CustomException("Error: This task has already been marked as done");
        }
        curr.markDone();
        return curr;
    }

    /**
     * Removes a Task from the Task List.
     *
     * @param index Index of Task in list.
     * @return Task that is removed.
     */
    public static Task remove(int index){
        return DataStorage.taskList.remove(index);
    }

    /**
     * Returns size of Task List.
     *
     * @return size of Task List.
     */
    public static int getSize(){
        return DataStorage.taskList.size();
    }

    /**
     * Returns number of Tasks that are not marked Done by the User.
     *
     * @return number of Tasks that are not marked Done by the User.
     */
    public static int getTasksNotDone(){
        int counter = 0;
        for(Task t:DataStorage.taskList){
            if(!t.isDone()){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Returns Task Added Response with number of tasks Not Done.
     *
     * @param task Task to be added.
     * @return Task Added Response with number of tasks Not Done.
     */
    public static String taskAdded(Task task){
        return "Got it. I've added this task:\n\t" + task.toString() +
                String.format("\nNow you have %d tasks in the list",
                        DataStorageInterface.getTasksNotDone());
    }

    /**
     * Adds Todo Task to task list.
     *
     * @param query User entered title of Task.
     * @return Task that is added.
     * @throws WrongUsageException If todo command is used wrongly.
     */
    public static Task addToDo(String query) throws WrongUsageException{
        Task newTask = new ToDo(query);
        DataStorage.taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds Deadline Task to task list.
     *
     * @param title User entered title of Task.
     * @param preposition User entered preposition for date and time.
     * @param date User entered due date of Task.
     * @param time User entered timing of Task.
     * @return Task that is added.
     * @throws WrongUsageException If deadline command is used wrongly.
     */
    public static Task addDeadline(String title,
                                   String preposition,
                                   LocalDate date, LocalTime time) throws WrongUsageException{
        Task newTask = new Deadline(title,preposition,date, time);
        DataStorage.taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds Event Task to task list.
     *
     * @param title User entered title of Task.
     * @param preposition User entered preposition for date and time.
     * @param date User entered due date of Task.
     * @param time User entered timing of Task.
     * @return Task that is added.
     * @throws WrongUsageException If event command is used wrongly.
     */
    public static Task addEvent(String title, String preposition, LocalDate date, LocalTime time)
            throws WrongUsageException{
        Task newTask = new Event(title,preposition,date, time);
        DataStorage.taskList.add(newTask);
        return newTask;
    }

    /**
     * Removes all tasks from the Task List.
     */
    public static void removeAllTasks(){
        DataStorage.taskList.clear();
    }

    /**
     * Adds Tasks from an array to the current Task List.
     *
     * @param taskArrayList Tasks to be Added in current Task List.
     */
    public static void loadNewTasks(ArrayList<Task> taskArrayList){
        for(Task t: taskArrayList){
            DataStorage.taskList.add(t);
        }
    }

    /**
     * Returns String representation of all tasks in the task list.
     *
     * @return String representation of all tasks in the task list.
     */
    public static String listOfTasks(){
        StringBuilder acc = new StringBuilder();
        int i = 0;
        for (Task t: DataStorage.taskList){
            i++;
            acc.append(String.format("%d. %s\n", i, t));
        }
        return acc.toString().isEmpty()?"There are no Tasks in the list":acc.toString();
    }

    /**
     * Returns String representation of tasks to be saved in an external file.
     *
     * @return String representation of tasks to be saved in an external file.
     */
    public static String getSaveRepresentation(){
        StringBuilder acc = new StringBuilder();
        int i = 0;
        for (Task t: DataStorage.taskList){
            i++;
            acc.append(String.format("%d. %s\n", i, t.saveStringRepresentation()));
        }
        return acc.toString().isEmpty()?"":acc.toString();
    }

    /**
     * Returns Usage of a single Command.
     *
     * @param query Command queried by User.
     * @return String representation of Command queried by User.
     * @throws UnknownCommandException If User enters wrong Command.
     */
    public static String getUsage(String query) throws UnknownCommandException{
        for(Command c: DataStorage.commandInit){
            if(c.getName().equalsIgnoreCase(query)){
                return c.getName() + "\nUsage: "  + c.getUsage() +
                        "\nDescription: " + c.getDescription();
            }
        }
        throw new UnknownCommandException(query);
    }


}
