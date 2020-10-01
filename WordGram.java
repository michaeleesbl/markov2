import java.util.*;
/**
 * Write a description of WordGram here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordGram {
    private String[] myWords;
    private Random myRandom;
    
    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }
    
    public int length() {
        return myWords.length;
    }
    
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index " + index);
        }
        return myWords[index];
    }
    
    public String toString() {
        String ret = "";
        for (int i = 0; i < myWords.length; i++) {
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }
    
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) return false;
        for (int i = 0; i < length(); i++) {
            if(!this.wordAt(i).equals(other.wordAt(i))) return false;
        }
        return true;
    }
    
    public WordGram shiftAdd(String word) {
        String[] newWords = new String[this.length()];
        for (int i = 1; i < this.length(); i++) {
            newWords[i - 1] = this.wordAt(i);
        }
        newWords[this.length() - 1] = word;
        WordGram wg = new WordGram(newWords, 0, this.length());
        return wg;
    }
    
    public int hashCode() {
        String text = this.toString();
        return text.hashCode();
    }
}
