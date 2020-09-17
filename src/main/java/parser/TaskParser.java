package parser;

import java.util.ArrayList;
import java.util.function.Function;

import exceptions.InvalidCommandException;
import service.Task;
import utils.TokenUtils;

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
    private ArrayList<TaskInstance> allTaskInstances;

    public TaskParser() {
        allTaskInstances = new ArrayList<>();
    }

    public void registerTask(Function<String[], Task> constructor, String taskWord) {
        allTaskInstances.add(new TaskInstance(constructor, taskWord));
    }

    /**
     *
     * @param tokens list of tokens
     * @return a new Task
     * @throws InvalidCommandException when facing invalid syntax
     */
    public Task parse(String[] tokens) throws InvalidCommandException {
        if (tokens.length <= 1) {
            throw new InvalidCommandException("Should contain more tokens");
        }
        String word = tokens[0];
        String[] tailedTokens = TokenUtils.dropFirst(tokens);
        for (TaskInstance instance: allTaskInstances) {
            if (word.equals(instance.taskWord)) {
                return instance.constructor.apply(tailedTokens);
            }
        }
        throw new InvalidCommandException("Task type not found");
    }
}
