package dictionary;

import java.util.ArrayList;

public interface DictionaryProc {
	

	/**
	 * Loads the content of the dictionary from a specific file.
	 * When loading the content, old content will be erased, so this may actually lead to a decrease in size in some conditions.
	 * @pre dictionary!=null
	 * @pre file exists
	 * 
	 * @post @inv
	 * (isConsistent)
	 * 
	 */
	public void populate();
	
	/**
	 * Adds a new word to the dictionary. It may also be used to add a new synonym to a word.
	 * A word can not have itself as a synonym
	 * @param word word to add to the dictionary
	 * @param synonym synonym of the added word
	 * 
	 * @pre @invariant 
	 * @pre word!=null && synonym!=null
	 * @pre word has only letters
	 * @pre synonym has only letters
	 * @pre !word.equals(synonym)
	 * 
	 * @post synonym does not appear more than once
	 * @post preSize<size;
	 * @post @inv
	 * 
	 */
	public void add(String word,String synonym);
	
	/**
	 * Removes word from dictionary
	 * @param word word to remove
	 * @pre @inv
	 * @pre word!=null
	 * @pre contains(word)==true
	 * @pre size>0
	 * @pre word has only letters
	 * 
	 * @post preSize>size
	 * @post contains(word)==false
	 * @post @inv
	 * 
	 */
	public void remove(String word);
	
	/**
	 * Saves the dictionary to a file.
	 * @pre @inv
	 * @pre true
	 * 
	 * @post file contains dictionary
	 * @post @nochange
	 * @post @inv
	 */
	public void save();
	
	/**
	 * Searches for a word in the dictionary. 
	 * The characters '*' and '?' play the role of wildcards and can be used to search for words with a specified pattern
	 * '?' replaces a single character
	 * '*' replaces a variable number of characters
	 * @param word word to search for
	 * @return String ArrayList which contains all the matching words
	 * 
	 * @pre @inv
	 * @pre word!=null
	 * @pre word has only letters and '*' and '?'
	 * @pre !word.contains("?*") && !word.contains("*?")
	 * 
	 * @post @nochange
	 * @post @inv
	 */
	public ArrayList<String> search(String word);
	
	/**
	 * Verifies if the dictionary contains a specific word
	 * @param word word to be contained by the dictionary
	 * @return true if word is contained, false if not
	 * 
	 * @pre @inv// invariant can't be used because contains(String word) method is used in the invariant's implementation
	 * @pre word!=null
	 * @pre word has only letters 
	 * 
	 * @post @inv// invariant can't be used because contains(String word) method is used in the invariant's implementation
	 * @post @nochange
	 */
	public boolean contains(String word);

}
