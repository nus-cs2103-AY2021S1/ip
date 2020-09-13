package duke.task;

import duke.DukeException;

public class Trivia extends Task {
    private String triviaQuestion;
    private String triviaAnswer;

    /**
     * Initiate Trivia object.
     *
     * @param triviaQuestion trivia question.
     * @param triviaAnswer answer to the trivia question.
     */
    public Trivia(String triviaQuestion, String triviaAnswer) {
        super(triviaQuestion + triviaAnswer);
        this.triviaQuestion = triviaQuestion;
        this.triviaAnswer = triviaAnswer;
    }

    public String getTriviaQuestion() {
        return this.triviaQuestion;
    }

    public String getTriviaAnswer() {
        return this.triviaAnswer;
    }

    @Override
    public void markDone() throws DukeException {
        throw new DukeException("Cannot mark trivia as done");
    }

    @Override
    public boolean getIsDone() throws DukeException {
        throw new DukeException("Cannot mark trivia as done");
    }

    @Override
    public boolean checkIfDuplicate(Task otherTask) {
        if (otherTask instanceof Trivia) {
            return super.checkIfDuplicate(otherTask);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[Q] %s\n    [A] %s", this.triviaQuestion, this.triviaAnswer);
    }
}
