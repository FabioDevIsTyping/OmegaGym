package com.autenticacion.models;

import org.quartz.*;
import com.autenticacion.services.CardExpirationJob;
import static org.quartz.CronScheduleBuilder.*;

/**
 * Configuration class for Quartz Scheduler.
 */
public class QuartzSchedulerConfig {

    /**
     * Configures the scheduler to execute the CardExpirationJob daily at 00:00.
     *
     * @throws SchedulerException if there is an error configuring the scheduler.
     */
    public static void configureScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        String jobName = "cardExpirationJob_" + System.currentTimeMillis(); // Make the job name unique

        JobDetail job = JobBuilder.newJob(CardExpirationJob.class)
                .withIdentity(jobName, "group1") // Use the modified identifier
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("cardExpirationTrigger", "group1")
                .startNow()
                .withSchedule(dailyAtHourAndMinute(0, 0)) // Execute the job daily at 00:00
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
