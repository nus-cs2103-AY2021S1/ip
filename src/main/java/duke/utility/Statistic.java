package duke.utility;

import duke.task.Task;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;


public class Statistic {

    private HashMap<Integer,ArrayList<String>> weeksInYear;

    public Statistic(){
        weeksInYear = new HashMap<>();
    }

    public void addCompletedTask(Task task) {
        LocalDate now = LocalDate.now();
        int weekNum = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        String taskDescription = task.getTaskName();
        weeksInYear.computeIfAbsent(weekNum, k -> new ArrayList<>());
        weeksInYear.get(weekNum).add(taskDescription);
    }

    public void addPastTask(String taskName, int weekNum) {
        assert weekNum >= 0 : "file format is wrong";
        LocalDate now = LocalDate.now();
        weeksInYear.computeIfAbsent(weekNum, k -> new ArrayList<>());
        weeksInYear.get(weekNum).add(taskName);
    }

    public String getStatisticSummary(){

        LocalDate now = LocalDate.now();
        int weekNum = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        if(weeksInYear.containsKey(weekNum-1)) {
            ArrayList<String> week = weeksInYear.get(weekNum - 1);
            int numOfTask = week.size();
            StringBuilder output = new StringBuilder(String.format("task completed last week: %d\n", numOfTask));

            for (String task : week) {
                output.append(task).append("\n");
            }

            return output.toString();
        }else{
            return "no task done past week";
        }
    }

    public String safeFileFormat(){
        StringBuilder saveFormat = new StringBuilder();
        for(int i=0;i<54;i++){
            if(weeksInYear.containsKey(i)){
                StringBuilder temp = new StringBuilder(String.format("#D#%d\n", i));
                for(String task : weeksInYear.get(i)){
                    temp.append(String.format("%s\n", task));
                }
                saveFormat.append(temp);
            }
        }
        return saveFormat.toString();
    }

    public void test(){
        for(int i=0;i<60;i++){
            if(weeksInYear.containsKey(i)){
                System.out.println(i+"\n");
                for(String task : weeksInYear.get(i)){
                    System.out.println(task);
                }
            }
        }
    }
}

