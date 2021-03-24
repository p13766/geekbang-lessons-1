package org.geektimes.configuration.microprofile.config.source.servlet;

import org.geektimes.configuration.microprofile.config.source.MapBasedConfigSource;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.Map;

import static java.lang.String.format;

public class ServletContextConfigSource extends MapBasedConfigSource {

    private static ServletContext servletContext;

    public ServletContextConfigSource() {
        super(format("ServletContext[path:%s] Init Parameters", "ServletContextConfigSource"), 500);

    }

    public static ServletContextConfigSource getInstance(ServletContext servletContext){
        ServletContextConfigSource.servletContext = servletContext;
        return new ServletContextConfigSource();
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            configData.put(parameterName, servletContext.getInitParameter(parameterName));
        }
    }
}
