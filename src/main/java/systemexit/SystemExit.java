package systemexit;

/**
 * Exits system if developers' code causes any odd data transmissions / bugs.
 */
public class SystemExit {
    /**
     * Terminates Duke according to error keyword received.
     *
     * @param keyword Error keywords - "add error", "todo error", "done error", "find error".
     * @return Empty String.
     */
    public static String terminateDuke(String keyword) {
        if (keyword.equals("add error")) {
            System.err.println("Something went wrong while adding tasks. Exiting system.");
        } else if (keyword.equals("todo error")) {
            System.err.println("Something went wrong while adding todo. Exiting system.");
        } else if (keyword.equals("done error")) {
            System.err.println("Something went wrong while marking task as done. Exiting system.");
        } else if (keyword.equals("find error")) {
            System.err.println("Something went wrong while finding tasks. Exiting system.");
        } else { // Generic termination message
            System.err.println("Something went wrong. Exiting system.");
        }

        int statusCode = -1;
        System.exit(statusCode);
        return "";
    }
}
