package duke.task.todo;

import duke.task.Task;

import java.time.LocalDateTime;

public class ToDo extends Task {
    public ToDo(String name, LocalDateTime date){
        super(name, date);
    }

    public ToDo(String line, boolean isAutomated) {
        super(line);
    }
    @Override
    public String toString(){
        return "[T]"+ super.toString();
    }
}
