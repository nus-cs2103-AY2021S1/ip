package taskbot.helper;

/**
 * Contains helper functions to be used
 * throughout the program
 */
public class Helper {

    /**
     * Wraps the given string in lines
     * @param s the string to be wrapped
     */
    public static void borderString(String s) {
        System.out.println("----------------------------------------------");
        System.out.println(s);
        System.out.println("----------------------------------------------\n");
    }

    /**
     * Helper function to print the error message of the exception
     * @param e the exception
     */
    public static void handleException(Exception e) {
        borderString(e.getMessage());
    }
}
