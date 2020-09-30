import java.util.*;
/**
 * Write a description of MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1);
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            //System.out.println(key + ": " + follows);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, key, pos);
            if (start == -1) break;
            if (start >= myText.length - 1) break;
            String next = myText[start + 1];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
    
    private int indexOf(String[] sa, String key, int pos) {
        for (int i = pos; i < sa.length; i++) {
            if (sa[i].equals(key)) return i;
        }
        return -1;
    }
    
    public void testIndexOf() {
        String text = "this is just a test yes this is a simple test";
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            int idx = indexOf(words, words[i], 0);
            System.out.println(words[i] + " has index of: " + i);
            
        }
    }
}
