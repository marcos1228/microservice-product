package com.product.config.jackson;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONStringUtil {
	private JSONStringUtil() {
	}

	private static final com.fasterxml.jackson.databind.ObjectMapper MAPPER = new com.fasterxml.jackson.databind.ObjectMapper();

	public static final String jsonToStringConvert(Object object) {
		try {
			return json().writeValueAsString(object);
		} catch (Exception e) {
			log.info("ERROR DEFAULT");
			 e.getMessage();
			 e.getCause();
			return null;
		}
	}

	public static final <T> T jsonToObjectConvert(String jsonContent, Class<T> clazz) {
		T obj;
		try {
			obj = MAPPER.readValue(jsonContent, clazz);
			return obj;
		} catch (Exception e) {
			log.info("ERROR DEFAULT");
			 e.getMessage();
			 e.getCause();
		}
		return null;
	}

	public static com.fasterxml.jackson.databind.ObjectMapper json() {
		return MAPPER;
	}

}
