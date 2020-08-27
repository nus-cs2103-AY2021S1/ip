public class TaskGenerator {
    public static Task generateTask(String taskData) throws Exception {
        String[] taskSubData = taskData.split("\\|");
        String taskTypeLetter = taskSubData[0];
        Boolean isDone = taskSubData[1].equals("1");
        String taskDesc = taskSubData[2];
        
        Task task;
        
        if (taskTypeLetter.equals("T")) {
            task = new ToDo(taskDesc);
        } else if (taskTypeLetter.equals("D")) {
            task = new Deadline(taskDesc, taskSubData[3]);
        } else if (taskTypeLetter.equals("E")) {
            task = new Event(taskDesc, taskSubData[3]);
        } else {
            // todo: generate exception
            System.out.println("corrupted save file");
            throw new Exception();
        }
        
        if (isDone) {
            task.markAsDone();
        }
        
        return task;
    }
}
