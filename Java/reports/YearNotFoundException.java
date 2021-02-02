// COURSE: CSCI1620
// TERM: Spring 2018
//
// NAME: Alex Schulte
// RESOURCES: I used StackOverflow in order to help with writing my junit tests.
package reports;
/**
 * An Exception to be thrown if a requested year is not present in the data set.
 * 
 * @author alex
 *
 */
public class YearNotFoundException extends RuntimeException
{
 	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets the message of the Exception to "Requested year not in file".
	 */
	public YearNotFoundException()
	{
	super("Requested year not in file");
	}
}
