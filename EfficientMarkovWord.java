import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> myMap;
    public EfficientMarkovWord(int order) { // order is the number of words to use in the prediction
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
    
    public void buildMap() {
        HashMap<Integer, ArrayList<String>> myMap = new HashMap<Integer, ArrayList<String>>();
        for (int i = 0; i < myText.length - myOrder - 1; i++) {
            WordGram target = new WordGram(myText, i, myOrder);
            int hashCode = target.hashCode();
            String next = myText[i + myOrder];
            if (!myMap.containsKey(hashCode)) {
                ArrayList<String> follows = new ArrayList<String>();
                follows.add(next);
                myMap.put(hashCode, follows);
            } else {
                ArrayList<String> follows = myMap.get(hashCode);
                follows.add(next);
                myMap.put(hashCode, follows);
            }
        }
        
        WordGram lastTarget = new WordGram(myText, myText.length - myOrder - 1, myOrder);
        if (!myMap.containsKey(lastTarget.hashCode())) myMap.put(lastTarget.hashCode(), new ArrayList<String>());
        //return myMap;
        this.myMap = myMap;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        int hashCode = kGram.hashCode();
        return myMap.get(hashCode());
        /*ArrayList<String> follows = new ArrayList<String>();
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
        return follows;*/
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
    
    private void printHashMapInfo() {
        
    }
}
