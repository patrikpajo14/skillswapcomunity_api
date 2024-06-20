package com.skillswapcomunity.skillswapcomunity.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String GET_PERSONS_LIST_JOB_IDENTITY = "getPersonsListJob";
    private static final String GET_PERSONS_LIST_TRIGGER = "getPersonsListTrigger";

    @Bean
    public JobDetail getPersonsListJobDetail() {
        return JobBuilder.newJob(GetPersonsListJob.class).withIdentity(GET_PERSONS_LIST_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger getPersonsListTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(getPersonsListJobDetail())
                .withIdentity(GET_PERSONS_LIST_TRIGGER).withSchedule(scheduleBuilder).build();
    }
}
