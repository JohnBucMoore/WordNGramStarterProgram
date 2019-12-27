
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}

	private int indexOf(String[] words, String target, int start) {
    	for (int i = start; i < words.length; i++) {
    		if (words[i].equals(target)) {
    			return i;
			}
		}
    	return -1;
	}

	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int currIndex = 0;
	    while (currIndex != -1) {
	    	int keyIndex = indexOf(myText, key, currIndex);
	    	int followingIndex = keyIndex + 1;
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
    	System.out.println(indexOf(myText, "this", 0));
		System.out.println(indexOf(myText, "this", 3));
		System.out.println(indexOf(myText, "frog", 0));
		System.out.println(indexOf(myText, "frog", 5));
		System.out.println(indexOf(myText, "simple", 2));
		System.out.println(indexOf(myText, "test", 5));
	}

	public static void main(String[] args) {
		MarkovWordOne mwo = new MarkovWordOne();
		mwo.testIndexOf();
	}
}
