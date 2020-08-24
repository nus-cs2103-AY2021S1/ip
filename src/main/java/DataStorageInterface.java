

public class DataStorageInterface {

    public static void initStorage() throws DukeException{
        DataStorage.init();
    }

    //TODO: Adjust the list usage accordingly to Command Center if you want to add custom commands
    public static String listCommands(){
        String allCommands = "";
        for(Command c:DataStorage.commandInit){
            allCommands = allCommands + c.getName() + ": "  + c.getUsage() + "\n";
        }
        return allCommands;
    }

    public static Task markDone(int index) throws CustomException {
        Task curr = DataStorage.taskList.get(index);
        if(curr.isDone()){
            throw new CustomException("Error: This task has already been marked as done");
        }
        curr.markDone();
        return curr;
    }

    public static int getSize(){
        return DataStorage.taskList.size();
    }

    //TODO: make a method called getAmountNotDone to get the number of tasks not yet finished for the markedAsDone method in Done class
    public static int getTasksNotDone(){
        int counter = 0;
        for(Task t:DataStorage.taskList){
            if(!t.isDone()){
                counter++;
            }
        }
        return counter;
    }

    public static String taskAdded(Task task){
        return "Got it. I've added this task:\n\t" + task.toString() +
                String.format("\nNow you have %d tasks in the list",
                        DataStorageInterface.getTasksNotDone());
    }

    public static Task addToDo(String query) throws WrongUsageException{
        Task newTask = new ToDo(query);
        DataStorage.taskList.add(newTask);
        return newTask;
    }

    public static Task addDeadline(String title,
                                   String preposition,
                                   String dateTime) throws WrongUsageException{
        Task newTask = new Deadline(title,preposition,dateTime);
        DataStorage.taskList.add(newTask);
        return newTask;
    }

    public static Task addEvent(String title, String preposition, String dateTime) throws WrongUsageException{
        Task newTask = new Event(title,preposition,dateTime);
        DataStorage.taskList.add(newTask);
        return newTask;
    }

    public static String listOfTasks(){
        StringBuilder acc = new StringBuilder();
        int i = 0;
        for (Task t: DataStorage.taskList){
            i++;
            acc.append(String.format("%d. %s\n", i, t));
        }
        return acc.toString();
    }

    //TODO: Possibly requires a newline char
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
