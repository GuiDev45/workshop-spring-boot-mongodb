package com.nelioalves.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	//Esse decode serve para os "espaços em branco".
	//Retorna a String decodificada ou caso ocorra algum erro na decodificação apenas retorna uma String vazia.
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	//Esse método vai tratar as datas recebidas, caso algum erro ocorra o default vai deixar o horário GMT.
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
		return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}
	}
}
