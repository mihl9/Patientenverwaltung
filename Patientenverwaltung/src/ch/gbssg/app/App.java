package ch.gbssg.app;

	
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.gbssg.app.ila.database.dao.UserJDBCTemplate;
import ch.gbssg.app.model.User;
import ch.gbssg.app.tla.AppController;
import ch.gbssg.core.pac.AgentFactory;

/**
 * entry point for application.
 * @author pedrett.sandro
 * @version 1.0
 */
public class App extends Application {
	private static ApplicationContext ctx;

	public static void main(String[] args) {
		/*ctx = new ClassPathXmlApplicationContext("Beans.xml");
		
		UserJDBCTemplate u = (UserJDBCTemplate)ctx.getBean("UserJDBCTemplate");
		
		List<User> users = u.get();*/
		
		launch(args);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		AppController appAgent = AgentFactory.getInstance().requestAgent(AppController.class);
		appAgent.showStartWindow(primaryStage);
	}
}