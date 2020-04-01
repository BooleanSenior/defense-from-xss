package com.example.defensefromxss.util.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapperNew extends HttpServletRequestWrapper {  
  
    public XssHttpServletRequestWrapperNew(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
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
        String[] values = super.getParameterValues(name);  
        if (values == null) {  
            return null;  
        }  
        String[] newValues = new String[values.length];  
  
        for (int i = 0; i < values.length; i++) {  
            newValues[i] = clearXss(values[i]);  
        }  
  
        return newValues;  
    }  
  
    /** 
     * 处理字符转义 
     *  
     * @param value 
     * @return 
     */  
    private String clearXss(String value) {  
        if (value == null || "".equals(value)) {  
            return value;  
        }  
        value = value.replaceAll("<", "<").replaceAll(">", ">");  
        value = value.replaceAll("\\(", "(").replace("\\)", ")");  
        value = value.replaceAll("'", "'");  
        value = value.replaceAll("eval\\((.*)\\)", "");  
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",  
                "\"\"");  
        value = value.replace("script", "");
        System.out.println("---------------我在进行过滤----------------------");
        value = value.replace("%", "");
        return value;  
    }  
  
} 