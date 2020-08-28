package duke.ui;

public class Ui {
    private final static String DIVIDER = "____________________________________________________________";
    private final static String ERROR_HEADER = "___________________________ERROR!___________________________";

    public void print(String msg) {
        System.out.print(DIVIDER + "\n" + msg + "\n" + DIVIDER + "\n");
    }

    public void printErr(String msg) {
        System.out.print(ERROR_HEADER + "\n" + msg + "\n" + DIVIDER + "\n");
    }
}
