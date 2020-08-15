import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        try {
            String file = args[0];
            FileInputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            ArrayList<String> strings = new ArrayList<>();
            StringBuffer text = new StringBuffer();
            for(int i = 0; i < size; i++){
                char c = (char)inputStream.read();
                text.append(c);
            }
            String s = "";
            for(int i = 0 ; i < size; i++){
                if(text.toString().charAt(i) == 10){
                    strings.add(s);
                    s = "";
                }
                s = s + text.toString().charAt(i);
            }
            strings.add(s);
            for(String string : strings){
                System.out.println(string);
                if(string.equals("bye")){
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

    }
}
