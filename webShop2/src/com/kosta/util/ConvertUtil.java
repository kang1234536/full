package com.kosta.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConvertUtil {
	//문자를 함수로만드는 함수
		public static int convertInt(String param) {
			if(param == null) return 0;
			return Integer.parseInt(param);
			
		}
		public static double convertDouble(String param) {
		
			return Double.parseDouble(param);
			
		}
		public static Date convertDate(String param) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date d = null;
			try {
				d = new Date(sdf.parse(param).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return d;
		
		}
}
