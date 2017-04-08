/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          p4
// FILE:             Interval.java
//
// Authors: Zexing Li (Richard), Bryan Watson, Mason Gomm
//
// Author1: Zexing Li (Richard), zli674@wisc.edu, zexing, lec001
// Author2: Bryan Watson, bmwatson2@wisc.edu, bmwatson2, lec001
// Author3: Mason Gomm, Mgomm@wisc.edu, mgomm, lec001
//
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/a
// 
// Online sources: N/a
//////////////////////////// 80 columns wide //////////////////////////////////

import java.lang.IllegalArgumentException;

/**
 * Class that defines an interval containing a start and end date for an 
 * assignment that is to be entered into a schedule. This data member will
 * be stored in an IntervalTree. Contains a constructor requiring data, as
 * there are no setter methods, but contains getter methods for data fields.
 * Has an overlap, contains, and compareTo method.
 * 
 * @author Bryan
 * 
 */
public class Interval<T extends Comparable<T>> implements IntervalADT<T> 
{
	/**
	 * Comparable start value of the interval.
	 */
	private T start;

	/**
	 * Comparable end value of the interval.
	 */
	private T end;

	/**
	 * Label for the interval.
	 */
	private String label;

	/**
	 * Parameterized constructor for an interval to be used for an assignment
	 * in an IntervalTree. Constructed using the start value of the interval,
	 * the end value of the interval, and the label for the interval.
	 * 
	 * @param start The start value of the interval.
	 * @param end The end value of the interval.
	 * @param label The label for the interval.
	 * @throws IllegalArgumentException if start > end.
	 */
	public Interval(T start, T end, String label) 
			throws IllegalArgumentException
	{
		if (start.compareTo(end) > 0)
		{
			throw new IllegalArgumentException("IllegalArgumentException");
		}
		else
		{
			this.start = start;
			this.end = end;
			this.label = label;
		}
	}

	/**
	 * Getter method for the start value of the interval.
	 * 
	 * @return The start value of the interval.
	 */
	@Override
	public T getStart() 
	{
		return this.start;
	}

	/**
	 * Getter method for the end value of the interval.
	 * 
	 * @return The end value of the interval.
	 */
	@Override
	public T getEnd() 
	{
		return this.end;
	}

	/**
	 * Getter method for the label of the interval.
	 * 
	 * @return The label of the interval.
	 */
	@Override
	public String getLabel() 
	{
		return this.label;
	}

	/**
	 * Determines whether two intervals overlap each other.
	 * First interval [a,b], second interval [c,d].
	 * 
	 * @param other Target interval to compare to this interval for overlap.
	 * @return true if it overlaps, false if it doesn't
	 * @throws IllegalArgumentException if the other interval is null
	 */
	@Override
	public boolean overlaps(IntervalADT<T> other) 
			throws IllegalArgumentException 
	{
		// Check if other is null, throw exception
		if (other == null)
		{
			throw new IllegalArgumentException("IllegalArgumentException");
		}
		// Check if a > d
		else if (this.getStart().compareTo(other.getEnd()) > 0)
		{
			return false;
		}
		// Check if b < c
		else if (this.getEnd().compareTo(other.getStart()) < 0)
		{
			return false;
		}
		// If all checks passed, overlap occurs
		else
		{
			return true;
		}
	}

	/**
	 * Determines whether this interval contains a value.
	 * Interval is [a,b].
	 * 
	 * @param point Point to search for.
	 * @return true if point lies within the interval, false if it doesn't.
	 */
	@Override
	public boolean contains(T point) 
	{
		// Check if point > a
		if (point.compareTo(this.getStart()) > 0)
		{
			// Check if point < b
			if (point.compareTo(this.getEnd()) < 0)
			{
				return true;
			}
			// point > b, return false
			else
			{
				return false;
			}
		}
		// point < a, return false
		else
		{
			return false;
		}
	}

	/**
	 * Compares this interval with another. Intervals 
	 * are compared first on their start time. The end time is only considered
	 * if the start time is the same.
	 * First interval is [a,b]. Second interval is [c,d].
	 * 
	 * * <p>For example, if start times are different:</p>
	 * 
	 * <pre>
	 * [0,1] compared to [2,3]: returns -1 because 0 is before 2
	 * [2,3] compared to [0,1]: return 1 because 2 is after 0
	 * [0,4] compared to [2,3]: return -1 because 0 is before 2
	 * [2,3] compared to [0,4]: return 1 because 2 is after 0
	 * [0,3] compared to [2,4]: return -1 because 0 is before 2
	 * [2,4] compared to [0,3]: return 1 because 2 is after 0
	 * </pre>
	 * 
	 * <p>If start times are the same, compare based on end time:</p>
	 * <pre>
	 * [0,3] compared to [0,4]: return -1 because start is same and 3 is 
	 * before 4 [0,4] compared to [0,3]: return 1 because start is same and 
	 * 4 is after 3</pre>
	 * 
	 * <p>If start times and end times are same, return 0</p>
	 * <pre>[0,4] compared to [0,4]: return 0</pre>
	 * 
	 * @param other Interval to which compare this interval with.       
	 * @return negative if this interval's comes before the other interval,
	 * 		   positive if this interval comes after the other interval,
	 * 		   0 if the intervals are the same.
	 */
	@Override
	public int compareTo(IntervalADT<T> other) 
	{
		// Get comparator value for a and c
		int comparatorStart = this.getStart().compareTo(other.getStart());

		// If a < c, return -1
		if (comparatorStart < 0)
		{
			return -1;
		}
		// If a > c, return 1
		else if (comparatorStart > 0)
		{
			return 1;
		}
		// If a = c, compare b and d
		else
		{
			// Get comparator value for b and d
			int comparatorEnd = this.getEnd().compareTo(other.getEnd());

			// If b < d, return -1
			if (comparatorEnd < 0)
			{
				return -1;
			}
			// If b > d, return 1
			else if (comparatorEnd > 0)
			{
				return 1;
			}
			// If b = d, return 0
			else
			{
				return 0;
			}
		}
	}

	/**
	 * Returns a specific string representation of the interval. It must 
	 * return
	 *the interval in this form.
	 * 
	 *  <p>For example: If the interval's label is p1 and the start is 24 
	 *  and the end is 45, then the string returned is:</p>
	 *  
	 *  <pre>p1 [24, 45]</pre>
	 */
	@Override
	public String toString()
	{
		// Create new String variable
		String interval;

		// Add label to string
		interval = this.getLabel();

		// Add start of interval to string
		interval += " [" + this.getStart() + ", ";

		// Add end of interval to string
		interval += this.getEnd() + "]";

		// Return completed string
		return interval;
	}

}
