package parser;

import commands.Command;
import service.Task;
import utils.TokenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class TaskParser {
    ///helper class
    private static class TaskInstance {
        private Function<String[], Task> constructor;
        private String taskWord;

        public TaskInstance(Function<String[], Task> constructor, String taskWord) {
            this.constructor = constructor;
            this.taskWord = taskWord;
        }
    }
    ArrayList<TaskInstance> allTaskInstances;

    public TaskParser() {
        allTaskInstances = new ArrayList<>();
    }

    public void registerTask(Function<String[], Task> constructor, String taskWord) {
        allTaskInstances.add(new TaskInstance(constructor, taskWord));
    }

    public Task parse(String[] tokens) throws Exception {
        if (tokens.length <= 1) {
            throw new Exception("Should contain more tokens");
        }
        String word = tokens[0];
        String[] tailedTokens = TokenUtils.dropFirst(tokens);
        for (TaskInstance instance: allTaskInstances) {
            if (word.equals(instance.taskWord)) {
                return instance.constructor.apply(tailedTokens);
            }
        }
        throw new Exception("Task type not found");
    }
}
