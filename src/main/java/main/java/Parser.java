package main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents a handler of the commands.
 * It parses the command the user types in and handles the instructions given
 */
public class Parser {
    Scanner sc;
    TaskList taskList;
    boolean flag;

    public Parser(TaskList taskList, boolean flag) {
        this.sc = new Scanner(System.in);
        this.taskList = taskList;
        this.flag = flag;
    }

    /**
     * parses anf handles the command the user passes into duke
     * @param input
     * @return the String to print out
     */
    public String handleCommand(String input) {
        sc = new Scanner(input);
        String command = sc.next();

        switch (command) {
            case "bye":
                return handleBye();

            case "list":
                return handleList();

            case "done":
                try {
                    return handleDone();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }

            case "delete":
                try {
                    return handleDelete();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }

            case "todo":
                try {
                    return handleTodo();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }

            case "deadline":
                try {
                    return handleDeadline();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }

            case "FDTask":
                try {
                    return handleFDTask();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }

            case "event":
                try {
                    return handleEvent();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }

            case "find":
                try {
                    return handleFind();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }

            default:
                try {
                    return handleDefault();
                } catch (DukeException e) {
                    return "     " + e.getMessage();
                }
        }
    }

    /**
     * handles instruction "bye"
     */
    public String handleBye() {
        flag = false;
        try {
            this.updateFile();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("IOException from FileWriter!!");
        }
        return "     Bye. Hope to see you again soon!";
    }

