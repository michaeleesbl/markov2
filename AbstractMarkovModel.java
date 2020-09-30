import java.util.*;
/**
 * Write a description of AbstractMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String text) {
        myText = text;
    }
    
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length() - 1) {
            int start = myText.indexOf(key, pos);
            if (start == -1) break;
            if (start + key.length() >= myText.length() - 1) break;
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            follows.add(next);
            pos = start + key.length();
        }
        return follows;        
    }
}
