package dependencies.storage;

import java.util.*;
import static dependencies.storage.CompletionState.*;

public class Store {
    private final String[] todoList;
    private final static HashSet<String> HASH_SET = new HashSet<>();
    private final static HashMap<Long, CompletionState> HASH_MAP = new HashMap<>();
    private int todoIdx = 0;

    private Store(int c) {
        todoList = new String[c];
    }

    public static Store initStorage(int storageCapacity) {
        return new Store(storageCapacity);
    }

    public String getTodos() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= todoIdx; i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(todoList[i]);
            if (i != todoIdx) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }




}
