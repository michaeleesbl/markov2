import java.util.*;
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) { // order is the number of words to use in the prediction
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        for (int i = 0; i < myText.length && i < 100; i++) {
            System.out.println(myText[i]);
        }
        System.out.println(myText);
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i <= words.length - myOrder; i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if (wg.equals(target)) return i;
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, kGram, pos);
            if (start == -1) break;
            if (start >= myText.length - myOrder) break;
            String next = myText[start + myOrder];
            follows.add(next);
            pos = start + 1;
        }
        //System.out.println(follows);
        return follows;
    }    
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        String[] key = Arrays.copyOfRange(myText, index, index + myOrder);
        WordGram target = new WordGram(key, 0, key.length);
        sb.append(target.toString());
        sb.append(" ");
        
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(target);
            //System.out.println(key + ": " + follows);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            target = target.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    /*public String getRandomText(int numWords){
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
    }*/
    

    
    
    public void testIndexOf() {
        String text = "this is just a test yes this is a simple test";
        String[] words = text.split("\\s+");
        WordGram target = new WordGram(words, 2, 2);
        System.out.println(target);
        //MarkovWord mw = new MarkovWord(2);
        this.setTraining(text);
        int idx = this.indexOf(myText, target, 5);
        System.out.println(idx);
        /*for (int i = 0; i < words.length; i++) {
            int idx = indexOf(words, words[i], 0);
            System.out.println(words[i] + " has index of: " + i);
            
        }*/
    }
}
