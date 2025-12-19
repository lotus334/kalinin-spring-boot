package com.springJdbcTemplate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

class PersonRepositoryTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        // Создадим базу данных для тестирования
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts() // Добавляем скрипты schema.sql и data.sql
                .setType(EmbeddedDatabaseType.H2) // Используем базу H2
                .build();

        // Создадим JdbcTemplate
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);

        // Создадим PersonRepository
        personRepository = new PersonRepositoryImpl(jdbcTemplate);
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void testFindAll() {
        Assertions.assertNotNull(personRepository.findAll());
        Assertions.assertEquals(2, personRepository.findAll().size());
    }

    @Test
    public void testFindOne() {
        Assertions.assertNotNull(personRepository.findOne("jack-daniels"));
        Assertions.assertNull(personRepository.findOne("nonexistent-id"));
    }

    @Test
    public void testSave() {
        Person person = personRepository.save(new Person("Jim Beam", "jimbeam@example.com"));

        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.getId());
        Assertions.assertEquals("Jim Beam", person.getName());
    }

    @Test
    public void testSaveInvalid() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> personRepository.save(new Person()));
    }

    @Test
    public void testSaveConflict() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> personRepository.save(new Person("Jim Beam", "jackdaniels@example.com")));
    }

    @Test
    public void testUpdate() {
        Person person = jdbcTemplate.queryForObject("select * from person where id = 'jack-daniels'", PersonRepository.ROW_MAPPER);
        person.setName("Johny Walker");

        person = personRepository.save(person);
        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.getId());
        Assertions.assertEquals("Johny Walker", person.getName());
    }

    @Test
    public void testUpdateInvalid() {
        Person person = jdbcTemplate.queryForObject("select * from person where id = 'jack-daniels'", PersonRepository.ROW_MAPPER);
        person.setName(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> personRepository.save(person));
    }

    @Test
    public void testUpdateConflict() {
        Person person = jdbcTemplate.queryForObject("select * from person where id = 'jack-daniels'", PersonRepository.ROW_MAPPER);
        person.setEmail("georgedickel@example.com");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> personRepository.save(person));
    }

    @Test
    public void testDelete() {
        Assertions.assertEquals(1, personRepository.delete("jack-daniels"));
        Assertions.assertEquals(0, personRepository.delete("nonexistent-id"));
    }
}