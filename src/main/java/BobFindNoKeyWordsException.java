package main.java;

public class BobFindNoKeyWordsException extends BobException {
    @Override
    public String getMessage() {
        return "A key word or phrase was not provided. "
                + "Please provide the key word or phrase that you're trying to find.";
    }
}
