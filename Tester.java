import java.util.*;
import edu.duke.*;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testGetFollows() {
        MarkovOne markovOne = new MarkovOne();
        markovOne.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markovOne.getFollows("t");
        for (int i = 0; i < follows.size(); i++) System.out.print(follows.get(i));
        System.out.println(follows);
        System.out.println(follows.size());
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markovOne = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markovOne.setTraining(st);
        ArrayList<String> follows = markovOne.getFollows("t");
        System.out.println(follows.size());
    }
}
