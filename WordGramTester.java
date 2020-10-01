import java.util.*;
/**
 * Write a description of WordGramTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordGramTester {
    public void testWordGram() {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;
        for (int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words, index, size);
            System.out.println(index + "\t" + wg.length() + "\t" + wg);
        }
    }
    
    public void testWordGramEquals() {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;
        for (int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words, index, size);
            list.add(wg);
        }
        WordGram first = list.get(0);
        System.out.println("Checking " + first);
        for (int k = 0; k < list.size(); k++) {
            if (first.equals(list.get(k))) {
                System.out.println("Matched at " + k + " " + list.get(k));
        
            }
        }
    }
    
    public void testWordGramShiftAdd() {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;        
        WordGram wg = new WordGram(words, 0, size);
        WordGram wg2 = wg.shiftAdd("SAMPLE");
        System.out.println(wg2);
        String[] w2 = Arrays.copyOfRange(words, 1, 4);
        for (int i = 0; i < w2.length; i++) {
            
            System.out.println(w2[i]);
        }
    }
}
