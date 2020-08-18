public class StringIdentifier {
    private static boolean isProgramRunning = true;

    public boolean isRunning() {
        return this.isProgramRunning;
    }

    public void checker(String str) {
        System.out.println("    ____________________________________________________________");
        if (str.equals("bye")) {
            close();
        } else {
            display(str);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public void display(String str) {
        System.out.println("    " + str);
    }

    public void close() {
        this.isProgramRunning = false;
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
