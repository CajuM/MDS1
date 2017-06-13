package ro.unibuc.fmi.my.mds1;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	private static SessionFactory sessionFactory;

	static Session getSession() {
		return Main.sessionFactory.openSession();
	}

	public static void main(String[] args) throws Exception {
		Main.APP_HOME = Paths.get(
			Main.class
			.getProtectionDomain()
			.getCodeSource()
			.getLocation()
			.toString()
		).getParent().getParent();

		Properties props = System.getProperties();
		props.setProperty("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));

		final StandardServiceRegistry registry =
			new StandardServiceRegistryBuilder()
                	.configure()
		        .build();

        	Main.sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

		SpringApplication.run(Main.class, args);
	}
}
