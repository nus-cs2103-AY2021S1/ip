import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static String face = "(づ￣ ³￣)づ";
    static String face2 = "(~˘▾˘)~";
    static String face3 = "〆(・∀・＠)";
    static String sadFace = "(>人<)";
    static String spacing = "    ";

    List<Task> ls = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(face + spacing + "Hey hey I'm Poco");
        Duke bot = new Duke();
        bot.processInput();

    }

    void processInput() {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine().toLowerCase();
        while(!msg.equals("bye")) {
            if(msg.equals("list")) {
                displayList();
            }
            else if(msg.contains("done")) {
                done(msg);
            }
            else {
                addToList(msg);
            }

            msg = sc.nextLine();
        }
        sc.close();
        System.out.println(face + spacing + "Bye bye. Talk again soon!");
    }

    void displayList() {
        int count = 1;
        System.out.println(face3);
        for(Task todo: ls) {
            System.out.println(count + ". " + todo.toString());
            count++;
        }
    }

    void addToList(String msg) {
        ls.add(new Task(msg));
        System.out.println(face2 + spacing + "Poco has added " + msg + " to your list");

    }

    void done(String msg) {
        int index = Integer.parseInt(msg.split(" ")[1]) - 1;
        if(index < 0 || index >= ls.size()) {
            System.out.println(sadFace + spacing + "Poco cannot find the task: " + index);
        } else {
            ls.get(index).done();
            System.out.println(face2 + spacing + "Good job!");
            System.out.println(ls.get(index).toString());
        }
    }
}
