package duke.utility;

import duke.task.Task;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Statistic {

    private HashMap<Integer,ArrayList<String>> weeksInYear;

    public Statistic(){
        weeksInYear = new HashMap<>();
    }

    public void addCompletedTask(Task task) {
        LocalDate now = LocalDate.now();
        int weekNum = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        String taskDescription = task.getTaskName();
        weeksInYear.computeIfAbsent(weekNum, k -> new ArrayList<String>());
        weeksInYear.get(weekNum).add(taskDescription);
    }

    public String getStatisticSummary(){

        LocalDate now = LocalDate.now();
        int weekNum = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        ArrayList<String> week = weeksInYear.get(weekNum - 1);
        int numOfTask = week.size();
        String output = String.format("task completed last week: %d\n", numOfTask);

        for(String task : week){
            output += task + "\n";
        }

        return output;
    }
}

