import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static String face = "/(*´∇｀*)/";
    static String face2 = "(~˘▾˘)~";
    static String face3 = "〆(・∀・＠)";
    static String sadFace = "(>人<)";
    static String spacing = "    ";

    List<Task> ls = new ArrayList<>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        System.out.println(face + spacing + "Hey hey I'm Poco");
        Duke bot = new Duke();
        bot.processInput();

    }

    void processInput() {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        while(!msg.equals("bye")) {
            if(msg.equals("list")) {
                displayList();
            }
            else if(msg.contains("done")) {
                int index = Integer.parseInt(msg.replace("done ", "").trim());
                done(index);
            }
            else if(msg.contains("delete")) {
                int index = Integer.parseInt(msg.replace("delete ", "").trim());
                delete(index);
            }
            else if(msg.contains("todo")){
                addToList(msg.replace("todo ", ""), Type.TODO);
            }
            else if(msg.contains("event")) {
                addToList(msg.replace("event ", ""), Type.EVENT);
            }
            else if(msg.contains("deadline")) {
                addToList(msg.replace("deadline ", ""), Type.DEADLINE);
            }
            else {
                System.out.println(sadFace + spacing + "Sorry, Poco does not understand. Try again?");
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
        if(count == 1) {
            System.out.println("No more tasks! Great job");
        }
    }

    void addToList(String msg, Type type) {
        if(msg.trim().isEmpty()) {
            System.out.println(sadFace + spacing + "Poco noticed that your task is empty");
        } else {
            String[] sp = new String[]{msg};
            switch (type) {
                case TODO:
                    ls.add(new ToDo(msg));
                    break;
                case EVENT:
                    sp = msg.split("/");
                    LocalDateTime ldt = LocalDateTime.parse(sp[1].trim(), formatter);
                    ls.add(new Event(sp[0], ldt));
                    break;
                case DEADLINE:
                    sp = msg.split(" /");
                    LocalDateTime ld = LocalDateTime.parse(sp[1].trim(), formatter);
                    ls.add(new Deadline(sp[0], ld));
                    break;
            }
            System.out.println(face2 + spacing + "Poco has added " + sp[0] + " to your list");
            System.out.println("Pending Tasks: " + ls.size());
        }

    }

    void done(int index) {
        index--;
        if(index < 0 || index >= ls.size()) {
            System.out.println(sadFace + spacing + "Poco cannot find the task: " + index);
        } else {
            ls.get(index).done();
            System.out.println(face2 + spacing + "Good job!");
            System.out.println(ls.get(index).toString());
        }
    }

    void delete(int index) {
        index--;
        if(index < 0 || index >= ls.size()) {
            System.out.println(sadFace + spacing + "Poco cannot find the task: " + index);
        } else {
            System.out.println(face3 + spacing + "Poco has deleted the task");
            System.out.println(ls.get(index).toString());
            ls.remove(index);
        }
    }
}

enum Type {
    //hello this is enum
    TODO, EVENT, DEADLINE
}
