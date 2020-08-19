import java.util.List;
import java.util.ArrayList;

public class ListOfItems {
    List<Task> list;
    int index;
    String divider = "____________________________________________________________";

    ListOfItems() {
        this.list = new ArrayList<>();
        this.index = 0;
    }

    void getList() {
        System.out.println(divider);
        if (list.size() == 0) {
            System.out.println("List is empty, you have free time (for now)! YAY :D");
        } else {
            System.out.println("Here are the task(s) in your list: ");
            for (int i = 0; i < index; i++) {
                System.out.println(list.get(i).id + "." + list.get(i));
            }
        }
        System.out.println(divider);
    }

    void doneItem(String input) {
        try {
            int number = Integer.parseInt(input.substring(5)); // retrieve number after "done "
            Task task = list.get(number - 1);
            if (task.isDone) {
                System.out.println("Task already done!");
            } else {
                task.markedDone();
                String message = "Good job! I've marked this task as done: ";
                System.out.println(divider + "\n" + message + "\n" +
                        task + "\n" + divider);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(divider);
            System.out.println("Sorry, you did not enter a valid number. Please try again.");
            System.out.println(divider);
        }
    }

    void deleteItem(String input) {
        try {
            int number = Integer.parseInt(input.substring(7));
            Task task = list.get(number - 1);
            for (int i = number; i < list.size(); i++) {
                list.get(i).id = list.get(i).id - 1;
            }
            list.remove(task);
            index--;
            System.out.println(divider);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task);
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(divider);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(divider);
            System.out.println("Whoops, you did not enter a valid number.");
            System.out.println(divider);
        }
    }

    void addItem(String input) {
        String addedMessage = "Got it. I've added this task: ";
        String totalMessage = "Now you have " + (index + 1) + " task(s) in the list.";

        if (input.contains("todo")) {
            try {
                String description = input.substring(5);

                System.out.println(divider + "\n" + addedMessage);
                list.add(index, new Todo(description, index + 1));
                System.out.println(list.get(index));
                System.out.println(totalMessage + "\n" + divider);
                index++;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(divider);
                System.out.println("Whoops, you did not fill in the details of the Todo properly :(");
                System.out.println(divider);
            }
        } else if (input.contains("deadline")) {
            try {
                String[] info = input.split("/");
                String description = info[0].substring(9);
                String dueDateTime = info[1];

                System.out.println(divider + "\n" + addedMessage);
                list.add(index, new Deadline(description, index + 1, dueDateTime));
                System.out.println(list.get(index));
                System.out.println(totalMessage + "\n" + divider);
                index++;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(divider);
                System.out.println("Whoops, you did not fill in the details of the Deadline properly :(");
                System.out.println(divider);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(divider);
                System.out.println("Whoops, you did not fill in the due date/time of the Deadline.");
                System.out.println(divider);
            }
        } else if (input.contains("event")) {
            try {
                String[] info = input.split("/");
                String description = info[0].substring(6);
                String duration = info[1];

                System.out.println(divider + "\n" + addedMessage);
                list.add(index, new Event(description, index + 1, duration));
                System.out.println(list.get(index));
                System.out.println(totalMessage + "\n" + divider);
                index++;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(divider);
                System.out.println("Whoops, you did not fill in the details of the Event properly :(");
                System.out.println(divider);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(divider);
                System.out.println("Whoops, you did not fill in the duration of the Event.");
                System.out.println(divider);
            }
        } else {
            System.out.println(divider);
            System.out.println("Sorry, you did not enter a valid command! Please try again.");
            System.out.println(divider);
        }
    }
}
