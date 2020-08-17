package dependencies.executable;

import dependencies.task.Task;

// TODO: Leave this for now
public interface Executable {

    CommandType getType();

    Task getTask();

}
