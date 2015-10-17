package de.couchdev.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;


public class Utilities {

	/**
	 * Euler's number
	 */
	public static final double e = 2.71828182846;
	
	/**
	 * Sums all values of the given Collection if it consists of numeric values. The sum of every empty
	 * Collection is {@code 0};
	 * @param collection The Collection which values will be summed up.
	 * @return The sum as a {@code double} value.
	 */
	public static <T> double sum(Collection<T> collection) {
		double sum = 0.0;
		if(collection.isEmpty())
			return sum;
		for(T item: collection){
			if(item instanceof Integer)
				sum += ((Integer) item).doubleValue();
			else if(item instanceof Double)
				sum += ((Double) item).doubleValue();
			else if(item instanceof Float)
				sum += ((Float) item).doubleValue();
			else
				throw new ClassFormatError("Cannot sum values of class '"+item.getClass()+"'!");
		}
		return sum;
	}

	/**
	 * Get the maximum value of a Collection of numeric values and its position in the given Collection.<br><br>
	 * Examples:<br>
	 * Assume following collections:<br>
	 * {@code numbers = [5, 1, 42]}<br>
	 * {@code max(numbers)[0]} gives {@code 42}, {@code max(numbers)[1]} gives {@code 2}<br>
	 * {@code numbers = []}<br>
	 * {@code max(numbers)[0]} gives {@code NaN}, {@code max(numbers)[1]} gives {@code -1}<br>
	 * @param collection The Collection to get the maximum of.
	 * @return An Array of size 2 where the first position holds the found maximum value and the second position
	 * holds the position of this maximum value inside of the Collection.
	 */
	public static <T> double[] max(Collection<T> collection) {
		if(collection.isEmpty()){
			return new double[]{Double.NaN, -1};
		}
		double max = Double.MIN_VALUE;
		double i = 0;
		double index = -1;
		Iterator<T> it = collection.iterator();
		while(it.hasNext()){
			T item = it.next();
			if(item instanceof Integer){
				if((Integer)item > max){
					max = ((Integer) item).doubleValue();
					index = i;
				}
			} else if(item instanceof Double){
				if((Double)item > max){
					max = ((Double) item).doubleValue();
					index = i;
				}
			} else if(item instanceof Float){
				if((Float)item > max){
					max = ((Float) item).doubleValue();
					index = i;
				}
			} else
				throw new ClassFormatError("Cannot get the maximum of values of class '"+item.getClass()+"'!");
			i++;
		}
		return new double[]{max, index};
	}

	/**
	 * Sorts the given HashMap by its values.
	 * @param map The HashMap to sort.
	 * @param ascending Whether to sort the values in ascending or descending order.
	 * @return A sorted LinkedHashMap.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <KeyType, ValType> LinkedHashMap<KeyType, ValType> sortHashMapByValues(
			HashMap<KeyType, ValType> map, boolean ascending) {
		LinkedHashMap<KeyType, ValType> result = new LinkedHashMap<KeyType, ValType>();
		if(map.isEmpty())
			return result;
		ArrayList<ValType> values = new ArrayList<ValType>();
		for(ValType v: map.values()){
			values.add(v);
		}
		ValType elem = values.get(0);
		if(elem instanceof Comparable)
			Collections.sort((ArrayList<Comparable>) values);
		else
			throw new ClassFormatError("Cannot sort values of class '"+elem.getClass()+"'!");
		if(!ascending){
			Collections.reverse(values);
		}
		for(ValType val: values){
			Iterator<Entry<KeyType, ValType>> it = map.entrySet().iterator();
			while(it.hasNext()){
				Entry<KeyType, ValType> entry = it.next();
				if(entry.getValue() == val){
					result.put(entry.getKey(), entry.getValue());
					map.remove(entry.getKey());
					break;
				}
			}
		}
		return result;
	}
}
