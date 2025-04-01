import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.digiteched.javadsa.Trie;

public class TrieTest {
    @Test
    public void itShouldBuildAnEmptyTrie(){
        Trie t = new Trie();

        assertEquals(0, t.size());

        assertEquals(true, t.isEmpty());
    }

    @Test
    public void itShouldAddAFirstWord(){
        Trie t = new Trie();

        String w = "hello";

        t.add(w);

        assertEquals(true, t.has(w));

        assertEquals(1, t.size());

        assertEquals(false, t.isEmpty());
    }

    @Test
    public void itShouldIProvideCompletions(){
        Trie t = new Trie();

        t.add("hello").add("help").add("head").add("good").add("bye");

        List<String> completions = t.getCompletions("hel");

        assertEquals(2, completions.size());

        assertTrue(completions.contains("hello"));

        assertTrue(completions.contains("help"));

    }
}
