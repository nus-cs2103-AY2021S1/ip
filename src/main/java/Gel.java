import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Gel {
    public static void keepingList() {
        // initialise list and scanner
        List<Task> listOfText = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("    Hello! I'm Gel\n    What can I do for you?\n");

        label:
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String keyword = inputArr[0];
            try {
                switch (keyword) {
                    case "bye": { //bye
                        System.out.println("\n    Bye. Hope to see you again soon!\n");
                        break label;
                    }
                    case "list": { //list
                        System.out.println("\n    Here are the task(s) in your list:");
                        for (int i = 1; i <= listOfText.size(); i++) {
                            Task task = listOfText.get(i - 1);
                            System.out.println("    " + i + "." + task);
                        }
                        System.out.println();
                        break;
                    }
                    case "done": { //done
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        Task taskToBeDone = listOfText.remove(index);
                        taskToBeDone.markAsDone();
                        listOfText.add(index, taskToBeDone);
                        System.out.println("\n    Nice! I've marked this task as done:");
                        System.out.println("    " + taskToBeDone);
                        System.out.println();
                        break;
                    }
                    case "delete": { //delete
                        if (inputArr.length <= 1) {
                            throw new GelException("    Yo tell me what you want to delete la");
                        }
                        try {
                            int taskNo = Integer.parseInt(inputArr[1]);
                            Task taskToBeDone = listOfText.remove(taskNo - 1);
                            System.out.println("\n    Noted. I've removed this task:");
                            System.out.println("    " + taskToBeDone);
                            System.out.println("    Now you have " + listOfText.size() + " task(s) in the list.\n");
                            break;
                        } catch (Exception e) {
                            throw new GelException("    Yoyoyo please input a valid number after delete");
                        }
                    }
                    case "deadline": {//deadline
                        int dateIndex = input.lastIndexOf("/");
                        if (dateIndex == -1 ) {
                            throw new GelException("    Bruh you need the /by tag for deadlines");
                        } else {
                            String[] dateDetails = input.substring(dateIndex).split(" ");
                            String checkBy = dateDetails[0];
                            if (!checkBy.equals("/by")) {
                                throw new GelException("    Bruh you need the /by tag for deadlines");
                            } else if (dateDetails.length <= 1) {
                                throw new GelException("    Bruh you left out the deadline");
                            }
                        }
                        String by = input.substring(dateIndex + 4);
                        String description = input.substring(8, dateIndex);
                        Deadline deadline = new Deadline(description, by);
                        listOfText.add(deadline);
                        System.out.println("\n    Got it. I've added this task:");
                        System.out.println("      " + deadline);
                        System.out.println("    Now you have " + listOfText.size() + " task(s) in the list.\n");
                        break;
                    }
                    case "event": {//event
                        int dateIndex = input.lastIndexOf("/");
                        if (dateIndex == -1 ) {
                            throw new GelException("    Bruh you need the /at tag for events");
                        } else {
                            String[] dateDetails = input.substring(dateIndex).split(" ");
                            String checkAt = dateDetails[0];
                            if (!checkAt.equals("/at")) {
                                throw new GelException("    Bruh you need the /at tag for events");
                            } else if (dateDetails.length <= 1) {
                                throw new GelException("    Bruh you left out the event date");
                            }
                        }
                        String at = input.substring(dateIndex + 4);
                        String description = input.substring(5, dateIndex);
                        Event event = new Event(description, at);
                        listOfText.add(event);
                        System.out.println("\n    Got it. I've added this task:");
                        System.out.println("      " + event);
                        System.out.println("    Now you have " + listOfText.size() + " task(s) in the list.\n");
                        break;
                    }
                    case "todo": {
                        if (inputArr.length <= 1) {
                            throw new GelException("    Yo tell me what you want todo");
                        }
                        String description = input.substring(4);
                        Todo todo = new Todo(description);
                        listOfText.add(todo);
                        System.out.println("\n    Got it. I've added this task:");
                        System.out.println("      " + todo);
                        System.out.println("    Now you have " + listOfText.size() + " task(s) in the list.\n");
                        break;
                    }
                    default: {
                        throw new GelException();
                    }
                }
            } catch (GelException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}
