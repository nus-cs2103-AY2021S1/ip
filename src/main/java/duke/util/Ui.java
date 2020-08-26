package duke.util;

public class Ui {
    private static String DIVIDER = "____________________________________________________________";
    private static String ERROR_HEADER = "___________________________ERROR!___________________________";

    public void print(String msg) {
        System.out.println(DIVIDER + "\n" + msg + "\n" + DIVIDER);
    }

    public void printErr(String msg) {
        System.out.println(ERROR_HEADER + "\n" + msg + "\n" + DIVIDER);
    }
}
