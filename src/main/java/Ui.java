public class Ui {
    private static String title = "     CS2103T Individual Project 1\n";

    public static void displayWelcome() {
        System.out.println("    _______________________________________________________________________\n     ***Welcome to Project DUKE***\n"
                            + title
                            + "\n     Hello! I'm Duke"
                            + "\n     What can I do for you?"
                            + "\n    _______________________________________________________________________\n");
    }

    public static void displayLoadFileError() {
        System.out.println("    _______________________________________________________________________"
                + "\n       *Error: File not found*"
                + "\n     Please specify the correct file location relative to your project"
                + "\n     root directory"
                + "\n    _______________________________________________________________________\n");
    }

    public static void displayLoadFileSuccess() {
        System.out.println("    _______________________________________________________________________"
                + "\n       File has successfully loaded!"
                + "\n     Duke Commands: bye, list, todo, event, deadline, delete"
                + "\n    _______________________________________________________________________\n");
    }

    public static void displayUserInputError(String message) {
        System.out.println("       " + message
                + "\n    _______________________________________________________________________\n");
    }

    public static void displayUpdateFileError(String message) {
        System.out.println("    _______________________________________________________________________"
                + "\n       *Error: Unable to save changes to file*"
                + "\n     Please check if your file still exists in your directory"
                + "\n     " + message
                + "\n    _______________________________________________________________________\n");
    }
}
