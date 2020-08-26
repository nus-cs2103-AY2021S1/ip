package rogue.logic.directives;

import java.util.HashMap;
import java.util.Map;

public enum Action {
    ADD_TODO        ("todo"),
    ADD_DEADLINE    ("deadline"),
    ADD_EVENT       ("event"),
    LIST            ("list"),
    DELETE          ("delete"),
    MARK_AS_DONE    ("done"),
    EXIT            ("bye"),
    INVALID         ("");

    private final String keyword;

    private static final Map<String, Action> ACTION_KEYWORD_MAP = new HashMap<>();

    // Cache the keyword and its corresponding action for reverse lookup
    static {
        for (Action action : values()) {
            ACTION_KEYWORD_MAP.put(action.keyword, action);
        }
    }

    Action(String keyword) {
        this.keyword = keyword;
    }

    public static Action getAction(String keyword) {
        Action action = ACTION_KEYWORD_MAP.get(keyword);
        return (action != null) ? action : Action.INVALID;
    }
}
