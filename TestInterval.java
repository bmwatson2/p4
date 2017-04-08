import java.lang.IllegalArgumentException;

public class TestInterval 
{
	static int testsRun = 0;
	static int testsPassed = 0;
	static int testsFailed = 0;
	
	public static void main(String[] args)
	{
		String Sexpected;
		String Sactual = null;
		int Iexpected;
		int Iactual;
		boolean Bexpected;
		boolean Bactual;
		Interval<Integer> testInterval1;
		
		// Test Constructor, valid case
		System.out.println("Constructor valid test case");
		testsRun++;
		try
		{
			int start1 = 0;
			int end1 = 5;
			String test1 = "test1";
			testInterval1 = new Interval<Integer>(start1, end1, test1);
			System.out.println("Test Passed" + '\n');
			testsPassed++;
		}
		catch (Exception e)
		{
			System.out.println("Test Failed" + '\n');
			testsFailed++;
		}
		
		// Assuming test passes, initiate for further use
		int start1 = 0;
		int end1 = 5;
		String test1 = "test1";
		testInterval1 = new Interval<Integer>(start1, end1, test1);
	
		// Test Constructor, invalid case
		System.out.println("Constructor invalid test case");
		Exception e = new IllegalArgumentException("IllegalArgumentException");
		Sexpected = e.getMessage();
		try
		{
			int start2 = 5;
			int end2 = 0;
			String test2 = "test2";
			@SuppressWarnings("unused")
			Interval<Integer> testInterval2 = new Interval<Integer>(start2, end2, test2);
		}
		catch (IllegalArgumentException iae)
		{
			Sactual = iae.getMessage();
		}
		stringTest(Sexpected, Sactual);
		
		// Test getStart() method
		System.out.println("getStart() Testing");
		Iexpected = 0;
		Iactual = testInterval1.getStart();
		intTest(Iexpected, Iactual);
		
		// Test getEnd() method
		System.out.println("getEnd() Testing");
		Iexpected = 5;
		Iactual = testInterval1.getEnd();
		intTest(Iexpected, Iactual);
		
		// Test getLabel() method
		System.out.println("getLabel() Testing");
		Sexpected = "test1";
		Sactual = testInterval1.getLabel();
		
		// Test overlaps(IntervalADT<T> other) method
		// Interval that overlaps both within
		System.out.println("overlaps(IntervalADT<T> other) testing");
		int start3 = 2;
		int end3 = 4;
		String test3 = "test3";
		Interval<Integer> testInterval3 = new Interval<Integer>(start3, end3, test3);
		Bexpected = true;
		Bactual = testInterval1.overlaps(testInterval3);
		booleanTest(Bexpected, Bactual);
		
		// Interval that overlaps both outside
		int start4 = -5;
		int end4 = 5;
		String test4 = "test4";
		Interval<Integer> testInterval4 = new Interval<Integer>(start4, end4, test4);
		Bexpected = true;
		Bactual = testInterval1.overlaps(testInterval4);
		booleanTest(Bexpected, Bactual);
		
		// Interval that overlaps only lower
		int start5 = 0;
		int end5 = 3;
		String test5 = "test5";
		Interval<Integer> testInterval5 = new Interval<Integer>(start5, end5, test5);
		Bexpected = true;
		Bactual = testInterval1.overlaps(testInterval5);
		booleanTest(Bexpected, Bactual);
		
		// Interval that overlaps only upper
		int start6 = 2;
		int end6 = 10;
		String test6 = "test6";
		Interval<Integer> testInterval6 = new Interval<Integer>(start6, end6, test6);
		Bexpected = true;
		Bactual = testInterval1.overlaps(testInterval6);
		booleanTest(Bexpected, Bactual);
		
		// Interval that is below, doesn't overlap
		int start7 = -5;
		int end7 = -1;
		String test7 = "test7";
		Interval<Integer> testInterval7 = new Interval<Integer>(start7, end7, test7);
		Bexpected = false;
		Bactual = testInterval1.overlaps(testInterval7);
		booleanTest(Bexpected, Bactual);
		
		// Interval that is above, doesn't overlap
		int start8 = 6;
		int end8 = 10;
		String test8 = "test8";
		Interval<Integer> testInterval8 = new Interval<Integer>(start8, end8, test8);
		Bactual = false;
		Bexpected = testInterval1.overlaps(testInterval8);
		booleanTest(Bexpected, Bactual);
		
		// Null interval passed
		try
		{
			IllegalArgumentException e2 = new IllegalArgumentException("IllegalArgumentException");
			Interval<Integer> testInterval9 = null;
			Sexpected = e2.getMessage();
			testInterval1.overlaps(testInterval9);
		}
		catch(IllegalArgumentException iae)
		{
			Sactual = iae.getMessage();
		}
		stringTest(Sexpected, Sactual);
		
		// Test contains(T point) method
		// interval contains point
		System.out.println("contains(T point) Testing");
		int point1 = 2;
		Bexpected = true;
		Bactual = testInterval1.contains(point1);
		booleanTest(Bexpected, Bactual);
		
		// point is outside of interval above
		int point2 = 6;
		Bexpected = false;
		Bactual = testInterval1.contains(point2);
		booleanTest(Bexpected, Bactual);
		
		// point is outside of interval below
		int point3 = -2;
		Bexpected = false;
		Bactual = testInterval1.contains(point3);
		
		// Test compareTo(IntervalADT<T> other) method
		// Start below
		System.out.println("compareTo(IntervalADT<T> other) testing");
		int start10 = 2;
		int end10 = 5;
		String  test10 = "test10";
		Interval<Integer> testInterval10 = new Interval<Integer>(start10, end10, test10);
		Iexpected = -1;
		Iactual = testInterval1.compareTo(testInterval10);
		intTest(Iexpected, Iactual);
		
		// Start above
		int start11 = -1;
		int end11 = 5;
		String test11 = "test11";
		Interval<Integer> testInterval11 = new Interval<Integer>(start11, end11, test11);
		Iexpected = 1;
		Iactual = testInterval1.compareTo(testInterval11);
		intTest(Iexpected, Iactual);
		
		// Start same, end below
		int start12 = 0;
		int end12 = 6;
		String test12 = "test12";
		Interval<Integer> testInterval12 = new Interval<Integer>(start12, end12, test12);
		Iexpected = -1;
		Iactual = testInterval1.compareTo(testInterval12);
		intTest(Iexpected, Iactual);
		
		// Start same, end above
		int start13 = 0;
		int end13 = 3;
		String test13 = "test13";
		Interval<Integer> testInterval13 = new Interval<Integer>(start13, end13, test13);
		Iexpected = 1;
		Iactual = testInterval1.compareTo(testInterval13);
		intTest(Iexpected, Iactual);
		
		// Start same, end same
		int start14 = 0;
		int end14 = 5;
		String test14 = "test14";
		Interval<Integer> testInterval14 = new Interval<Integer>(start14, end14, test14);
		Iexpected = 0;
		Iactual = testInterval1.compareTo(testInterval14);
		intTest(Iexpected, Iactual);
		
		// Test toString() method
		System.out.println("toString() testing");
		Sexpected = "test1 [0, 5]";
		Sactual = testInterval1.toString();
		stringTest(Sexpected, Sactual);
		
		// Results
		System.out.println("Results");
		System.out.println("=====================================");
		System.out.println("Tests Run: " + testsRun);
		System.out.println("Tests Passed: " + testsPassed);
		System.out.println("Tests Failed: " + testsFailed);
	}
	
	private static void intTest(int expected, int actual)
	{
		testsRun++;
		if (expected == actual)
		{
			System.out.println("Test Passed" + '\n');
			testsPassed++;
		}
		else
		{
			System.out.println("Test Failed" + '\n');
			testsFailed++;
		}
	}
	
	private static void stringTest(String expected, String actual)
	{
		testsRun++;
		if (expected.equalsIgnoreCase(actual))
		{
			System.out.println("Test Passed" + '\n');
			testsPassed++;
		}
		else
		{
			System.out.println("Test Failed" + '\n');
			testsFailed++;
		}
	}
	
	private static void booleanTest(boolean expected, boolean actual)
	{
		testsRun++;
		if (expected)
		{
			if (actual)
			{
				System.out.println("Test Passed" + '\n');
				testsPassed++;
			}
			else
			{
				System.out.println("Test Failed" + '\n');
				testsFailed++;
			}
		}
		else
		{
			if (!actual)
			{
				System.out.println("Test Passed" + '\n');
				testsPassed++;
			}
			else
			{
				System.out.println("Test Failed" + '\n');
				testsFailed++;
			}
		}
	}
}
