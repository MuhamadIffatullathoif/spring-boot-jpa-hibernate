package com.iffat.springboot.jpa.repositories;

import com.iffat.springboot.jpa.dto.PersonDto;
import com.iffat.springboot.jpa.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    // query method from JpaRepository
    List<Person> findByNameBetween(String name1, String name2);
    List<Person> findByIdBetween(Long id1, Long id2);
    List<Person> findAllByOrderByNameDesc();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MIN(LENGTH(p.name) ) FROM Person p)")
    List<Object[]> getShortName();

    @Query("SELECT p FROM Person p WHERE p.id = (SELECT MAX(p.id) FROM Person p)")
    Optional<Person> getLastRegistration();

    @Query("SELECT MIN(p.id), MAX(p.id), SUM(p.id), AVG(LENGTH(p.name)), COUNT(p.id) FROM Person p")
    Object getResumeAggregationFunction();

    @Query("SELECT MIN(LENGTH(p.name)) FROM Person p")
    Integer getMinLengthName();

    @Query("SELECT MAX(LENGTH(p.name)) FROM Person p")
    Integer getMaxLengthName();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p")
    List<Object[]> getPersonNameLength();

    @Query("SELECT COUNT(p) FROM Person p")
    Long getTotalPerson();

    @Query("SELECT MIN(p.id) FROM Person p")
    Long getMinId();

    @Query("SELECT MAX(p.id) FROM Person p")
    Long getMaxId();

    @Query("SELECT p FROM Person p ORDER BY p.name, p.lastname DESC")
    List<Person> getAllOrdered();

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN 'E' AND 'J'")
    List<Person> findAllBetweenName();

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2")
    List<Person> findAllBetweenName(String name1, String name2);

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN 2 AND 3")
    List<Person> findAllBetweenId();

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN ?1 AND ?2")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("SELECT LOWER( CONCAT(p.name,' ',p.lastname)) FROM Person p")
    List<String> findAllFullNameConcatLower();

    @Query("SELECT UPPER(CONCAT(p.name,' ',p.lastname)) FROM Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("SELECT CONCAT(p.name, ' ', p.lastname) FROM Person p")
    List<String> findAllFullNameConcat();

    @Query("SELECT COUNT (DISTINCT (p.programmingLanguage)) FROM Person p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("SELECT DISTINCT (p.programmingLanguage) FROM Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("SELECT DISTINCT (p.name) FROM Person p")
    List<String> findAllNamesDistinct();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

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
