package com.autenticacion.models;


import org.quartz.*;
import com.autenticacion.services.CardExpirationJob;
import static org.quartz.CronScheduleBuilder.*;

public class QuartzSchedulerConfig {

    public static void configureScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        String jobName = "cardExpirationJob_" + System.currentTimeMillis(); // rendo univoco il nome del job

        JobDetail job = JobBuilder.newJob(CardExpirationJob.class)
                .withIdentity(jobName, "group1") // Utilizza l'identificatore modificato
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("cardExpirationTrigger", "group1")
                .startNow()
                .withSchedule(dailyAtHourAndMinute(0, 0)) // Esegue il job ogni giorno alle 00:00
                .build();

        boolean triggerExists = scheduler.checkExists(trigger.getKey());

        if (!triggerExists) {
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } else {
            String newTriggerName = "cardExpirationTrigger_" + System.currentTimeMillis();
            Trigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(newTriggerName, "group1")
                    .startNow()
                    .withSchedule(dailyAtHourAndMinute(0, 0))
                    .build();

            scheduler.start();
            scheduler.scheduleJob(job, newTrigger);
        }
    }
}