    /**
     * handles instruction "list"
     */
    public String handleList() {
        String listOutput = "";
        listOutput += "     Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            listOutput += "     " + (i + 1) + "." + taskList.get(i).getDescription() + "\n";
        }
        return listOutput;
    }

    /**
     * handles instruction "done"
     */
    public String handleDone() throws DukeException {
        if (!sc.hasNextLine()) {
            throw new DukeException("\u2639 OOPS!!! I need to know the index of the task to be done!");
        }
        assert sc.hasNextLine();
        String doneCommand = sc.nextLine();
        int index = 0;
        if (doneCommand.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! I need to know the index of the task to be done!");
        }
        try {
            index = Integer.parseInt(doneCommand.stripLeading());
        } catch (NumberFormatException e) {
            return "     \u2639 Please enter a valid integer!!";
        }
        if (index > taskList.size()) {
            throw new DukeException("\u2639 Your number is too large!!");
        }
        Task currentTask = taskList.get(index - 1);

        String doneOutput = "";
        currentTask.markAsDone();
        doneOutput += "     Nice! I've marked this task as done:\n     ";
        doneOutput += currentTask.getDescription();
        return doneOutput;
    }

    /**
     * handles instruction "delete"
     */
    public String handleDelete() throws DukeException {
        if (!sc.hasNextLine()) {
            throw new DukeException("\u2639 OOPS!!! I need to know the index of the task to be deleted!");
        }
        assert sc.hasNextLine();
        String deleteCommand = sc.nextLine();
        int index = 0;
        if (deleteCommand.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! I need to know the index of the task to be deleted!");
        }
        try {
            index = Integer.parseInt(deleteCommand.stripLeading());
        } catch (NumberFormatException e) {
            return "     \u2639 Please enter a valid integer!!";
        }
        if (index > taskList.size()) {
            throw new DukeException("\u2639 Your number is too large!!");
        }
        assert index <= taskList.size();
        Task currentTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        String deleteOutput = "";
        deleteOutput += "     Noted! I've removed this task:\n     ";
        deleteOutput += currentTask.getDescription();
        deleteOutput += "\n     Now you have " + taskList.size() + " tasks in the list.";
        return deleteOutput;
    }

    /**
     * handles instruction "find"
     */
    public String handleFind() throws DukeException {
        if (!sc.hasNextLine()) {
            throw new DukeException("\u2639 OOPS!!! I need to know the keyword!!");
        }
        assert sc.hasNextLine();
        String input = sc.nextLine();
        if (input.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! I need to know the keyword!!");
        }
        String keyword = input.replaceFirst(" ", "");
        String output = "";
        output += "     Here are the matching tasks in your list:\n";
        for (int i = 0, count = 1; i < taskList.size(); i++) {
            if (taskList.get(i).description.contains(keyword)) {
                output += "     " + count + "." + taskList.get(i).getDescription() + "\n";
                count++;
            }
        }
        return output;
    }

    /**
     * handles instruction "todo"
     */
    public String handleTodo() throws DukeException {
        if (!sc.hasNextLine()) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        assert sc.hasNextLine();
        String todoDescription = sc.nextLine();
        if (todoDescription.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
        String output = "";
        output += "Got it. I've added this task:       \n" + todo.getDescription();
        output += "\nNow you have " + taskList.size() + " tasks in the list.";
        return output;
    }

    /**
     * handles instruction "deadline"
     */
    public String handleDeadline() throws DukeException {
        if (!sc.hasNextLine()) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        assert sc.hasNextLine();
        String deadlineCommand = sc.nextLine();
        if (deadlineCommand.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!deadlineCommand.contains("/by")) {
            throw new DukeException("\u2639 OOPS!!! The timing of a deadline cannot be empty.");
        }
        String[] strings = deadlineCommand.split("/by");
        String deadlineDescription = strings[0];
        String time = strings[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd");
        LocalDate date = LocalDate.parse(time, formatter);
        Deadline deadline = new Deadline(deadlineDescription, date);
        taskList.add(deadline);
        String output = "";
        output += "Got it. I've added this task:        \n" + deadline.getDescription();
        output += "\nNow you have " + taskList.size() + " tasks in the list.";
        return output;
    }

    /**
     * handles instruction "FDTask"
     */
    public String handleFDTask() throws DukeException{
        if (!sc.hasNextLine()) {
            throw new DukeException("\u2639 OOPS!!! The description of a FDTask cannot be empty.");
        }
        String FDCommand = sc.nextLine();
        if (FDCommand.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a FDTask cannot be empty.");
        }
        if (!FDCommand.contains("/for")) {
            throw new DukeException("\u2639 OOPS!!! The duration of a FDTask cannot be empty.");
        }
        String[] strings = FDCommand.split("/for");
        String FDDescription = strings[0];
        String period = strings[1];
        double hours = Double.valueOf(period);
        FixedDurationTasks fixedDurationTasks = new FixedDurationTasks(FDDescription, hours);
        taskList.add(fixedDurationTasks);
        String output = "";
        output += "Got it. I've added this task:        \n" + fixedDurationTasks.getDescription();
        output += "\nNow you have " + taskList.size() + " tasks in the list.";
        return output;
    }

    /**
     * handles instruction "event"
     */
    public String handleEvent() throws DukeException{
        if (!sc.hasNextLine()) {
            throw new DukeException("\u2639 OOPS!!! The description of a event cannot be empty.");
        }
        assert sc.hasNextLine();
        String eventCommand = sc.nextLine();
        if (eventCommand.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        }
        if (!eventCommand.contains("/at")) {
            throw new DukeException("\u2639 OOPS!!! The timing of an event cannot be empty.");
        }
        String[] strings = eventCommand.split("/at");
        String eventDescription = strings[0];
        String date = strings[1];
        Event event = new Event(eventDescription, date);
        taskList.add(event);
        String output = "";
        output += "Got it. I've added this task:        \n";
        output += event.getDescription() + "Now you have " + taskList.size() + " tasks in the list.";
        return output;
    }

    /**
     * throws exceptions when the command is wrongly typed
     * @throws DukeException
     */
    public String handleDefault() throws DukeException {
        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * updates the task list in the local hard disk
     * @throws IOException
     */
    public void updateFile() throws IOException {
        FileWriter fw = new FileWriter("./command.txt");
        FileWriter fwAppend = new FileWriter("./command.txt", true);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String line;
            if(task instanceof Todo) {
                int done = task.isDone? 1:0;
                line = "T|" + done + "|" + task.description;
            } else if(task instanceof Deadline) {
                int done = task.isDone? 1:0;
                line = "D|" + done + "|" + task.description + "|" +
                        ((Deadline) task).deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else if(task instanceof FixedDurationTasks) {
                int done = task.isDone? 1:0;
                line = "F|" + done + "|" + task.description + "|" + ((FixedDurationTasks) task).getHours();
            } else {
                int done = task.isDone? 1:0;
                line = "T|" + done + "|" + task.description + "|" + ((Event) task).time;
            }

            if (i == 0) {
                fw.write(line + '\n');
            } else if (i < taskList.size() - 1) {
                fwAppend.write(line + '\n');
            } else {
                fwAppend.write(line);
            }
        }
        fw.close();
        fwAppend.close();
    }

}