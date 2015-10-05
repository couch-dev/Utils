package de.couchdev.utils;

public class Format {
	
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
