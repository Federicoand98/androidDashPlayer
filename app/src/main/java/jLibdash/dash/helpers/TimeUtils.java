package jLibdash.dash.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
	
	public static String getCurrentUTCTimeString() {
		SimpleDateFormat dateFormatUTC = new SimpleDateFormat("Y-m-dd'T'H:M:SZ");
		dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		return dateFormatUTC.format(new Date());
	}

}
