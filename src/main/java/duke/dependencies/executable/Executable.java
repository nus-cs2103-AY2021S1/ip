package duke.dependencies.executable;

import duke.dependencies.task.Task;

// TODO: Leave this for now
public interface Executable {

    CommandType getType();

    Task getTask();

}
