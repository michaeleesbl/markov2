import java.util.*;
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovFour implements IMarkovModel{
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() -4);// change for different markov
        String key = myText.substring(index, index + 4); // same
        sb.append(key);
        
        for (int k = 0; k < numChars - 4; k++) { // same
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key " + key + " " + follows);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
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
