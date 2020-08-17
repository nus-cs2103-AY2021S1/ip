package dependencies.storage;

import java.util.*;
import static dependencies.storage.CompletionState.*;

public class Store {
    /** todoList that stores the tasks. */
    private final String[] todoList;
    private int todoIdx = 0;

    /** Hashmaps and sets to keep track of the tasks. */
    private final static HashSet<String> HASH_SET = new HashSet<>();
    private final static HashMap<Long, CompletionState> HASH_MAP = new HashMap<>();

    /** Private constructor */
    private Store(int c) {
        todoList = new String[c];
    }

    /**
     * Initializer for the Store object.
     *
     * @param storageCapacity
     * @return the Store object
     */
    public static Store initStorage(int storageCapacity) {
        return new Store(storageCapacity);
    }

    /**
     * Returns a String in the form of a list with \n appended
     * at the end of each item.
     *
     * @return todoList in a form of a String
     */
    public String getTodosInList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < todoIdx; i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(todoList[i]);
            if (i != todoIdx) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Adds the specified task to the todoList.
     *
     * @param task
     */
    public void add(String task) {
        todoList[todoIdx++] = task;
    }

    public void deleteTask(int i) {

    }




}
