package jLibdash.dash.helpers;

import java.util.Vector;

public class VectorUtils {
	
	public static <T> Vector<T> toVector(T[] array) {
		Vector<T> vector = new Vector<T>();
		
		for(T element : array)
			vector.add(element);
		
		return vector;
	}
	
	public static Vector<Integer> toIntegerVector(Vector<String> stringVector) throws NumberFormatException {
		Vector<Integer> result = new Vector<Integer>();
		
		for(String s : stringVector)
			result.add(Integer.parseInt(s));
		
		return result;		
	}

}
