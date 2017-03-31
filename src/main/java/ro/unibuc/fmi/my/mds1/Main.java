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
	public static Path APP_HOME;
	public static Path DB_PATH;

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

		Main.DB_PATH = Main.APP_HOME
			.resolve("data")
			.resolve("dex");

		Properties props = System.getProperties();
		String jdbcUrl = String.format("jdbc:h2:%s", Main.DB_PATH.toString());
		props.setProperty("hibernate.connection.url", jdbcUrl);

		final StandardServiceRegistry registry =
			new StandardServiceRegistryBuilder()
                	.configure()
		        .build();

        	Main.sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

		SpringApplication.run(Main.class, args);
	}
}
