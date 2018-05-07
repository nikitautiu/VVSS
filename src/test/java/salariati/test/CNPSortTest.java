package salariati.test;

import org.junit.Before;
import org.junit.Test;
import salariati.controller.implementations.EmployeeControllerImpl;
import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeRepositoryMock;
import salariati.validator.EmployeeValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CNPSortTest {

    private EmployeeRepositoryInterface employeeRepository;
    private EmployeeControllerImpl controller;
    private EmployeeValidator employeeValidator;

    @Before
    public void setUp() {
        employeeRepository = new EmployeeRepositoryMock();
        controller         = new EmployeeControllerImpl(employeeRepository);
        employeeValidator  = new EmployeeValidator();
    }

    @Test
    public void testRepositoryMock() {
        assertFalse(controller.getEmployeesList().isEmpty());
        assertEquals(6, controller.getEmployeesList().size());
    }

    @Test
    public void testAddNewEmployee() throws EmployeeException {
        Employee newEmployee = new Employee("Vasile", "ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
        assertTrue(employeeValidator.isValid(newEmployee));
        controller.addEmployee(newEmployee);
        assertEquals(7, controller.getEmployeesList().size());
        assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }

}