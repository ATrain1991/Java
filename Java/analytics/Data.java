// COURSE: CSCI1620
// TERM: Spring 2018
//
// NAME: Alex Schulte
// RESOURCES: I used StackOverflow in order to help with writing my junit tests.
package analytics;
/**
 * 
 * Set of useful reusable tools for analyzing sets of data.
 * 
 * @author alex
 * 
 */
public class Data 
{
	/**
	 * Finds the minimum value in the passed array.
	 * 
	 * @param <E> generic representation of the passed variable.
	 * @param data array of data that is sorted through.
	 * @return the minimum value in the array.
	 */
	public static <E extends Comparable<E>> E minimum(E[] data)
	{
		E min = data[0];
		for (int i = 0; i < data.length; i++)
		{
			if (min.compareTo(data[i]) > 0)
			{
				min = data[i];
			}
		}
		return min;
	}
	/**
	 * Finds the maximum value in the passed array.
	 * 
	 * 
	 * @param <E> generic representation of the passed variable.
	 * @param data array of data that is sorted through.
	 * @return the maximum value in the array.
	 */
	public static <E extends Comparable<E>> E maximum(E[] data)
	{
		E max = data[0];
		for (int i = 0; i < data.length; i++)
		{
			if (max.compareTo(data[i]) < 0)
			{
				max = data[i];
	
			}
		}
		return max;
	}
	/**
	 * Finds the average of values in the passed array.
	 * 
	 * @param <N> generic representation of the passed variable.
	 * @param data array of data that is sorted through.
	 * @return the average value of all data in the array.
	 */
	public static <N extends Number> Double average(N[] data)
	{
		double avg = 0;
		for (int i = 0; i < data.length; i++)
		{
			avg += data[i].doubleValue();
		}
		return (Double) avg / data.length;
	}
	/**
	 * Finds the population standard deviation of values in the passed array. 
	 * 
	 * 
	 * @param <N> generic representation of the passed variable.
	 * @param data array of data that is sorted through.
	 * @return the standard deviation value of all data in the array.
	 */
	public static <N extends Number> Double standardDeviation(N[] data)
	{
		double mean = average(data);
		Double[] difference = new Double[data.length];
		for (int i = 0; i < data.length; i++)
		{ 
			difference[i] = Math.pow(data[i].doubleValue() - mean, 2);
		}
		return Math.sqrt(average(difference));
	}
}
