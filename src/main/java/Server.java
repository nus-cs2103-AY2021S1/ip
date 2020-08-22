
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;

public class Server {
    private ArrayList<Task> taskList;
    private final DateTimeFormatter acceptedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Server()  {
        try {
            this.taskList = FileHandler.load();
        } catch (IOException e) {
            taskList = new ArrayList<>();
        }
    }
    private void add(Task newTask) {
        StringBuilder out = new StringBuilder();

        this.taskList.add(newTask);

        out.append("Got it. I've added this task: ").append("\n\t\t")
                .append(newTask.toString()).append("\n\t")
                .append(String.format("Now you have %d tasks in the list.\n",taskList.size()));

        FileHandler.save(this.taskList);

        System.out.print(Duke.formatOut(out.toString()));
    }

    private void list() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list: \n\t");

        if (this.taskList.isEmpty()) {
            System.out.print(Duke.formatOut("You haven't added any task here !"));
            return ;
        }

        for(Task task:taskList) {
            builder.append(taskList.indexOf(task) + 1).append(". ")
                    .append(task.toString()).append("\n").append("\t");
        }

        System.out.print(Duke.formatOut(builder.toString()));
    }

    private void delete(int index) {
        StringBuilder out = new StringBuilder();
        Task removed = this.taskList.remove(index);
        out.append("Noted. I've removed this task: ").append("\n\t\t")
                .append(removed.toString()).append("\n\t")
                .append(String.format("Now you have %d tasks in the list.\n",taskList.size()));
        FileHandler.save(this.taskList);
        System.out.print(Duke.formatOut(out.toString()));
    }

    public void perform(String input) {
        try {
            if (input.equals("")) {
                throw new AmbiguousInputException();
            }
            String[] s = input.split(" ", 2);
            if (s[0].equals("list")) {
                this.list();
            } else if (s[0].equals("done")) {
                int index = Integer.parseInt(s[1]) - 1;
                this.taskList.get(index).markAsDone();
            } else {
                if (s.length == 1) {
                    if (s[0].equals("delete")) {
                        throw new DeletionEmptyException();
                    } else {
                        throw new DescriptionEmptyException(s[0]);
                    }
                }

                //Judge the action and execute
                switch (s[0]) {
                case "todo":
                    this.add(new Todo(s[1]));
                    break;
                case "deadline": {
                    String[] set = s[1].split(" /by ");

                    if (set.length == 1) {
                        throw new TimeEmptyException("deadline");
                    }
                    this.add(new Deadline(set[0], LocalDateTime.parse(set[1], acceptedFormatter)));

                    break;
                }
                case "event": {
                    String[] set = s[1].split(" /at ");

                    if (set.length == 1) {
                        throw new TimeEmptyException("event");
                    }

                    this.add(new Event(set[0], LocalDateTime.parse(set[1], acceptedFormatter)));

                    break;
                }
                case "delete": {
                    this.delete(Integer.parseInt(s[1]) - 1);
                    break;
                }
                default:
                    throw new AmbiguousInputException();
                }
                }
        } catch (Exception e) {
            System.out.print(Duke.formatOut(e.getMessage()));
        }
    }
}
