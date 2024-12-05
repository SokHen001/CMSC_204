import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Sokha Heng
 * Project 5: Morse Code
 * Due date: 12/04/2024
 */
public class MorseCodeConverter extends Object{
	
	private static MorseCodeTree Tree = new MorseCodeTree();
	
	// constructor
	MorseCodeConverter(){
	}
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a
	 * space (‘ ‘). Each word is delimited by a ‘/’.
	 * 
	 * @param codeFile - name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		if (codeFile == null || !codeFile.exists()) {
			throw new FileNotFoundException();
		}
		
		Scanner scanner = new Scanner(codeFile);
		StringBuilder text = new StringBuilder();
		
//		while (scanner.hasNextLine()) {
//			String lineOfMorseCode = scanner.nextLine().trim();
//			if (!lineOfMorseCode.isEmpty()) {
//				String lineText = convertToEnglish(lineOfMorseCode);
//				if (lineText != null) {
//					text.append(lineText).append("\n");
//				}
//			}
//		}
		
		while (scanner.hasNextLine()) {
			String lineOfMorseCode = scanner.nextLine().trim();
			if(!lineOfMorseCode.isEmpty()) {
				text.append(convertToEnglish(lineOfMorseCode)).append("\n");
			}
		}
		
		scanner.close();
		return text.toString().trim();
	}

	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘).
	 * Each word is delimited by a ‘/’.
	 * 
	 * @param code - the morse code
	 * @return the English translation
	 */
	public static String convertToEnglish(String code) {
		
		if(code == null || code.trim().isEmpty()) {
			return "";
		}
		
		code = code.trim().replaceAll(" +", " ");
			
		StringBuilder text = new StringBuilder();
		String[] words = code.split(" / ");
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			
			String[] letters = word.split(" ");
			
			for (int j = 0; j < letters.length; j++) {
				String letter = letters[j];
				
				if (!letter.isEmpty()) {
					text.append(Tree.fetch(letter));
				}
			}
			
			if(i < words.length - 1) {
				text.append(" ");
			}
			
		}
		return text.toString().trim();
		
	}
	
//	public static String convertToEnglish(String code) {
//	    if (code == null || code.trim().isEmpty()) {
//	        return "";
//	    }
//
//	    code = code.trim().replaceAll(" +", " "); // Normalize spaces
//	    StringBuilder text = new StringBuilder();
//	    String[] words = code.split(" / "); // Split words by the / delimiter
//
//	    for (int i = 0; i < words.length; i++) {
//	        String word = words[i];
//	        String[] letters = word.split(" "); // Split word by space to get individual letters
//
//	        for (int j = 0; j < letters.length; j++) {
//	            String letter = letters[j];
//	            if (!letter.isEmpty()) {
//	                String translatedLetter = Tree.fetch(letter); // Fetch corresponding letter
//	                if (translatedLetter != null) {
//	                    text.append(translatedLetter);
//	                } else {
//	                    text.append("?"); // If letter is invalid, append a placeholder
//	                }
//	            }
//	        }
//
//	        if (i < words.length - 1) {
//	            text.append(" "); // Separate words with a space
//	        }
//	    }
//
//	    return text.toString().trim(); // Trim to remove leading/trailing spaces
//	}
//	
//	
	
	/**
	 * returns a string with all the data in the tree in LNR order with an space in
	 * between them. Uses the toArrayList method in MorseCodeTree
	 * 
	 * @return the data in the tree in LNR order separated by a space.
	 */
	public static String printTree() {
		ArrayList<String> treeData = Tree.toArrayList();
		
		String result = "";
		
		for (int i = 0; i < treeData.size(); i++) {
			result += treeData.get(i);
			
			if (i < treeData.size() - 1) {
				result += " ";
			}
		}
		
		return result.trim();
	}

}
