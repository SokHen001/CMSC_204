import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class checks for:
 * 1.Length of password
 * 2.Has uppercase alpha character 
 * 3.Has lowercase alpha character
 * 4.Has special character
 * 5.Has digit
 * 6.No same sequence password
 * 7.whether password is weak
 * 8.confirm password match/unmatch
 * If any of the above condition is not met, then it will throws exceptions.
 * @author Sokha Heng
 * Project: 1
 * Due Date: 09/17/2024
 */

public class PasswordCheckerUtility {

	/**
	 * 
	 * @param password
	 * @return false if password length is not between 6 and 9
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		if (password.length() >= 6 && password.length() <= 9) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param password
	 * @return true if password has digit
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(password);

		try {
			if (!matcher.find()) {
				throw new NoDigitException();
			}
		} catch (NoDigitException e) {
			throw new NoDigitException();
		}
		return true;
	}

	/**
	 * 
	 * @param password
	 * @return true if password has lower alphabetic character
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		Pattern pattern = Pattern.compile("[a-z]");
		Matcher matcher = pattern.matcher(password);

		try {
			if (!matcher.find()) {
				throw new NoLowerAlphaException();
			}
		} catch (NoLowerAlphaException e) {
			throw new NoLowerAlphaException();
		}
		return true;
	}
	
	/**
	 * 
	 * @param password
	 * @return true if password has upper alphabetic character
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(password);

		try {
			if (!matcher.find()) {
				throw new NoUpperAlphaException();
			}
		} catch (NoUpperAlphaException e) {
			throw new NoUpperAlphaException();
		}
		return true;
	}
	
	/**
	 * 
	 * @param password
	 * @return true if password has special character
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(password);

		try {
			if (!matcher.find()) {
				throw new NoSpecialCharacterException();
			}
		} catch (NoSpecialCharacterException e) {
			throw new NoSpecialCharacterException();
		}
		return true;
	}

	/**
	 * 
	 * @param password
	 * @return true if password meets minimum length requirement
	 * @throws LengthException
	 */
	public static boolean isValidLength(String password) throws LengthException {
		try {
			if (!(password.length() > 6)) {
				throw new LengthException();
			}
		} catch (LengthException e) {
			throw new LengthException();
		}
		return true;
	}

	/**
	 * 
	 * @param password
	 * @return false if the password is valid and the length of password is not between 6 and 9
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		try {
			if (hasBetweenSixAndNineChars(password)) {
				throw new WeakPasswordException();
			}
		} catch (WeakPasswordException e) {
			throw new WeakPasswordException();
		}
		return false;
	}

	/**
	 * 
	 * @param password
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException
	 */
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
		Pattern pattern = Pattern.compile("((.)\\2{2,})");
		Matcher matcher = pattern.matcher(password);
		
		try {
			if(matcher.find()) {
				throw new InvalidSequenceException();
			}
		} catch (InvalidSequenceException e){
			throw new InvalidSequenceException();
		}
		return true;
	}

	/**
	 * 
	 * @param password
	 * @return true if valid password, false if invalid password
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException 
	{
		try {
			if (isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password) && hasSpecialChar(password) && NoSameCharInSequence(password)) 
			{
				return true;
			}
		} 
		catch (LengthException e){
			throw new LengthException();
		}
		catch (NoUpperAlphaException e) {
			throw new NoUpperAlphaException();
		}
		catch (NoLowerAlphaException e) {
			throw new NoLowerAlphaException();
		}
		catch (NoDigitException e) {
			throw new NoDigitException();
		}
		catch (NoSpecialCharacterException e) {
			throw new NoSpecialCharacterException();
		} 
		catch (InvalidSequenceException e) {
			throw new InvalidSequenceException();
		}
		return false;
	}

	/**
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @return true if both matches, false otherwise
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if (password.equals(passwordConfirm)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		try {
			if (!password.equals(passwordConfirm)) {
				throw new UnmatchedException();
			}
		} catch (UnmatchedException e) {
			throw new UnmatchedException();
		}
	}

	/**
	 * 
	 * @param passwords
	 * @return ArrayList of invalid passwords in the correct format
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> invalidPasswords = new ArrayList<>();
		for (int i = 0; i < passwords.size(); i++) {
			try {
				isValidPassword(passwords.get(i));
			} catch (Exception e) {
				invalidPasswords.add(passwords + ":" + e.getMessage());
			}
		}
		return invalidPasswords;
	}

}
