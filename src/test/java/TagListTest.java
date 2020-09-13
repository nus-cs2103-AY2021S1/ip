import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.siawsam.duke.DukeException;
import com.siawsam.duke.Tag;
import com.siawsam.duke.TagList;
import com.siawsam.duke.Todo;

public class TagListTest {
    @Test
    public void tagList_addTodoToTag_newTagAdded() throws DukeException {
        Todo testTodoItem = new Todo("hello world");
        
        Tag expected = new Tag("new tag");
        expected.addItem(testTodoItem);
    
        TagList tagList = new TagList();
        Tag actual = tagList.addTaggableToTag("new tag", testTodoItem);
        
        assertEquals(expected.toString(), actual.toString());
    }
    
    @Test
    public void tagList_removeLastItemFromTag_emptyTagRemovedFromTagList() throws DukeException {
        Todo testItem = new Todo("hello world");
        TagList tagList = new TagList();
        Tag tag = tagList.addTaggableToTag("tag", testItem);
        tagList.untag(tag, testItem);
        
        assertEquals("", tagList.toString());
    }
}
