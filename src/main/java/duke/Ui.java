package duke;

/**
 * Deals with the user interface.
 */
public class Ui {

    /**
     * Prints a greeting message.
     */
    public void greet() {
        String greeting = "Hello mah dud, itza handsome robo speakin\n"
                + "What duh hell du yu wan?";
        System.out.println(greeting);
    }

    /**
     * Prints a farewell message.
     */
    public void farewell() {
        String farewell = "Never come back,\n"
                + "dun wanna see yu ever agin";
        System.out.println(farewell);
    }

//    public String readCommand() {
//        Scanner sc = new Scanner(System.in);
//        String nextLine = sc.nextLine();
//        sc.close();
//        return nextLine;
//    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println("________________________________________");
    }

    /**
     * Prints the error message.
     *
     * @param e String of error to be printed.
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Prints the loading error message.
     *
     * @param e String of loading error to be printed.
     */
    public void showLoadingError() {
        System.out.println("Error when loading file...");
    }
}
