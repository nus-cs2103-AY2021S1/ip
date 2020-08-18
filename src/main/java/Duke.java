
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
    public static void output(){
        System.out.println("  ____________________________________________________________\n" + "  Hello! I'm Duke\n" + "  What can I do for you?\n" +
                "  ____________________________________________________________");
            for (String string : todos) {
                if (string.length() >= 3 && string.equals("bye")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    System.out.println("  Bye. Hope to see you again soon!\n" + "  ____________________________________________________________");
                    break;
                }
                else if (string.length() >= 4 && string.equals("list")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    Task.listing();
                }
                else if (string.length() >= 4 && string.substring(0, 4).equals("done")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    if(string.length() == 4 || string.length() == 5){
                        System.out.println(new DoneException(true).toString());
                        continue;
                    }
                    int ID = Integer.parseInt(string.substring(5));
                    if(ID >= Task.tasks.size()){
                        System.out.println(new DoneException(false).toString());
                        continue;
                    }
                    Task.tasks.get(ID - 1).setDone();
                    Task.tasks.get(ID - 1).donePrint();
                }
                else if (string.length() >= 4 && string.substring(0, 4).equals("todo")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    if(string.length() == 4 || string.length() == 5){
                        System.out.println(new TodoException().toString());
                    }else {
                        todo t = new todo(string.substring(5));
                        t.output();
                    }
                }
                else if (string.length() >= 5 && string.substring(0, 5).equals("event")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    if(string.length() == 5 || string.length() == 6){
                        System.out.println(new EventException(true, false).toString());
                        continue;
                    }
                    String s = "";
                    int index = -1;
                    boolean time = false;
                    for (int i = 5; i < string.length(); i++) {
                        if (string.charAt(i) == '/') {
                            index = i;
                            time = true;
                            break;
                        }
                        s = s + string.charAt(i);
                    }
                    if(!time){
                        System.out.println(new EventException(true, false).toString());
                        continue;
                    }
                    event e = new event(s.substring(1, s.length() - 1), string.substring(index + 4));
                    e.output();
                    //event e = new event(string.substring())
                }else if (string.length() >= 8 && string.substring(0, 8).equals("deadline")) {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    if(string.length() == 8 || string.length() == 9){
                        System.out.println(new EventException(true, false).toString());
                        continue;
                    }
                    String s = "";
                    int index = -1;
                    boolean time = false;
                    for (int i = 8; i < string.length(); i++) {
                        if (string.charAt(i) == '/') {
                            index = i;
                            time = true;
                            break;
                        }
                        s = s + string.charAt(i);
                    }
                    if(!time){
                        System.out.println(new EventException(true, false).toString());
                        continue;
                    }
                    deadline e = new deadline(s.substring(1, s.length() - 1), string.substring(index + 4));
                    e.output();
                } else {
                    System.out.println("\n" + string + "\n  ____________________________________________________________");
                    System.out.println(new WrongInputException().toString());
                }
                //System.out.println(string);
            }

    }
    public static void main(String[] args) throws FileNotFoundException {
        scan();
        output();
    }
}
