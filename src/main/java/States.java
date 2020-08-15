import java.util.function.BiFunction;
import java.util.function.Function;

public enum States{

    //The main State of tasks
    TODO(Todo::new),
    EVENT(Event::new),
    DEADLINE(Deadline::new);

    //Create a variable to store the Function / Bi Function
    protected Function<String, Task> taskSupplier = null;
    protected BiFunction<String, String, Task> taskSupplier1 = null;

    //Constructor for the function
    States(Function<String, Task> taskSupplier){
        this.taskSupplier = taskSupplier;
    }


    //Constructor if it is a Bi Function
    States(BiFunction<String, String, Task> taskSupplier){
        this.taskSupplier1 = taskSupplier;
    }

    //Get the task given description
    public Task createTask(String description){
        return taskSupplier.apply(description);
    }

    //Create the task given desciption and date
    public Task createTask(String description, String date){
        return taskSupplier1.apply(description, date);
    }

}