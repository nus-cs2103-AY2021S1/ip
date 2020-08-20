import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) throws DukeException {
        try {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("Hello! I'm Duke \n"
                    + "What can I do for you?");
            Scanner sc = new Scanner(System.in);
            List<Input> inputs = new ArrayList<>();
            while (true) {
                String nextLine = sc.nextLine();
                if (nextLine.startsWith("done")) {
                    if (nextLine.equals("done") || nextLine.equals("done ")) {
                        throw new DukeException("OOPS!!! The description of done cannot be empty.");
                    }
                    int numTaskDone = Integer.valueOf(nextLine.substring(5));
                    if (numTaskDone > inputs.size()) {
                        throw new DukeException("OOPS!!! Task does not exist.");
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    Input inputType = inputs.get(numTaskDone - 1);
                    inputType.taskDone();
                    System.out.println("[/] " + inputType.content + " " + inputType.time);
                } else {
                    if (nextLine.startsWith("todo")) {
                        if (nextLine.equals("todo") || nextLine.equals("todo ")) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(nextLine.substring(5));
                        inputs.add(todo);
                        int count = inputs.size();
                        System.out.println("Got it. I've added this task: \n" + "  [T][x] " + todo.content +
                                "\n Now you have " + count + " tasks in the list");
                    } else if (nextLine.startsWith("deadline")) {
                        if (nextLine.equals("deadline") || nextLine.equals("deadline ")) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        int charLoc = nextLine.indexOf("/by");
                        Deadline deadline = new Deadline(nextLine.substring(9, charLoc), nextLine.substring(charLoc + 3));
                        inputs.add(deadline);
                        int count = inputs.size();
                        System.out.println("Got it. I've added this task: \n" + "  [D][x] " + deadline.content +
                                deadline.time + "\n Now you have " + count + " tasks in the list");
                    } else if (nextLine.startsWith("event")) {
                        if (nextLine.equals("event") || nextLine.equals("event ")) {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                        }
                        int charLoc = nextLine.indexOf("/at");
                        Event event = new Event(nextLine.substring(6, charLoc), nextLine.substring(charLoc + 3));
                        inputs.add(event);
                        int count = inputs.size();
                        System.out.println("Got it. I've added this task: \n" + "  [E][x] " + event.content +
                                event.time + "\n Now you have " + count + " tasks in the list");

                    } else if (nextLine.equals("list")) {
                        System.out.println("Here are the tasks in your list:");
                        int len = inputs.size();
                        for (int i = 1; i <= len; i++) {
                            Input inputType = inputs.get(i - 1);
                            if (inputType.done) {
                                System.out.println(i + ". " + inputType.id + "[/] " + inputType.content + " " +
                                        inputType.time);
                            } else {
                                System.out.println(i + ". " + inputType.id + "[x] " + inputType.content + " " +
                                        inputType.time);
                            }
                        }
                    } else if (nextLine.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
        }
        catch (DukeException e) {
            System.out.println(e.msg);
        }
    }
        
    public static class Input {
        boolean done;
        String content;
        String id;
        String time;

        Input(String content) {
            this.content = content;
            boolean done = false;
        }
        Input(boolean done, String content) {
            this.done = done;
            this.content = content;
        }

        public void taskDone() {
            this.done = true;
        }
    }

    static class Todo extends Input {

        Todo(String content) {
            super(content);
            this.id = "[T]";
            this.time = "";
        }

        Todo(boolean done, String content) {
            super(done, content);
            this.id = "[T]";
            this.time = "";
        }
    }

    static class Deadline extends Input {

        Deadline(String content, String deadlineTime) {
            super(content);
            this.time = "(by:" + deadlineTime + ")";
            this.id = "[D]";
        }

        Deadline(boolean done, String content, String deadlineTime) {
            super(done, content);
            this.time = "(by:" + deadlineTime + ")";
            this.id = "[D]";
        }
    }

    static class Event extends Input {

        Event(String content, String eventTime) {
            super(content);
            this.time = "(at:" + eventTime + ")";
            this.id = "[E]";
        }

        Event(boolean done, String content, String eventTime) {
            super(done, content);
            this.time = "(at:" + eventTime + ")";
            this.id = "[E]";
        }
    }
    
    
    public static class DukeException extends Exception {
        String msg; 
        
        DukeException(String msg) {
            this.msg = msg;
        }
    }
    

}





    
