import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> strings = new ArrayList<String>(100);
        String str;
        str = sc.nextLine();
        while (!str.equals("bye")){
            System.out.println("-----------------");
                if (str.equals("list")){
                    int i = 0;
                    for(String string : strings) {
                        System.out.println(String.format("%d. %s", ++i, string));
                    }
                }else{
                    System.out.println("Added: "+str);
                    strings.add(str);
                }
            System.out.println("-----------------");
            str = sc.nextLine();
        }
        System.out.println("-----------------");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("-----------------");
    }
}
