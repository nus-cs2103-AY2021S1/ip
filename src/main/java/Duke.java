import main.java.*;


import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String intro = "____________________________________________________________ \n" +
                        "Hello! I'm Duke \n" + "What can I do for you? \n" +
                        "____________________________________________________________";
        System.out.println(intro);
        Scanner input =  new Scanner(System.in);
        String underscore = "____________________________________________________________ \n";
        String line;
        ArrayList<Task> todo = new ArrayList<>();
        while(input.hasNextLine()) {
            try {
                line = input.nextLine();
                if (line.equals("bye")) {
                    System.out.println(underscore + " Bye. Hope to see you again soon!" + "\n" + underscore);
                    break;
                } else if (line.equals("list")) {
                    System.out.println(underscore);
                    if(todo.size() == 0){
                        System.out.println("you do not have any tasks yet");
                    } else {
                        for (int i = 0; i < todo.size(); i++) {
                            int number = i + 1;
                            System.out.println(" " + number + "." + todo.get(i));
                        }
                    }
                    System.out.println(underscore);
                } else if (line.indexOf("done") == 0 || line.indexOf("delete") == 0) {
                    int number = Character.getNumericValue(line.charAt(line.length() - 1));
                    if(line.indexOf("done") == 0) {
                        todo.get(number - 1).checkOff();
                        System.out.println(underscore + "Nice! I've marked this task as done: \n" +
                                todo.get(number - 1) + "\n" + underscore
                        );
                    } else {
                        if (number - 1 > todo.size() || number - 1 < 0 || todo.size() == 0){
                            throw new DukeException("Invalid task number");
                        } else {
                            int size = todo.size() - 1;
                            System.out.println(underscore + "Noted. I've removed this task: \n"
                                + todo.get(number - 1) + "\n" + "Now you have " + size + " task in the list \n"
                                + underscore
                            );
                            todo.remove(number - 1);
                        }
                    }
                } else {
                    if (line.indexOf("todo") == 0) {
                        String[] splits = line.split("todo");
                        if(splits.length > 1) {
                            Todo task = new Todo(splits[1]);
                            todo.add(task);
                            System.out.println(underscore + "Got it. I've added this to task: \n" + task + "\n"
                                    + "Now you have " + todo.size() + " tasks in the list \n" + underscore
                            );
                        } else {
                            throw new DukeInvalidTaskException();
                        }
                    } else if (line.indexOf("deadline") == 0) {
                        String[] splits = line.split("deadline |/by ");
                        if (splits.length > 2) {
                            Deadline task = new Deadline(splits[1], splits[2]);
                            todo.add(task);
                            System.out.println(underscore + "Got it. I've added this to task: \n" + task + "\n"
                                    + "Now you have " + todo.size() + " tasks in the list \n" + underscore
                            );
                        } else if (splits.length > 1) {
                            throw new DukeInvalidDateException();
                        } else {
                            throw new DukeInvalidTaskException();
                        }
                    } else if (line.indexOf("event") == 0) {
                        String[] splits = line.split("event |/at");
                        if (splits.length > 2){
                            Event task = new Event(splits[1], splits[2]);
                            todo.add(task);
                            System.out.println(underscore + "Got it. I've added this to task: \n" + task + "\n"
                                    + "Now you have " + todo.size() + " tasks in the list \n" + underscore
                            );
                        } else if (splits.length > 1){
                            throw new DukeInvalidDayException();
                        } else {
                            throw new DukeInvalidTaskException();
                        }
                    } else {
                        throw new DukeException("I'm sorry i don't know what you mean :(");
                    }
                }
            } catch (DukeInvalidDateException err){
                System.out.println(err.getMessage());
            } catch (DukeInvalidDayException err) {
                System.out.println(err.getMessage());
            } catch (DukeInvalidTaskException err) {
                System.out.println(err.getMessage());
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
        }
        input.close();
    }
}
