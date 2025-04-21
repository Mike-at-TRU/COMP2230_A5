import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.digiteched.javadsa.Trie;
import com.digiteched.javadsa.exceptions.FailedToAddNonAlphabetValueToTrie;
import com.digiteched.javadsa.interfaces.ITrie;

public class MoreTrieTest {

    @Test
    public void itShouldOnlyAcceptAlhabet() {
        ITrie trie = new Trie();
        assertThrows(FailedToAddNonAlphabetValueToTrie.class, () -> {
            trie.add("This Is A W0Rd");
        }); // the ' ' should be gone

        assertThrows(FailedToAddNonAlphabetValueToTrie.class, () -> {
            trie.add("W0Rd");
        }); // the '0' should be gone
        assertThrows(NullPointerException.class, () -> {
            trie.add(null);
        }); // the null should be gone
    }

    @Test
    public void itShouldMakeLettersLowercase() {
        ITrie trie = new Trie();
        // I wanted to do a assert doesn't throws but that didn't work so
        trie.add("WoRd");
        assertTrue(trie.has("word")); // also why don't you use assertTrue?
    }

    @Test
    public void itShouldBeAbleToTakeInAHasWordWithOffSymbols() {
        ITrie trie = new Trie();
        assertFalse(trie.has("this is a word"));
        assertFalse(trie.has("w0rd"));

        trie.add("word");

        assertFalse(trie.has("this is a word"));
        assertFalse(trie.has("w0rd"));
    }

    @Test
    public void itShouldSayYesForWordsUpperCase() {
        ITrie trie = new Trie();
        trie.add("word");
        assertTrue(trie.has("Word"));
    }

}
