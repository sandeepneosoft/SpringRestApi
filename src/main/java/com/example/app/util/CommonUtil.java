package com.example.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	public String convertFormatDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		Date formattedDate = format.parse(date);
		java.sql.Date dateDB = new java.sql.Date(formattedDate.getTime());
		return dateDB.toString();
	}
}
