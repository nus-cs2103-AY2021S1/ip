package main.java;

public class Duke {
    public static final String INDENTATION = "    ";
    public static final String DIVIDER = "____________________________________________________________";
    public static final String GREETING = "Hello! I am Smith\n" + "What can I do for you?";
    public static final String EXITMESSAGE = "Bye. Hope to see you again soon!";

    public static String makeBlock(String string) {
        String[] strings = string.split("\n");
        String result = INDENTATION + DIVIDER + "\n";
        for(int i = 0; i < strings.length; i = i + 1) {
            result = result + INDENTATION + strings[i] + "\n";
        }
        result = result + INDENTATION + DIVIDER + "\n";
        return  result;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println(makeBlock(logo + "\n" + GREETING));
        //System.out.println(("    1      ").split(" ").length);
        LevelEight.interact();
   }
}
