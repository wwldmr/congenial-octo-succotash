package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindById_thenReturnEmployee() {
        // given
        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");
        employee.setEmail("123@qwe.com");
        entityManager.persist(employee);
        entityManager.flush();

        // when
        Optional<Employee> found = employeeRepository.findById(employee.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo(employee.getFirstName());
    }

    @Test
    public void whenFindByFirstName_thenReturnEmployee() {
        // given
        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");
        employee.setEmail("123@qwe.com");
        entityManager.persist(employee);
        entityManager.flush();

        // when
        Optional<Employee> found = employeeRepository.findByFirstName(employee.getFirstName());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo(employee.getFirstName());
    }

    @Test
    public void whenFindByNonExistentFirstName_thenReturnEmpty() {
        // when
        Optional<Employee> found = employeeRepository.findByFirstName("NonExistent");

        // then
        assertThat(found).isEmpty();
    }

    @Test
    public void whenSaveEmployee_thenCanBeFoundById() {
        // given
        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");
        employee.setEmail("123@qwe.com");

        // when
        Employee saved = employeeRepository.save(employee);
        Optional<Employee> found = employeeRepository.findById(saved.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    public void whenSaveEmployeeWithDuplicateEmail_thenThrowException() {
        // given
        Employee employee1 = new Employee();
        employee1.setFirstName("Ivan");
        employee1.setLastName("Ivanov");
        employee1.setEmail("123@qwe.com");
        entityManager.persist(employee1);
        entityManager.flush();

        Employee employee2 = new Employee();
        employee2.setFirstName("AnotherIvan");
        employee2.setLastName("AnotherIvanov");
        employee2.setEmail("123@qwe.com");

        // when/then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            employeeRepository.saveAndFlush(employee2);
        });
    }
}