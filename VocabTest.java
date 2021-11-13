import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

/**
 * Test of the Vocabulary class
 */
public class VocabTest {
                  

    @Test
    public void testAddStartingWord() {
        Vocabulary v = new Vocabulary();
        v.addStartingWord("hello");
        // After adding one starting word, we should see that word but
        // the pairs should be unchanged.
        assertThat (v.getStartingWords().size(), is(1));
        assertThat (v.getStartingWords(), hasItem("hello"));
        assertThat(v.getNumStartingWords(), is(1));
        assertThat(v.getNumWordPairs(), is(0));
        assertTrue(v.getWordsThatCanFollow("hello").isEmpty());

        v.addStartingWord("goodbye");
        // After adding another starting word, we should see both words in
        // the order they were added, and the pairs should be unchanged.
        assertThat (v.getStartingWords().size(), is(2));
        assertThat (v.getStartingWords(), contains("hello", "goodbye"));
        assertThat(v.getNumStartingWords(), is(2));
        assertThat(v.getNumWordPairs(), is(0));
        assertTrue(v.getWordsThatCanFollow("hello").isEmpty());
        assertTrue(v.getWordsThatCanFollow("goodbye").isEmpty());

        v.addStartingWord("hello");
        // After adding another starting word, we should see three words in
        // the order they were added even if one word is the duplicate of one
        // of the earlier ones. The pairs should be unchanged.
        String[] expected = {"hello", "goodbye", "hello"};
        List<String> observed = v.getStartingWords();
        assertThat(observed, contains(expected));

        assertThat(v.getNumStartingWords(), is(3));
        assertThat(v.getNumWordPairs(), is(0));
        assertTrue(v.getWordsThatCanFollow("hello").isEmpty());
    
    }
    @Test
    public void testVocabularyConstructor() {
        Vocabulary v = new Vocabulary();
        assertThat(v.getStartingWords().size(), is(0));
        assertThat(v.getNumStartingWords(), is(0));
        assertThat(v.getNumWordPairs(), is(0));
        assertTrue(v.getWordsThatCanFollow(null).isEmpty());
    }
    @Test
    public void testAddWordPairs() {
        Vocabulary v = new Vocabulary();
        v.addWordPair("Coconut", "Pineapple");
        v.addWordPair("Coconut", "Pineapple");
        assertThat(v.getStartingWords().size(), is(0));
        assertThat(v.getNumStartingWords(), is(0));
        assertThat(v.getNumWordPairs(), is(2));
        assertTrue(v.getWordsThatCanFollow("Pineapple").isEmpty());
        assertThat(v.getWordsThatCanFollow("Coconut").size(), is(2));
        assertThat(v.getWordsThatCanFollow("Coconut"), contains("Pineapple"));
    }
}