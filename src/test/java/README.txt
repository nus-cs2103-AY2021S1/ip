TESTING:

Classes ignored for testing (and why):
1. Duke
    Implementation under Duke is rather dependent on the other classes.
    It doesn't do much on its own so it is better (imo) to test the other classes in detail.

2. CommonString
    It just contains some Strings and returns them.

3. UIManager
    It is just printing stuff so easy to validate.

4. Helper methods under UserInputParser
    Should write something to test them

5. GUI
    Not sure how to test

6. StorageManager
    Not very sure how to test the IO

7. Commands:
    AddCommand, ExitCommand, FindCommand, HelpCommand, ListCommand
    Implementations are trivial and dependent on the other classes that are tested.
    Testing DoneCommand and DeleteCommand because they have some form of input validation.