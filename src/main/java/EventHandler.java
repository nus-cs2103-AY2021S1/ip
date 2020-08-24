import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventHandler {
    public static String iterateToDo(List<Task> arr) {
        String output = "";
        int counter = 1;
        for (Task task : arr) {
            if (task == null) {
                break;
            } else {
                output += Integer.toString(counter) + ". " + task.toString() + "\n";
                counter++;
            }
        }
        return output;
    }

    public static void handle() throws Exception {
        String hor_line = "____________________________________\n";
        List<Task> todo_list = new ArrayList<Task>();
        todo_list = FileParser.loadData(todo_list);
        String logo = " _ .-') _               .-. .-')     ('-.  \n"
                + "( (  OO) )              \\  ( OO )  _(  OO)  \n"
                + " \\     .'_  ,--. ,--.   ,--. ,--. (,------. \n"
                + " ,`'--..._) |  | |  |   |  .'   /  |  .---' \n"
                + " |  |  \\  ' |  | | .-') |      /,  |  |     \n"
                + " |  |   ' | |  |_|( OO )|     ' _)(|  '--.  \n"
                + " |  |   / : |  | | `-' /|  .   \\   |  .--'  \n"
                + " |  '--'  /('  '-'(_.-' |  |\\   \\  |  `---. \n"
                + " `-------'   `-----'    `--' '--'  `------' \n";

        System.out.println("Hello from\n" + logo);
        System.out.println(hor_line + "Hello! I'm Duke la\n" + "What can I do for you ah?\n" + hor_line);
        int counter = todo_list.size();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            try {
                if (command.equals("bye")) {
                    System.out.println(hor_line + "Bye bye. See you soon bro!\n" + hor_line);
                    sc.close();
                    break;
                } else if (command.equals("list")) {
                    System.out.println(hor_line + "Here are the things you need to do lor: \n");
                    System.out.println(iterateToDo(todo_list) + hor_line);
                } else if (command.startsWith("delete")) {
                    if (command.equals("delete")) {
                        throw new ResponseException(hor_line + "☹ AIYO!!! Please state which task to delete la... \n" +
                                hor_line);
                    } else {
                        String index = command.substring(command.length() - 1);
                        int number = Integer.parseInt(index);
                        System.out.println(hor_line + "Task deleted liao: \n" + todo_list.get(number - 1).toString() + "\n" +
                                "You got " + Integer.toString(counter - 1) + " tasks left. \n" + hor_line);
                        todo_list.remove(number - 1);
                        FileParser.writeData(todo_list);
                        counter --;
                    }
                } else if (command.startsWith("done")) {
                    if (command.equals("done")) {
                        throw new ResponseException(hor_line + "☹ AIYO!!! Please state which task is done la... \n" +
                                hor_line);
                    } else {
                        String index = command.substring(command.length() - 1);
                        int number = Integer.parseInt(index) - 1;
                        todo_list.set(number, todo_list.get(number).markDone());
                        System.out.println(hor_line + "Swee! Now I will mark this as done: \n" +
                                todo_list.get(number).toString() + "\n" + hor_line);
                        FileParser.writeData(todo_list);
                    }
                } else if (command.startsWith("todo")) {
                    if (command.equals("todo")) {
                        throw new ResponseException(hor_line + "☹ AIYOYO!!! The description of a todo cannot be empty la... \n"
                                + hor_line);
                    } else {
                        String instructions = command.substring(5);
                        todo_list.add(counter, new Todo(false, counter + 1, instructions));
                        System.out.println(hor_line + "Okok. I add for you: \n" +
                                todo_list.get(counter).toString() + "\n" +
                                "You got " + Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                        FileParser.writeData(todo_list);
                        counter++;
                    }
                } else if (command.startsWith("deadline")) {
                    if (command.equals("deadline")) {
                        throw new ResponseException(hor_line +
                                "☹ AIYO!!! The description of a deadline cannot be empty la... \n" + hor_line);
                    } else {
                        String instructions = command.substring(9);
                        String[] arr = instructions.split("/by");
                        instructions = arr[0].substring(0, arr[0].length() - 1);
                        String date = arr[1].substring(1);
                        todo_list.add(counter, new Deadline(false, counter + 1, instructions, date));
                        System.out.println(hor_line + "Okok. I help you add this task: \n" +
                                todo_list.get(counter).toString() + "\n" + "You got " +
                                Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                        FileParser.writeData(todo_list);
                        counter++;
                    }
                } else if (command.startsWith("event")) {
                    if (command.equals("event")) {
                        throw new ResponseException(hor_line + "☹ AIYOYO!!! The description of a event cannot be empty la... \n"
                                + hor_line);
                    } else {
                        String instructions = command.substring(5);
                        String[] arr = instructions.split("/at");
                        instructions = arr[0].substring(0, arr[0].length() - 1);
                        String time = arr[1].substring(1);
                        todo_list.add(counter, new Event(false, counter + 1, instructions, time));
                        System.out.println(hor_line + "Okay. I've added this task: \n"
                                + todo_list.get(counter).toString() + "\n" +  "You got " +
                                Integer.toString(counter + 1) + " tasks in the list.\n" + hor_line);
                        FileParser.writeData(todo_list);
                        counter++;
                    }
                }else {
                    throw new ResponseException(hor_line + "☹ AIYO!!! What do you mean sia? :-( \n"
                            + hor_line);
                }
            } catch (ResponseException exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
