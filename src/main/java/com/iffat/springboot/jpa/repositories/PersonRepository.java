package com.iffat.springboot.jpa.repositories;

import com.iffat.springboot.jpa.dto.PersonDto;
import com.iffat.springboot.jpa.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT new com.iffat.springboot.jpa.dto.PersonDto(p.name, p.lastname) FROM Person p")
    List<PersonDto> findAllPersonDto();

    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixPerson();

    @Query("SELECT new Person(p.name, p.programmingLanguage)FROM Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p")
    List<Object[]> getPersonFullData();

    @Query("SELECT p.id,p.name,p.lastname,p.programmingLanguage FROM Person p WHERE p.id = ?1")
    Optional<Object> getPersonFullDataById(Long id);

    @Query("SELECT p.name FROM Person p WHERE p.id = ?1")
    String getNameById(Long id);

    @Query("SELECT p.id FROM Person p WHERE p.id = ?1")
    String getIdById(Long id);

    @Query("SELECT CONCAt(p.name,' ', p.lastname) as fullname FROM Person p WHERE p.id = ?1")
    String getFullNameById(Long id);

    @Query("SELECT p FROM Person p WHERE p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("SELECT p FROM Person p WHERE p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    Optional<Person> findOneLikeName(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1")
    List<Person> searchByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Person> searchByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> getPersonData();

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name = ?1")
    List<Object[]> getPersonData(String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Object[]> getPersonData(String programmingLanguage, String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1")
    List<Object[]> getPersonDataByProgrammingLanguage(String programmingLanguage);
}
