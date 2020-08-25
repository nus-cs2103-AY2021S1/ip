package duck.storage;

import duck.exception.DuckException;
import duck.task.TaskList;

public interface Storage {
     void save(TaskList task) throws DuckException;
     TaskList load() throws DuckException;
}