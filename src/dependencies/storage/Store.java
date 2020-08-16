package dependencies.storage;

import java.util.*;
import static dependencies.storage.CompletionState.*;

public class Store {
    private final String[] todoList;
    private final static HashSet<String> HASH_SET = new HashSet<>();
    private final static HashMap<Long, CompletionState> HASH_MAP = new HashMap<>();

    private Store(int c) {
        todoList = new String[c];
    }

    public static Store initStorage(int storageCapacity) {
        return new Store(storageCapacity);
    }




}
