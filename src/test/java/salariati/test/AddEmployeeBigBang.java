package salariati.test;

import org.junit.Before;
import org.junit.Test;
import salariati.controller.implementations.EmployeeControllerImpl;
import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;
import salariati.model.Employee;
import salariati.repository.implementations.EmployeeRepositoryImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeRepositoryMock;
import salariati.validator.EmployeeValidator;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddEmployeeBigBang {
    @Test
    public void unitTestValidator() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee newEmployee = new Employee("Vasile", "ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
        assertTrue(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void unitTestRepo() throws EmployeeException, IOException {
        File tempFile = File.createTempFile("employee", "txt");

        EmployeeRepositoryInterface repo = new EmployeeRepositoryImpl(tempFile.getAbsolutePath());
        Employee validEmpl = new Employee("gelu", "george", "1951207060028", DidacticFunction.ASISTENT, "1200");
        assertTrue(repo.addEmployee(validEmpl));

        tempFile.delete();
    }

    @Test
    public void unitTestController() throws EmployeeException, IOException {
        EmployeeRepositoryMock employeeRepository = new EmployeeRepositoryMock();
        EmployeeControllerImpl controller = new EmployeeControllerImpl(employeeRepository);

        Employee newEmployee = new Employee("Vasile", "ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
        controller.addEmployee(newEmployee);
        assertEquals(7, controller.getEmployeesList().size());
    }

    @Test
    public void IntegrationTest() throws EmployeeException, IOException {
        File tempFile = File.createTempFile("employee", "txt");

        EmployeeRepositoryInterface repo = new EmployeeRepositoryImpl(tempFile.getAbsolutePath());
        EmployeeControllerImpl controller = new EmployeeControllerImpl(repo);

        Employee newEmployee = new Employee("Vasile", "ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
        controller.addEmployee(newEmployee);
        assertEquals(1, controller.getEmployeesList().size());

        tempFile.delete();
    }
}