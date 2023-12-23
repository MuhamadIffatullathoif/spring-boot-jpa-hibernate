package com.iffat.springboot.jpa;

import com.iffat.springboot.jpa.entities.Person;
import com.iffat.springboot.jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootJpaHibernateApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // List<Person> persons = personRepository.findAll();
        // List<Person> persons = personRepository.findByProgrammingLanguage("Javascript");
        // List<Person> persons = personRepository.searchByProgrammingLanguage("Javascript");
        // List<Person> persons = personRepository.findByProgrammingLanguageAndName("Javascript", "Faqih");
        List<Person> persons = personRepository.searchByProgrammingLanguageAndName("Javascript", "Faqih");
        persons.stream().forEach(System.out::println);

        List<Object[]> personValues = personRepository.getPersonData("Muhammad");
        personValues.stream().forEach(person -> {
            System.out.println(person[0] + " expert on " + person[1]);
        });
    }
}
