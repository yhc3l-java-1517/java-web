package se.coredev.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public final class ApplicationInitializer implements ServletContextListener {

	public static final String DATASOURCE_KEY = "Datasource-Key";
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Initialized");
		DataSource datasource = null;
		event.getServletContext().setAttribute(DATASOURCE_KEY, datasource);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Destroyed");  
		DataSource datasource = (DataSource)event.getServletContext().getAttribute(DATASOURCE_KEY);		
	}

}
