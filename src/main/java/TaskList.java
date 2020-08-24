import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    
    private final List<Task> lst;
    
    public TaskList() {
        this.lst = new ArrayList<>();
    }
    
    public TaskList(List<String> inputLst) {
        String DONE = "1";
        this.lst = new ArrayList<>();
        for (String line : inputLst) {
            String[] splitInput = line.split(" \\| ");
            TaskType taskType = TaskType.valueOf(splitInput[0]);
            switch (taskType) {
                case TODO:
                    this.add(new ToDoTask(splitInput[2], splitInput[1].equals(DONE)));
                    break;
                case DEADLINE:
                    this.add(new DeadlineTask(splitInput[2], splitInput[1].equals(DONE), splitInput[3]));
                    break;
                case EVENT:
                    this.add(new EventTask(splitInput[2], splitInput[1].equals(DONE), splitInput[3]));
                    break;
            }

        }
    }
    
    public void add(Task task) {
        lst.add(task);
    }
    
    public void remove(Task task) {
        lst.remove(task);
    }
    
    public int size() {
        return lst.size();
    }
    
    public Task get(int index) {
        return lst.get(index);
    }
}
