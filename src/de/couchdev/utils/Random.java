package de.couchdev.utils;

public class Random
{
	private static int _count;
	private static int[] _counter;
	private static int _currentMin;
	private static int _currentMax;
	private static int _range;
	private static int _offset;

	/**
	 * Initiate a new random number provider with the default minimum 1 an maximum 100.
	 */
	public Random()
	{
		if(_counter==null)
		{
			_count = 0;
			_currentMin = 1;
			_currentMax = 100;
			_counter = new int[_currentMax+1];
			_range = _currentMax-_currentMin+1;
			_offset=_range/20*7+3;
		}
	}
	
	/**
	 * Initiate a new random number provider with custom minimum and maximum.
	 * @param min The minimum value that can come out.
	 * @param max The maximum number that can come out.
	 */
	public Random(int min, int max)
	{
		assert(max > min);
		_count = 0;
		_currentMin = min;
		_currentMax = max;
		_counter = new int[_currentMax+1];
		_range = _currentMax-_currentMin+1;
		_offset=_range/20*7+3;
	}
	
	/**
	 * Get the currently set minimum value.
	 * @return The current minimum.
	 */
	public int getMin()
	{
		return _currentMin;
	}
	
	/**
	 * Get the currently set maximum value.
	 * @return The current maximum.
	 */
	public int getMax()
	{
		return _currentMax;
	}
	
	/**
	 * Calculates <b>amount</b> random integer numbers between <b>min</b> and <b>max</b>.
	 * <br>(including <b>min</b> and <b>max</b>)
	 * 
	 * @param min The minimum number to come out.
	 * @param max The maximum number to come out.
	 * @param amount The length of the returned array.
	 * @return An array of random integer numbers.
	 */
	public int[] getBetween(int min, int max, int amount)
	{
		int[] numbers = new int[amount];
		if(min<max && min>=0 && amount>0)
		{
			_count = 0;
			_counter = new int[max+1];
			for(int i=0; i<max; ++i)
			{
				_counter[i]=0;
			}
			for(int i=0; i<amount; ++i)
			{
				numbers[i] = getBetween(min,max);
			}
		}
		return numbers;
	}
	
	/**
	 * Generates and returns a random number between the current minimum and maximum value.
	 * @return A new random number.
	 */
	public int next()
	{
		int result = -1;
		if(_counter!=null)
		{
			result = getBetween(_currentMin, _currentMax);
		}
		return result;
	}
	
	private int getBetween(int min, int max)
	{
		int range = max - min + 1;
		if(min<max && min>=0)
		{
			return getNumber(range)+min;
		}
		return -1;
	}
	
	private int getNumber(int max)
	{
		if(max>0 && max<1000)
		{
			int t = (int) System.nanoTime()>>10;
			if(t<0)
			{
				t=-t;
			}
			int n = t%max;
			int c=0;
			int dead=0;
			while(_counter[n]>_count)
			{
				n = (n+_offset)%max;
				++c;
				if(c==max)
				{
					++_count;
				}
				dead++;
				if(dead == 10000)
					break;
			}
			++_counter[n];
			return n;
		}
		return -1;
	}
}

