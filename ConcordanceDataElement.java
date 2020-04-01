/**
 * This class is the data element class for the ConcordanceDataStructure.
 * It consists of a word (String) and a list of page numbers (LinkedList).
 * @author Shenabeth Jenkins
 */

import java.util.LinkedList;

public class ConcordanceDataElement implements Comparable<ConcordanceDataElement> {
	//fields
	private LinkedList<Integer> list;
	private String word;

	
	//constructors
	/**
	 * 1 arg constructor
	 * @param word the word for the concordance data element
	 */
	public ConcordanceDataElement(String word) {
		this.word = word;
		
		//create new list
		list = new LinkedList<>();
	}

	
	//methods
	/**
	 * Return the word portion of the Concordance Data Element
	 * @return the word portion of the Concordance Data Element
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Returns the hashCode
	 * You may use the String class hashCode method
	 */
	public int hashCode() {
		return word.hashCode();
	}
	
	/**
	 * Returns the linked list of integers that represent the line numbers
	 * @return the linked list of integers that represent the line numbers
	 */
	public LinkedList<Integer> getList() {
		return list;
	}
	
	/**
	 * Add a line number to the linked list if the number doesn't exist in the list
	 * @param lineNum the line number to add to the linked list
	 */
	public void addPage(int lineNum) {
		//if the number doesn't exist in the list
		if (!list.contains(lineNum)) {
			//Add a line number to the linked list
			list.add(lineNum);
		}
	}
	
	
	//overridden methods
	/**
	 * compareTo in interface java.lang.Comparable<ConcordanceDataElement>
	 */
	@Override
	public int compareTo(ConcordanceDataElement arg0) {        
		//return IO exception
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the word followed by page numbers
	 * Returns a string in the following format: word: page num, page num
	 * Example: after: 2,8,15
	 */
	@Override
	public String toString() {
		//declare variables
		String concordanceString = word + ": ";
		
		//loop
		for (int i = 0; i < list.size(); i++) {
			//add to concordance
			//if ( i == 0) concordanceString += list.get(i);
			//else concordanceString += (", " + list.get(i));
			concordanceString += (i == 0 ? list.get(i) : ", " + list.get(i));
		}
		
		//return
		return concordanceString;
	}

}