package dictionary;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Dictionary implements DictionaryProc, Serializable {

	private Map<String,ArrayList<String>> map;
	private File file;
	private static final long serialVersionUID = -1088612300522001453L;
	
	
	public Dictionary(){
		map=new HashMap<String,ArrayList<String>>();
		file=new File("src\\resource\\dictionary.bin");
		if(!file.exists() && !file.isDirectory()) {
			ClassLoader cl = this.getClass().getClassLoader();
			InputStream read= cl.getResourceAsStream("resource/Cuvinte.txt");
			
			String line;
			String[] words,syn;
			int i;
			try {
				BufferedReader in=new BufferedReader(new InputStreamReader(read));
				line=in.readLine();
				while(line!=null){
					words=line.split(" - ");
					syn=words[1].split(", ");
					for(i=0;i<syn.length;i++)
						add(words[0],syn[i]);
					line=in.readLine();
					
				}
				in.close();
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR reading words from file!","ERROR",JOptionPane.ERROR_MESSAGE );
			}
			
			}
		
	
	}
	
	private boolean isConsistent(){
		 
		 if(map.size()==0)
			 return true;
		//ArrayList<String> temp;
		Iterator<String> j;
		String syn;
		for(String i:map.keySet())
		{
			//temp=map.get(i);
			j=map.get(i).iterator();
			while(j.hasNext()){
				syn=j.next();
				if(!contains(syn))
					return false;
			}
				
			
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void populate() {
		assert file!=null;
		assert map!=null;
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			map=((HashMap<String, ArrayList<String> >)in.readObject());
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		assert isConsistent();

	}
	
	public boolean preWord(String word){
		if(word==null)
			return false;
		if(word=="")
			return false;
		char[] temp=word.toCharArray();
		for(int i=0;i<temp.length;i++)
			if(!Character.isLetter(temp[i]))
				{if(temp[i]!='?' &&temp[i]!='*')
					return false;
				if(i<temp.length-1)
				if(temp[i]=='*'&&temp[i+1]=='*')
					return false;
				}
		if(word.contains("?*")||word.contains("*?"))
			return false;
		return true;
	}
	
	boolean hasOnlyLetters(String word){
		if(!preWord(word))
			return false;
		if(word.contains("*")||word.contains("?"))
			return false;
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(String word, String synonym) {
		assert isConsistent();
		assert hasOnlyLetters(word) && hasOnlyLetters(synonym);
		assert !word.equals(synonym);
		int preSize=map.size();
		word=word.toLowerCase();
		synonym=synonym.toLowerCase();
		ArrayList<String> temp,reTemp;
		if(map.containsKey(word))
		{
			temp=map.get(word);
			if(!temp.contains(synonym))
				temp.add(synonym);
		}
		else
		{
			temp=new ArrayList<String>();
			temp.add(synonym);
			map.put(word, temp);
			
		}
		
		if(!isConsistent()){
			reTemp=((ArrayList<String>)temp.clone());
			reTemp.add(word);
			reTemp.remove(synonym);
			map.put(synonym, reTemp);
		}
		
		assert preSize<map.size();
		assert isConsistent();

	}
	@Override
	public void remove(String word){
				assert isConsistent();
				assert hasOnlyLetters(word);
				assert map.containsKey(word);
				assert map.size()>0;
				int preSize=map.size();
				map.remove(word);
				ArrayList<String> temp;
				TreeSet<String> tree=new TreeSet<String>(map.keySet());
				Iterator<String> i=tree.iterator();
				String key;
				while(i.hasNext()){
					key=i.next();
					temp=map.get(key);
					if(temp.contains(word)){
						temp.remove(word);
						if(temp.size()==0)
							recursiveRemove(key);
					}
						
				}
				assert isConsistent();
				assert preSize>map.size();
				assert !contains(word);
		
	}
	
	private void recursiveRemove(String word) {
		map.remove(word);
		ArrayList<String> temp;
		TreeSet<String> tree=new TreeSet<String>(map.keySet());
		Iterator<String> i=tree.iterator();
		String key;
		while(i.hasNext()){
			key=i.next();
			temp=map.get(key);
			if(temp.contains(word)){
				temp.remove(word);
				if(temp.size()==0)
					remove(key);
			}
				
		}
	}

	@Override
	public void save() {
		assert isConsistent();
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(map);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assert isConsistent();

	}

	@Override
	public ArrayList<String> search(String word) {
		assert isConsistent();
		assert preWord(word);
		word=word.replace("*", ".*");
		word=word.replace("?", ".");
		//System.out.println(word);
		word=word.toLowerCase();
		ArrayList<String> result=new ArrayList<String>();
		for(String i:map.keySet())
			if(i.matches(word))
				result.add(i);
		assert isConsistent();
		return result;
	}

	@Override
	public boolean contains(String word) {
		assert hasOnlyLetters(word);
		return map.containsKey(word);

	}
	
	@Override
	/**
	 * Returns a String with all the words in the dictionary and their synonyms.
	 * The format, which is the standard input/output format for this application is:
	 * word1 - synonym1, synonym2, ..., synonym[n]
	 * .
	 * .
	 * .
	 * word[n] - synonym1, synonym2, ..., synonym[n]
	 */
	public String toString(){
		assert isConsistent();
		String x=new String();
		Iterator<String> a;
		String temp;
		TreeSet<String> keys=new TreeSet<String>(map.keySet());
		
		for(String i:keys){
			x+=(i+" - ");
			for(a=map.get(i).iterator();a.hasNext(); ){
				
				temp=a.next();
				if(a.hasNext())
					x+=temp+", ";
				else
					x+=temp;
			}
			x+="\r\n";
		}
		assert isConsistent();
		return x;
	}
	
	String[] getWordArray(){
		return (map.keySet().toArray(new String[map.size()]));
	}
	
	/**
	 * Returns the array of synonyms for the given word
	 * @param key word to search for synonyms
	 * @return String[] containing the synonyms of key
	 */
	public ArrayList<String> getSynonymsArray(String key){
			return map.get(key);
	}
	
	/**
	 * Add new words to the dictionary from the file specified in the path.
	 * In order to avoid errors, the file must respect the input format
	 * @param pathname path to file
	 */
	public void addFromFile(String pathname){
		File source=new File(pathname);
		String line;
		String[] words,syn;
		int i;
		try {
			BufferedReader in=new BufferedReader(new FileReader(source));
			line=in.readLine();
			while(line!=null){
				words=line.split(" - ");
				syn=words[1].split(", ");
				for(i=0;i<syn.length;i++)
					add(words[0],syn[i]);
				line=in.readLine();
				
			}
			in.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR reading words from file!","ERROR",JOptionPane.ERROR_MESSAGE );
		}
	}
	
	/**
	 * Empties the dictionary
	 */
	public void clear(){
		map.clear();
	}
	
	
	
	    
}
