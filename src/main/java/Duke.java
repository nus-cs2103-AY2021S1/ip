import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String line = "____________________________________________________________";
   private static Commands comm;
    public static ArrayList<Task> ListOfItems = new ArrayList<Task>();

    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String introduction = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(line);
        System.out.println(introduction);
        System.out.println(line);
        runDuke();

    }

    private static void runDuke() {
        TaskList taskList = Storage.load(Storage.FILE_PATH);
        Scanner sc = new Scanner(System.in);
        String echo;
        boolean flag = false;

        while (!flag) {
            echo = sc.nextLine();
            try {
                String split = echo;
                String arr[] = split.split(" ", 2);
                String keyword = arr[0];
                Commands command = Commands.findCommand(keyword);
                switch (command) {
                    case EXIT:
                        Storage.save(taskList, Storage.FILE_PATH);
                        System.out.println(line);
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(line);
                    flag = true;
                    break;
                    case LIST:
                        addLines(taskList.printOutList());
                        break;
                    case DONE:
                        try {
                            int index = Integer.parseInt(arr[1]) - 1;
                            addLines(taskList.markCompleted(index));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("Integer not detected"));
                            break;
                        }
                    case DEADLINE:
                        try {
                            String obtainDate = arr[1];
                            String arr2[] = obtainDate.split("/by", 2);
                            String descrip = arr2[0];
                            String date = arr2[1];
                            Deadline item = new Deadline(descrip, date);
                            addLines(taskList.add(item));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e));
                        }
                    case TODO:
                        try {
                            ToDo item = new ToDo(arr[1]);
                            addLines(taskList.add(item));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e));
                            break;
                        }
                    case EVENT:
                        try {
                            String obtainDate = arr[1];
                            String arr2[] = obtainDate.split("/at", 2);
                            String descrip = arr2[0];
                            String date = arr2[1];
                            Event item = new Event(descrip, date);
                            addLines(taskList.add(item));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a event cannot be empty.", e));
                            break;
                        }
                    case DELETE:
                        try {

                            int index2 = Integer.parseInt(arr[1]) - 1;
                            addLines(taskList.deleteTask(index2));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! There is no task at that list number to delete!", e));
                            break;
                        }
                }



            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }




    private static void addLines(String content) {
        System.out.print(line);
        System.out.print(content);
        System.out.println(line);
    }
}


