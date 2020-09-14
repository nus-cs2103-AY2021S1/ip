package duke.helpers;

import java.util.HashMap;

/**
 * ShortCuts class is used to define shortcuts and access defined shortucts
 */
public class ShortCuts {
    private static HashMap<String, String> shortCuts = new HashMap<>();
    //Contains the shortcuts, where the Key is the short form and the value is the original value

    /**
     * Constructor to insert shortCuts into shortCuts hashMap
     *
     * @param shortCut is the key for the hashMap
     * @param originalForm is the value for the hashMap
     */
    private ShortCuts(String shortCut, String originalForm) {
        shortCuts.put(shortCut, originalForm);
    }
    /**
     * gets the shortCuts and sets default values of short cut to proper keywords
     *
     * @return shortCuts
     */
    public static HashMap<String, String> getShortCuts() {
        defaultShortCut(); //sets the default short cuts to shortcuts
        return shortCuts;
    }

    /**
     * default short cuts provided by Duke
     */
    public static void defaultShortCut() {
        shortCuts.put("s", "short"); //s is short form for short
        shortCuts.put("b", "bye"); //b is short form for bye
        shortCuts.put("l", "list"); //l is short form for list
        shortCuts.put("d", "delete"); //d is short form for delete
        shortCuts.put("do", "done"); //do is short form for done
        shortCuts.put("t", "todo"); //t is short form for todo
        shortCuts.put("e", "event"); //e is short form for event
        shortCuts.put("de", "deadline"); //de is short form for deadline
        shortCuts.put("f", "find"); //f is short for find
    }

    /**
     * Adds shortcuts to the shortCuts hashMap given by user
     *
     * @param originalForm is the actual keyword
     * @param shortForm the short form given by user
     */
    public static void addShortCut(String originalForm, String shortForm) {
        new ShortCuts(shortForm, originalForm);
        //calls constructor which then puts originalForm into key and shortForm into value
    }

    /**
     * Checks whether the input contains the short cut
     *
     * @param input given by user
     * @return boolean value on whether the keyword given by the user is a defined short cut.
     */
    public static boolean containsShortCut(String input) {
        defaultShortCut();
        String keyWord = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') { //space indicates that the keyword is over.
                break;
            }
            keyWord = keyWord + input.charAt(i);
        }
        return shortCuts.containsKey(keyWord); //checks whether hashmap contains keyword.
    }

}
