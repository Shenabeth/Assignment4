/**
 * This is the Concordance Data Structure Class that is used with the Concordance Data Manager class.
 * @author Shenabeth Jenkins
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class ConcordanceDataStructure implements ConcordanceDataStructureInterface {
	//fields
	private LinkedList<ConcordanceDataElement>[] concordanceLinkedList;

	
	//constructor
	/**
	 * 2 arg constructor
	 * Constructor for testing purposes
	 * @param test the string "Testing"
	 * @param size the size of the hash table
	 */
	public ConcordanceDataStructure(String test, int size) {
		//create list
		concordanceLinkedList = new LinkedList[size];
		
		//loop through list
		for (int i = 0; i < concordanceLinkedList.length; i++){ concordanceLinkedList[i] = new LinkedList<>(); }
	}

	/**
	 * This constructor takes in an integer which represents the estimated number of words in the text
	 * Determine the size of the table by using a loading factor of 1.5 and a 4K+3 prime
	 * Example if you estimated 500 words, 500/1.5 = 333. The next 4K+3 prime over 333 is 347.
	 * So you would make the table a length of 347.
	 * @param num the estimated number of words in the text
	 */
	public ConcordanceDataStructure(int num) {
		//create variables
		int prime = (int)(num / 1.5);
		int divisor;
		int dIndex;

		//equation variables
		boolean fourkplus3 = false;
		boolean isPrime = false;

		//make number odd
		if(prime % 2 == 0) prime = prime +1;

		//4k+3 is false and not prime
		while(fourkplus3 == false) { 
			while(isPrime == false) { 
				//divide
				divisor = (int)(Math.sqrt(prime) + 0.5);

				//loop
				for(dIndex = divisor; dIndex > 1; dIndex--) { if(prime % dIndex == 0) break; }

				//no prime
				if(dIndex != 1) prime = prime + 2;
				else isPrime = true;
			}
			
			//fourkplus3
			if((prime - 3) % 4 == 0) fourkplus3 = true;
			else {  
				prime = prime + 2;
				isPrime = false;
			}
		} 

		//new list with prime
		concordanceLinkedList = new LinkedList[prime];

		//loop into list
		for (int i = 0; i < concordanceLinkedList.length; i++){
			concordanceLinkedList[i] = new LinkedList<>();
		}

	}
	
	
	//overridden methods
	/**
	 * Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	 */
	@Override
	public int getTableSize() {
		return concordanceLinkedList.length;
	}

	/**
	 * Returns an ArrayList of the words at this index [0] of the ArrayList holds the first
	 * word in the "bucket" (index) [1] of the ArrayList holds the next word in the "bucket", etc.
	 * This is used for testing
	 * @param index location within the hash table
	 * @return Arraylist of the words at this index
	 */
	@Override
	public ArrayList<String> getWords(int index) {
		//create variables
		ArrayList<String> wordsList = new ArrayList<>();

		//enhanced loop to loop through ALL elements of the list
		for (ConcordanceDataElement e : concordanceLinkedList[index]) { wordsList.add(e.getWord()); }

		//return
		return wordsList;
	}

	/**
	 * Returns an ArrayList of the Linked list of page numbers for each word at this index [0] of
	 * the ArrayList holds the LinkedList of page numbers for the first word in the "bucket" (index) [1]
	 * of the ArrayList holds the LinkedList of page numbers for next word in the "bucket", etc.
	 * This is used for testing
	 * @param index location within the hash table
	 * @return ArrayList of the Linked list of page numbers for each word at this index
	 */
	@Override
	public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
		//create variables
		ArrayList<LinkedList<Integer>> pageNumList = new ArrayList<>();

		//enhanced loop to loop through ALL elements of the list
		for (ConcordanceDataElement e : concordanceLinkedList[index]) { pageNumList.add(e.getList()); }

		//return
		return pageNumList;
	}
	
	/**
	 * Use the hashcode of the ConcordanceDataElement to see if it is in the hashtable.
	 * If the word does not exist in the hashtable - Add the ConcordanceDataElement to the hashtable.
	 * Put the line number in the linked list If the word already exists in the hashtable 1.
	 * add the line number to the end of the linked list in the ConcordanceDataElement
	 * (if the line number is not currently there).
	 * @param term the word to be added/updated with a line number
	 * @param lineNum the line number where the word is found
	 */
	@Override
	public void add(String word, int lineNum) {
		//create variables
		ConcordanceDataElement e = new ConcordanceDataElement(word);
		boolean hasWord = false;
		int indexHashCode = Math.abs(e.hashCode() % concordanceLinkedList.length), index;

		//loop 
		for (index = 0; index < concordanceLinkedList[indexHashCode].size(); index++) {
			if (concordanceLinkedList[indexHashCode].get(index).getWord().equalsIgnoreCase(word)) {
				hasWord = true;

				//stop loop
				break;
			}   
		}

		//if there is a word
		if (hasWord) { concordanceLinkedList[indexHashCode].get(index).addPage(lineNum); }

		//otherwise
		else {
			e.addPage(lineNum);
			concordanceLinkedList[indexHashCode].add(e);
		}
	}
	
	/**
	 * Display the words in Alphabetical Order followed by a :, followed by the line numbers in numerical order,
	 * followed by a newline
	 * Here's an example:
	 * after: 129, 175 agree: 185 agreed: 37 all: 24, 93, 112, 175, 203 always: 90, 128
	 * @return ArrayList of Strings
	 */
	@Override
	public ArrayList<String> showAll() {
		//create variables
		ArrayList<String> concordanceList = new ArrayList<>();
		
		//enhanced loop
		for (LinkedList<ConcordanceDataElement> list : concordanceLinkedList) {
			for (ConcordanceDataElement e : list) { concordanceList.add(e.toString()); }
		}
		
		//collection sort
		Collections.sort(concordanceList);
		
		
		//loop
		for (int i = 0; i < concordanceList.size(); i++) {
			//check
			if (concordanceList.get(i).contains("'")) {
				//new variable
				String j = concordanceList.get(i);
				
				//set
				concordanceList.set(i, concordanceList.get(i + 1));
				concordanceList.set(i + 1, j);
				
				//increment
				i++;
			}
		}
		return concordanceList;
	}

}