package duck.storage;

import duck.exception.DuckException;
import duck.task.TaskList;

/**
 * Storage interface to be implemented to store TaskList data.
 */
public interface Storage {
     void save(TaskList task) throws DuckException;
     TaskList load() throws DuckException;
}