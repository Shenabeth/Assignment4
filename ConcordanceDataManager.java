/**
 * This is the manager class
 * @author Shenabeth Jenkins
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class ConcordanceDataManager implements ConcordanceDataManagerInterface {

	/**
	 * Create concordance array
	 */
	@Override
	public ArrayList<String> createConcordanceArray(String input) {
		//create variables
		String[] wordLine = input.split("\n");
		String[][] words = new String[wordLine.length][];
		
		//loop
		for (int i = 0; i < words.length; i++) {
			//replace characters
			words[i] = wordLine[i].replaceAll("[\\p{P}&&[^\\u0027]]", "").toLowerCase().split(" ");
		}

		//create the data
		ConcordanceDataStructure dataStructure = new ConcordanceDataStructure(words.length);

		//loop
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length; j++) {
				//ignoring cases
				if (!words[i][j].equalsIgnoreCase("and") && !words[i][j].equalsIgnoreCase("the") && words[i][j].length() >= 3)
					//add
					dataStructure.add(words[i][j], i + 1);
			}
		}
		
		//return the data
		return dataStructure.showAll();
	}

	/**
	 * Create concordance file
	 */
	@Override
	public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
		//create variables
		Scanner inputReader = new Scanner(input);
		String words = "";
		
		//reading lines of words
		while (inputReader.hasNextLine()) {
			words += inputReader.nextLine() + "\n"; }

		//create the concordance
		ArrayList<String> concordanceList = createConcordanceArray(words);

		//create the output
		PrintWriter outputWriter = new PrintWriter(output);

		//enhanced loop to write output
		for (String e : concordanceList) {
			outputWriter.println(e); }

		//close input
		inputReader.close();
		
		//close output
		outputWriter.close();

		//return
		return true;
	}

}