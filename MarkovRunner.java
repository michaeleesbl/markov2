
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
         FileResource fr = new FileResource();
         String st = fr.asString();
         st = st.replace('\n', ' ');
         MarkovZero markov = new MarkovZero();
         markov.setRandom(101);
         markov.setTraining(st);
         for(int k=0; k < 3; k++){
             String text = markov.getRandomText(500);
             printOut(text);
         }
    }
    public void runMarkovOne() {
         FileResource fr = new FileResource();
         String st = fr.asString();
         st = st.replace('\n', ' ');
         MarkovOne markov = new MarkovOne();
         markov.setRandom(42);
         markov.setTraining(st);
         for(int k=0; k < 3; k++){
             String text = markov.getRandomText(500);
             printOut(text);
        }
    }    
    public void runMarkovFour() {
         FileResource fr = new FileResource();
         String st = fr.asString();
         st = st.replace('\n', ' ');
         MarkovFour markov = new MarkovFour();
         markov.setRandom(25);
         markov.setTraining(st);
         for(int k=0; k < 3; k++){
             String text = markov.getRandomText(500);
             printOut(text);
         } 
    }
   
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
             System.out.print(words[k]+ " ");
             psize += words[k].length() + 1;
             if (psize > 60) {
                 System.out.println();
                 psize = 0;
                }
        }
        System.out.println("\n----------------------------------");
    }
   
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes a test";
        MarkovWordOne m1 = new MarkovWordOne();
        m1.setRandom(175);
        runModel(m1, st, 175);
    }
   
    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes a test";
        MarkovModel markov = new MarkovModel(6);
        markov.setRandom(38);
        markov.setTraining(st);
        for (int k = 0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }       
    }
    
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void testGetFollows() {
        String text = "this is just a test yes this is a simple test";
        MarkovWordOne m1 = new MarkovWordOne();
        runModel(m1, text, 200);
    }
    public void runMarkovTwo() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes a test";
        MarkovWordTwo m2 = new MarkovWordTwo();
        m2.setRandom(549);
        runModel(m2, st, 175);
    }
}
