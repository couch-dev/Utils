package de.couchdev.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;


public class Utilities {

	public static final double e = 2.71828182846;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <KeyType, ValType> LinkedHashMap<KeyType, ValType> sortHashMapByValues(
			HashMap<KeyType, ValType> map, boolean ascending) {
		LinkedHashMap<KeyType, ValType> result = new LinkedHashMap<KeyType, ValType>();
		if(map.isEmpty())
			return result;
		ArrayList<ValType> values = new ArrayList<ValType>();
		LinkedHashMap<ValType, KeyType> reverseMap = new LinkedHashMap<ValType, KeyType>();
		Iterator<Entry<KeyType, ValType>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<KeyType, ValType> entry = it.next();
			reverseMap.put(entry.getValue(), entry.getKey());
			values.add(entry.getValue());
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
			result.put(reverseMap.get(val), val);
		}
		return result;
	}

}
