// COURSE: CSCI1620
// TERM: Spring 2018
//
// NAME: Alex Schulte
// RESOURCES: I used StackOverflow in order to help with writing my junit tests.
package reports;
/**
 * An Exception to be thrown if it is attempted to write a report that has not been processed.
 * 
 * @author alex
 *
 */
public class DataNotProcessedException extends RuntimeException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets the message of the Exception to "Data not processed, cannot write
	 * report".
	 */
	public DataNotProcessedException()
	{
	super("Data not processed, cannot write report");
	}
}
