public class GreetExit {
    public void greet() {
        String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Helper.horiLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you ?");
        System.out.println(Helper.horiLine);
    }
    public void exit() {
        System.out.println(Helper.horiLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Helper.horiLine);
    }
}