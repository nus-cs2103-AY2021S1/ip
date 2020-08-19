public class GreetExit {
    public static String horiLine = "";
    GreetExit() {
        for (int i = 1; i <= 30; i++) horiLine += "-";
    }
    public void greet() {
        String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horiLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you ?");
        System.out.println(horiLine);
    }
    public void exit() {
        System.out.println(horiLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horiLine);
    }
}