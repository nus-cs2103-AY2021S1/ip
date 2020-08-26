package main.java;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

class AddCommand extends Command  {

     AddCommand(String text) {
        this.commandText = text;
     }
    enum TYPES {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private String text;

        TYPES(String text) {
            this.text = text;
        }
    }
     @Override
     public void execute(String taskDetails, TaskList taskList) {
        try {
            if (this.commandText.equals(TYPES.TODO.text)) {
            Task toAdd = new Task(taskDetails);
                taskList.addTask(toAdd);
            } else {
                String[] partsOfTask = taskDetails.split("/");
                String description = partsOfTask[0];
                String date = partsOfTask[1].strip();

                int day = Integer.parseInt(date.substring(0, 2));
                int month = Integer.parseInt(date.substring(2, 4));
                int year = Integer.parseInt(date.substring(4, 8));
                LocalDate actualDate = LocalDate.of(year,month,day);

                if (this.commandText.equals(TYPES.DEADLINE.text)) {
                    Task toAdd = new Deadline(description.strip(), actualDate);
                    taskList.addTask(toAdd);

                } else if (this.commandText.equals(TYPES.EVENT.text)) {
                    Task toAdd = new Event(description.strip(), actualDate);
                    taskList.addTask(toAdd);

                }
            }
        } catch (DateTimeException e) {
            System.out.println("Please key in again with a valid date\n" + Ui.LINE);
        }
     }
 }
