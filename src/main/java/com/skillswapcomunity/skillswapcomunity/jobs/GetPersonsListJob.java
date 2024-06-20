package com.skillswapcomunity.skillswapcomunity.jobs;

import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.repository.PersonRepository;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class GetPersonsListJob extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(GetPersonsListJob.class);

    private final PersonRepository personRepository;

    public GetPersonsListJob(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        final List<Person> personList = personRepository.findAll();

        if(personList.stream().anyMatch(this::isPersonExists)){
            log.info("List of current Users:");
            personList.stream()
                    .filter(this::isPersonExists)
                    .forEach(
                            person -> log.info(person.getName() + " (" + person.getEmail() + ")")
                    );
            log.info("---------------------------------");
        } else {
            log.info("There is no Users in list!");
        }

    }

    private boolean isPersonExists(Person person) {
        return person.getId() > 0;
    }
}
