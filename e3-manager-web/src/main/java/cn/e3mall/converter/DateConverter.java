package cn.e3mall.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	public Date convert(String source) {
//		2015-02:09 13:22_57
		    Long time = Long.valueOf(source);
		    Date date=new Date(time);
			
			try {
				return date;
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			return null;
		}

	
	
}
