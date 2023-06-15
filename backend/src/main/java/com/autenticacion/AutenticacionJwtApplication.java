package com.autenticacion;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.autenticacion.models.QuartzSchedulerConfig;

@SpringBootApplication
public class AutenticacionJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticacionJwtApplication.class, args);
		try {
            QuartzSchedulerConfig.configureScheduler();
        } catch (SchedulerException e) {
            // Gestisci eventuali eccezioni durante la configurazione del job scheduler
            e.printStackTrace();
        }
	}

}
