import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MarkovWordTwo  implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; i++) {
            if (words[i].equals(target1) && words[i+1].equals(target2)) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int currIndex = 0;
        while (currIndex != -1) {
            int keyIndex = indexOf(myText, key1, key2, currIndex);
            int followingIndex = keyIndex + 2;
            if (keyIndex != -1 && (followingIndex < myText.length)) {
                follows.add(myText[followingIndex]);
                currIndex = followingIndex;
            } else {
                break;
            }
        }
        return follows;
    }

    public void testIndexOf() {
        setTraining("this is just a test yes this is a simple test");
        System.out.println(Arrays.asList(myText).toString());
        System.out.println(indexOf(myText, "this", "is", 0));
        System.out.println(indexOf(myText, "this", "is", 3));
        System.out.println(indexOf(myText, "frog", "hop", 0));
        System.out.println(indexOf(myText, "frog", "hop", 5));
        System.out.println(indexOf(myText, "simple", "test", 2));
        System.out.println(indexOf(myText, "test","yes", 0));
    }

    public void testGetFollows() {
        setTraining("this is just a test yes this is a simple test");
        System.out.println(Arrays.asList(myText).toString());
        for (int i = 0; i < myText.length - 2; i++) {
            String key1 = myText[i];
            String key2 = myText[i+1];
            ArrayList<String> follows = getFollows(key1, key2);
            System.out.println(key1+" "+key2+"\t"+follows.toString());
        }
    }

    public static void main(String[] args) {
        MarkovWordTwo mwt = new MarkovWordTwo();
        mwt.testGetFollows();
    }
}
