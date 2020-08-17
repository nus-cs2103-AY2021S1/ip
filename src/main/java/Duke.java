
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> todos = new ArrayList<>();
    public static void scan(){
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()) {
            do {
                todos.add(sc.nextLine());
            } while (sc.hasNextLine());
        }
    }
    public static void scan(FileInputStream inputStream){
        try {
            int size = inputStream.available();
            StringBuffer text = new StringBuffer();
            for(int i = 0; i < size; i++){
                char c = (char)inputStream.read();
                text.append(c);
            }
            String s = " ";
            for(int i = 0 ; i < size; i++){
                if(text.toString().charAt(i) == 10){
                    todos.add(s.substring(1));
                    s = " ";
                }else {
                    s = s + text.toString().charAt(i);
                }
            }
            todos.add(s.substring(1));
        }catch (Exception e){
            System.out.println();
        }
    }
    public static void output(){
        System.out.println("  ____________________________________________________________\n" + "  Hello! I'm Duke\n" + "  What can I do for you?\n" +
                "  ____________________________________________________________");
            for (String string : todos) {
                if (string.equals("bye")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    System.out.println("  Bye. Hope to see you again soon!\n" + "  ____________________________________________________________");
                    break;
                }
                else if (string.equals("list")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    Task.listing();
                }
                else if (string.substring(0, 4).equals("done")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    int ID = Integer.parseInt(string.substring(5));
                    Task.tasks.get(ID - 1).setDone();
                    Task.tasks.get(ID - 1).donePrint();
                }
                else if (string.substring(0, 4).equals("todo")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                        todo t = new todo(string.substring(5));
                        t.output();
                }
                else if (string.substring(0, 5).equals("event")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    String s = "";
                    int index = -1;
                    for (int i = 5; i < string.length(); i++) {
                        if (string.charAt(i) == '/') {
                            index = i;
                            break;
                        }
                        s = s + string.charAt(i);
                    }
                    event e = new event(s.substring(1, s.length() - 1), string.substring(index + 4));
                    e.output();
                    //event e = new event(string.substring())
                }

                else if (string.substring(0, 8).equals("deadline")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    String s = "";
                    int index = -1;
                    for (int i = 8; i < string.length(); i++) {
                        if (string.charAt(i) == '/') {
                            index = i;
                            break;
                        }
                        s = s + string.charAt(i);
                    }
                    deadline e = new deadline(s.substring(1, s.length() - 1), string.substring(index + 4));
                    e.output();
                } else {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                }
                //System.out.println(string);
            }

    }
    public static void main(String[] args) throws FileNotFoundException {
        scan();
        output();

    }
}
