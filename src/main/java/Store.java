package main.java;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Task> allItems;
    private final String line = "____________________________________________________________\n";

    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";

    Store() {
        this.allItems = new ArrayList<>();
    }

    public void addToStore(String type, String item) {
        try {
            String actual_item = item.strip();
        Task toAdd;
            if (type.equals(TODO)) {
            toAdd = new Task(actual_item);
            this.addTask(toAdd);
        } else {
            String[] parts_of_task = actual_item.split("/");
            if (parts_of_task.length != 2) {
                String instruction = "<type of task> <description> / <deadline>";
                if (type.equals(EVENT)) instruction = "<type of task> <description> / <date of event>";
                throw new DukeGotNoArgumentsException(instruction);
            } else {
                String description = parts_of_task[0];
                String duedate = parts_of_task[1];
                if (type.equals(DEADLINE)) {
                    toAdd = new Deadline(description.strip(), duedate.strip());
                    this.addTask(toAdd);

                } else if (type.equals(EVENT)) {
                    toAdd = new Event(description.strip(), duedate.strip());
                    this.addTask(toAdd);

                } else {
                    // should not reach here
                    System.out.println("It's not you, it's me. Something went wrong\n" +
                            "Please try again.\n" + line);
                }
            }
        }
        } catch (DukeGotNoArgumentsException e) {
            System.out.println(e.getMessage() + "\n" + line);
        }
    }

    private void addTask(Task toAdd) {
        this.allItems.add(toAdd);
        System.out.println("Alright, its in your list now!\n\t" + toAdd +
                "\nNow you have " + this.allItems.size() + " tasks.\n" + line);
    }

    public boolean printStore() {
        String printList;
        if (this.allItems.size() == 0) {
            printList = "There are no tasks added till now.\nAdd one by just typing its name.\n" + line;
        } else {
            printList = "Please finish these tasks ASAP!\n";
            int counter = 1;
            for (Task task: this.allItems) {
                printList = printList.concat("[" + counter + "] " + task + "\n");
                counter++;
            }
            printList = printList.concat("If you're brave enough to start,\n" + "You're strong enough to finish it!\n" + line);
        }
        System.out.println(printList);
        return true;
    }

    public boolean completeTask(String answer) {
        try {
            Integer one_index = Integer.valueOf(answer);
            Integer real_index = one_index - 1;
            Task toComplete = this.allItems.get(real_index);
            toComplete.finishTask();
        } catch (NumberFormatException ex) {
            System.out.println("I can't seem to understand what task you are referring to.\n" +
                    "Please let me know in this format: done <number of task> \n" + line);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Hmm... I don't have a task numbered " + answer + "\n" + line);
        }
        return true;
    }
    public boolean deleteTask(String answer) {
        try {
            int one_index = Integer.parseInt(answer);
            int real_index = one_index - 1;
            this.allItems.remove(real_index);
            System.out.println("I have removed the task from your list.\n" + line);
        } catch (NumberFormatException ex) {
            System.out.println("I can't seem to understand what task you are referring to.\n" +
                    "Please let me know in this format: done <number of task> \n" + line);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Hmm... I don't have a task numbered " + answer + "\n" + line);
        }
        return true;
    }
}
