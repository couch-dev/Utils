package de.couchdev.utils;

public class Format {
	
	/**
	 * Transforms an Integer number to its formatted String representation.<br><br>
	 * Examples:<br>
	 * {@code int2str(5, 3)} returns {@code "005"}<br>
	 * {@code int2str(123, 2)} returns {@code "123"}<br>
	 * {@code int2str(2015, 2, true)} returns {@code "15"}<br>
	 * @param num The number to get the String for.
	 * @param digits The amount of digits of the number. (The minimum length of the String)
	 * @param cut {@code -optional-} Whether to cut the String at the beginning if it would extend the
	 * length of <b>digits</b>.
	 * @return
	 */
	public static String int2str(int num, int digits, boolean... cut){
		String format = "";
		if(digits>1){
			int length = (""+num).length();
			if(length<digits){
				for(int i=0; i< digits-length; i++){
					format += "0";
				}
				format += num;
			} else{
				if(cut != null && cut.length>0 && cut[0]){
					int dif = length - digits;
					format = (""+num).substring(dif);
				} else{
					format += num;
				}
			}
		} else{
			format += num;
		}
		return format;
	}
}
