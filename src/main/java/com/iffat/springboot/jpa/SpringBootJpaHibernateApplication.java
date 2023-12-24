package com.iffat.springboot.jpa;

import com.iffat.springboot.jpa.entities.Person;
import com.iffat.springboot.jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootJpaHibernateApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // list();
        // findOne();
        create();
    }

    @Transactional
    public void create() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter lastname: ");
        String lastname = scanner.next();
        System.out.println("Enter programming language: ");
        String programmingLanguage = scanner.next();

        Person person = new Person(null, name, lastname, programmingLanguage);
        Person personResponse = personRepository.save(person);
        personRepository.findOne(personResponse.getId()).ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void findOne() {
//        Person person = null;
//        Optional<Person> optionalPerson = personRepository.findById(1L);
//        if(optionalPerson.isPresent()) {
//            person = optionalPerson.get();
//        }
//        System.out.println(person);

        /* More simple */
        // personRepository.findById(1L).ifPresent(System.out::println);
        personRepository.findOne(1L).ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void list() {
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
