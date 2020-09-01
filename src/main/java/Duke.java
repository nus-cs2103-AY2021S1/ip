
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {
    static String face = "/(*´∇｀*)/";
    static String face2 = "(~˘▾˘)~";
    static String face3 = "〆(・∀・＠)";
    static String sadFace = "(>人<)";
    static String spacing = "    ";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Storage storage;
    private TaskList tasks;
    private UI ui;


    public static void main(String[] args) {
        System.out.println(face + spacing + "Hey hey I'm Poco");
        Duke bot = new Duke("data/duke.txt");
        bot.processInput();
    }

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    /**
     * Processes each command line by line,
     * calling the corresponding methods
     * Exits when user inputs "bye"
     */
    void processInput() {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        while(!msg.equals("bye")) {
            if(msg.equals("list")) {
                displayList();
            } else if(msg.contains("done")) {
                int index = Integer.parseInt(msg.replace("done ", "").trim());
                done(index);
            } else if(msg.contains("delete")) {
                int index = Integer.parseInt(msg.replace("delete ", "").trim());
                delete(index);
            } else if(msg.contains("todo")){
                addToList(msg.replace("todo ", ""), Type.TODO);
            } else if(msg.contains("event")) {
                addToList(msg.replace("event ", ""), Type.EVENT);
            } else if(msg.contains("deadline")) {
                addToList(msg.replace("deadline ", ""), Type.DEADLINE);
            } else if(msg.contains("find")) {
                find(msg.replace("find ", ""));
            } else {
                System.out.println(sadFace + spacing + "Sorry, Poco didn't understand. Try again?");
            }

            msg = sc.nextLine();
        }
        sc.close();
        System.out.println(face + spacing + "Bye bye. Talk again soon!");
    }

    /**
     * Prints each item in the task list
     */
    void displayList() {
        System.out.println(face3);
        if(tasks.size() == 0) {
            System.out.println("Yay, all done!");
        } else {
            System.out.println(tasks.toString());
        }
    }

    /**
     * Adds corresponding task to task list
     * @param msg
     * @param type
     */
    void addToList(String msg, Type type) {
        if(msg.trim().isEmpty()) {
            System.out.println(sadFace + spacing + "Poco noticed that your task is empty");
        } else {
            String[] sp = new String[]{msg};
            switch (type) {
                case TODO:
                    tasks.add(new ToDo(msg));
                    break;
                case EVENT:
                    sp = msg.split("/");
                    LocalDateTime ldt = LocalDateTime.parse(sp[1].trim(), formatter);
                    tasks.add(new Event(sp[0], ldt));
                    break;
                case DEADLINE:
                    sp = msg.split("/");
                    LocalDateTime ld = LocalDateTime.parse(sp[1].trim(), formatter);
                    tasks.add(new Deadline(sp[0], ld));
                    break;
            }
            System.out.println(face2 + spacing + "Poco has added " + sp[0] + " to your list");
            System.out.println("Pending Tasks: " + tasks.size());
        }
        storage.saveFile(tasks);
    }

    /**
     * Marks the task at index in task list as done
     * @param index
     */
    void done(int index) {
        index--;
        if(index < 0 || index >= tasks.size()) {
            System.out.println(sadFace + spacing + "Poco cannot find the task: " + index);
        } else {
            tasks.done(index);
            System.out.println(face2 + spacing + "Good job!");
            System.out.println(tasks.get(index).toString());
        }
        storage.saveFile(tasks);
    }

    /**
     * Deletes the task at index in task list
     * @param index
     */
    void delete(int index) {
        index--;
        if(index < 0 || index >= tasks.size()) {
            index++;
            System.out.println(sadFace + spacing + "Poco cannot find the task: " + index);
        } else {
            System.out.println(face3 + spacing + "Poco has deleted the task");
            System.out.println(tasks.get(index).toString());
            tasks.remove(index);
        }
        storage.saveFile(tasks);
    }

    void find(String match) {
        System.out.println(face3);
        System.out.println(tasks.find(match));
    }
}

enum Type {
    TODO, EVENT, DEADLINE
}
