/**
 * HR
 * 
 * @author 	Hugo Navarro, http://hnavarro-sv.blogspot.com
 * @version 1.0
 * @date	30/11/2011	
 * 
 */
package sv.hnavarro.examples.hr.common.jaxb2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeAdapter {
	private static final DateFormat formatter;

	static {
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	}

	public static Date parseDate(String date) {
		try {
			return formatter.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String printDate(Date date) {
		return formatter.format(date);
	}
	
}