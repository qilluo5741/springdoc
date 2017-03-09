package com.spring.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		return clearXss(super.getParameter(name));
	}

	@Override
	public String getHeader(String name) {
		return clearXss(super.getHeader(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		// �õ�ȫ��ֵ
		String[] values = super.getParameterValues(name);
		// ���³�ʼ��һ��ֵ
		String[] newValues=null;
		try {
			newValues = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				// �����滻��ֵ
				newValues[i] = clearXss(values[i]);
			}
		} catch (Exception e) {
			System.out.println("��������������");
		}
		return super.getParameterValues(name);
	}

	/***
	 * //����һ����������ת��
	 * 
	 * @param value
	 * @return
	 */
	private String clearXss(String value) {
		if (value == null || "".equals(value)) {
			return value;
		}
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("script", "");
		return value;
	}
}
