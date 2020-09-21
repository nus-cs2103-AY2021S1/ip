public class MissingInputException extends DukeException {

    public MissingInputException(String missingThing, String expectedThings) {
        super(String.format("I'm sorry you forgot to enter %s. Expected to enter %s",
                missingThing, expectedThings));
    }
}
