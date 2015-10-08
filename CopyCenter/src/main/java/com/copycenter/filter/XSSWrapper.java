package com.copycenter.filter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSWrapper extends HttpServletRequestWrapper {
	public XSSWrapper(HttpServletRequest request) {
		super(request);
	}

	private String stripXSS(String input) {
		if (input != null) {
			input = input.replaceAll("", "");

			Pattern scriptPattern;

			scriptPattern = Pattern.compile("<script>(.*?)</script>",
					Pattern.CASE_INSENSITIVE);
			input = scriptPattern.matcher(input).replaceAll("");

			scriptPattern = Pattern.compile("<link> (.*?)</link>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			input = scriptPattern.matcher(input).replaceAll("");

			scriptPattern = Pattern.compile("<div>(.*?)</div>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			input = scriptPattern.matcher(input).replaceAll("");

			scriptPattern = Pattern.compile("<a>(.*?)</a>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			input = scriptPattern.matcher(input).replaceAll("");

			scriptPattern = Pattern.compile("<style>(.*?)</style>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			input = scriptPattern.matcher(input).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			input = scriptPattern.matcher(input).replaceAll("");
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			input = scriptPattern.matcher(input).replaceAll("");

			scriptPattern = Pattern.compile("<%(.*?)%>",
					Pattern.CASE_INSENSITIVE);
			input = scriptPattern.matcher(input).replaceAll("");

			scriptPattern = Pattern.compile("//$");
			input = scriptPattern.matcher(input).replaceAll("");
			

		}
		return input;
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		String[] encodedValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			encodedValues[i] = stripXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		return stripXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return stripXSS(value);
	}
}
