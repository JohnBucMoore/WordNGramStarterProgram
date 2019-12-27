
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 200); 
    }

    public void runMarkovTwo() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWordTwo m = new MarkovWordTwo();
        runModel(m, st, 200, 549);
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

    public void testMarkovWordOne() {
        MarkovWordOne mwo = new MarkovWordOne();
        FileResource fr = new FileResource("data/confucius.txt");
        String text = fr.asString();
        //String text = "this is just a test yes this is a simple test";
        runModel(mwo, text, 120, 175);
    }

    public void testGetFollows() {
        MarkovWordTwo mwt = new MarkovWordTwo();
        String text = "this is just a test yes this is a simple test";
        runModel(mwt, text, 120, 175);
    }

    public static void main(String[] args) {
        MarkovRunner mr = new MarkovRunner();
        mr.runMarkovTwo();
    }
}
