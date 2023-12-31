package com.iffat.springboot.jpa;

import com.iffat.springboot.jpa.dto.PersonDto;
import com.iffat.springboot.jpa.entities.Person;
import com.iffat.springboot.jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        // create();
        update();
        // deleteByObject();
        // personalizedQueries2();
        // personalizedQueryDistinct();
        // personalizedQueryConcatUpperLowerCase();
        // personalizedQueriesBetween();
        // queriesAggregateFunction();
        // queriesSubQuery();
        // queriesWhereIN();
    }

    @Transactional(readOnly = true)
    public void queriesWhereIN() {
        System.out.println("WHERE IN");
        List<Person> persons = personRepository.getPersonByIds(Arrays.asList(1L,4L));
        persons.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void queriesSubQuery(){
        System.out.println("Short name");
        List<Object[]> persons = personRepository.getShortName();
        persons.forEach(person -> {
            String name = (String) person[0];
            Integer length = (Integer) person[1];
            System.out.println("Name: " + name + " " + "Length: " + length);
        });

        System.out.println("Last Registration");
        Optional<Person> optionalPerson = personRepository.getLastRegistration();
        optionalPerson.ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void queriesAggregateFunction() {
        System.out.println("Total person");
        Long totalPerson = personRepository.getTotalPerson();
        System.out.println(totalPerson);

        System.out.println("MIN ID of person");
        Long minID = personRepository.getMinId();
        System.out.println(minID);

        System.out.println("MAX ID of person");
        Long maxID = personRepository.getMaxId();
        System.out.println(maxID);

        System.out.println("LENGTH name of person");
        List<Object[]> persons = personRepository.getPersonNameLength();
        persons.forEach(person -> {
            String name = (String) person[0];
            Integer lengthName = (Integer) person[1];
            System.out.println("name: " + name + " " + "Length : " + lengthName);
        });

        System.out.println("MIN of Length person name");
        Integer minLengthPersonName = personRepository.getMinLengthName();
        System.out.println(minLengthPersonName);

        System.out.println("MAX of Length person name");
        Integer maxLengthPersonName = personRepository.getMaxLengthName();
        System.out.println(maxLengthPersonName);

        System.out.println("Aggregate Function: Min, Max, Sum, Avg, Count");
        Object[] aggregationFunction = (Object[]) personRepository.getResumeAggregationFunction();
        System.out.println("Min: " + aggregationFunction[0] + "Max: " + aggregationFunction[1] + "Sum: " + aggregationFunction[2] + "Avg: " + aggregationFunction[3] + "Count: " + aggregationFunction[4]);
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesBetween() {
        System.out.println("Between ID");
        // List<Person> persons = personRepository.findAllBetweenId();
        // List<Person> persons = personRepository.findAllBetweenId(2L,3L);
        List<Person> persons = personRepository.findByIdBetween(2L, 3L);
        persons.forEach(System.out::println);

        System.out.println("Between name");
        // persons = personRepository.findAllBetweenName();
        // persons = personRepository.findAllBetweenName("K","N");
        persons = personRepository.findByNameBetween("K", "N");
        persons.forEach(System.out::println);

        persons = personRepository.getAllOrdered();
        persons.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueryConcatUpperLowerCase() {
        System.out.println("Concat case");
        List<String> fullNames = personRepository.findAllNames();
        fullNames.forEach(System.out::println);

        System.out.println("Upper case");
        fullNames = personRepository.findAllFullNameConcatUpper();
        fullNames.forEach(System.out::println);

        System.out.println("Lower case");
        fullNames = personRepository.findAllFullNameConcatLower();
        fullNames.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueryDistinct() {
        System.out.println("== Take all names in table Person ==");
        List<String> names = personRepository.findAllNames();
        names.forEach(System.out::println);

        System.out.println("Use distinct to remove duplicate name");
        names = personRepository.findAllNamesDistinct();
        names.forEach(System.out::println);

        System.out.println("Use distinct to remove duplicate programming language");
        List<String> programmingLanguageDistinct = personRepository.findAllProgrammingLanguageDistinct();
        programmingLanguageDistinct.forEach(System.out::println);

        System.out.println("Use count and distinct to check total and remove duplicate");
        Long countProgrammingLanguage = personRepository.findAllProgrammingLanguageDistinctCount();
        System.out.println("Total: " + countProgrammingLanguage);
    }

    @Transactional
    public void personalizedQueries2() {
        List<Object[]> allMixPerson = personRepository.findAllMixPerson();
        allMixPerson.forEach(person -> {
            System.out.println("Programming Language: " + person[1] + " person: " + person[0]);
        });

        List<Person> persons = personRepository.findAllObjectPersonPersonalized();
        persons.forEach(System.out::println);

        List<PersonDto> personDtos = personRepository.findAllPersonDto();
        personDtos.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter ID person: ");
        long id = scanner.nextLong();

        scanner.close();

        String name = personRepository.getNameById(id);
        System.out.println(name);

        String idDB = personRepository.getIdById(id);
        System.out.println(idDB);

        String fullName = personRepository.getFullNameById(id);
        System.out.println(fullName);

        Optional<Object> optionalObject = personRepository.getPersonFullDataById(id);
        if (optionalObject.isPresent()) {
            Object[] fullData = (Object[]) optionalObject.get();
            System.out.println("ID: " + fullData[0] + " name: " + fullData[1] + " lastname: " + fullData[2] + " programming language: " + fullData[3]);
        }

        List<Object[]> fullDatas = personRepository.getPersonFullData();
        fullDatas.forEach(fullData -> System.out.println("ID: " + fullData[0] + " name: " + fullData[1] + " lastname: " + fullData[2] + " programming language: " + fullData[3]));
    }

    @Transactional
    public void deleteByObject() {
        personRepository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id person to delete : ");
        long id = scanner.nextLong();

        Optional<Person> optionalPerson = personRepository.findById(id);
        optionalPerson.ifPresentOrElse(personRepository::delete, () -> System.out.println("ID person " + id + "not found"));

        personRepository.findAll().forEach(System.out::println);
    }

    @Transactional
    public void deleteOne() {
        personRepository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id person to delete : ");
        long id = scanner.nextLong();

        personRepository.deleteById(id);

        personRepository.findAll().forEach(System.out::println);
        scanner.close();

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
        scanner.close();
    }

    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id to search person want update: ");
        long id = scanner.nextLong();
        Optional<Person> optionalPerson = personRepository.findOne(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            System.out.println("Enter your programming language: ");
            String programmingLanguage = scanner.next();
            person.setProgrammingLanguage(programmingLanguage);
            Person personObject = personRepository.save(person);
            System.out.println(personObject);
        } else {
            System.out.println("Person no exist by id" + id);
        }
        scanner.close();
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
