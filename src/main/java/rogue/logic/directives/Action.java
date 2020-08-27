package rogue.logic.directives;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps each possible action that {@code Rogue} can take to
 * a list of keywords. These keywords are entered by the user as inputs
 * to execute the corresponding actions.
 */
public enum Action {
    ADD_TODO        ("todo"),
    ADD_DEADLINE    ("deadline"),
    ADD_EVENT       ("event"),
    LIST            ("list"),
    FIND            ("find"),
    DELETE          ("delete"),
    MARK_AS_DONE    ("done"),
    EXIT            ("bye"),
    INVALID         ("");

    private final String keyword;

    /** Stores keyword to action mappings. */
    private static final Map<String, Action> ACTION_KEYWORD_MAP = new HashMap<>();

    // Caches the keyword and its corresponding action for fast reverse lookup. */
    static {
        for (Action action : values()) {
            ACTION_KEYWORD_MAP.put(action.keyword, action);
        }
    }

    /**
     * Constructs an {@code Action}.
     *
     * @param keyword The keyword that is mapped to the action.
     */
    Action(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Performs a reverse lookup for {@code Action} given a keyword.
     * {@code Action.INVALID} is returned if the keyword is not mapped
     * to any {@code Action}.
     *
     * @param keyword The keyword to be searched
     * @return An {@code Action} matching the keyword, or {@code Action.INVALID} if no match.
     */
    public static Action getAction(String keyword) {
        Action action = ACTION_KEYWORD_MAP.get(keyword);
        return (action != null) ? action : Action.INVALID;
    }
}
