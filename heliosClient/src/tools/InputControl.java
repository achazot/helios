package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputControl {
	
	/**
	 * 
	 * @param field
	 * @return true if the field contains only alphabetic characters 
	 */
	public static boolean checkOnlyAlphabetic(String field)
	{
		Pattern p = Pattern.compile("([a-zA-Z]){1,10}"); 
		Matcher m = p.matcher(field); 
		boolean b = m.matches() ;
		return b;
	}

	/**
	 * 
	 * @param mail
	 * @return true if mail matches with <alphanumeric>.<alphanumeric>@helios.fr or <alphanumeric>@helios.fr
	 */
	public static boolean checkMail(String mail) 
	{
		Pattern p = Pattern.compile("^[a-zA-Z0-9.]+@helios.fr"); 
		Matcher m = p.matcher(mail); 
		boolean b = m.matches() ;
		return b;
	}

	/**
	 * 
	 * @param field
	 * @return true if the field does not contains SQL words reserved 
	 */
	public static boolean checkNoSQL(String field) 
	{
		Pattern p = Pattern.compile(".*(ALTER|CREATE|DELETE|DROP|EXEC(UTE){0,1}|INSERT( +INTO){0,1}|MERGE|SELECT|UPDATE|UNION( +ALL){0,1}).*", Pattern.CASE_INSENSITIVE); 
		Matcher m = p.matcher(field); 
		boolean b = m.matches() ;
		return ( b == false );
	}
}
