package com.nelioalves.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
}
