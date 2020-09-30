import java.util.*;
/**
 * Write a description of MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length - 2);
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1 + " " + key2);
        sb.append(" ");
        for (int k = 0; k < numWords - 2; k++) {
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println(key + ": " + follows);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }
    
    public ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        String key = key1 + " " + key2;
        while (pos < myText.length) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1) break;
            if (start >= myText.length - 2) break;
            String next = myText[start + 2];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; i++) {
            if (words[i].equals(target1)) {
                if (words[i + 1].equals(target2)) return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String text = "this is just a test yes this is a simple test";
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length - 1; i++) {
            int idx = indexOf(words, words[i], words[i + 1], 0);
            System.out.println(words[i] + " has index of: " + i);
            
        }
    }
}
