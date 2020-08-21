public class Ui {
    private static final String horizontalLine = "\t=================================================================================";

    public void print(String str) {
        System.out.println(str);
    }

    private String output(String message) {
        return horizontalLine + "\n\t  " + message + "\n" + horizontalLine + "\n";
    }
}
