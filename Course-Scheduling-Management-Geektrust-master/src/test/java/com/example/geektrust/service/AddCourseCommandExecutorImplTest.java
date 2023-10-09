package com.example.geektrust.service;

import com.teja.coursescheduling.exception.CourseFullException;
import com.teja.coursescheduling.exception.InvalidInputException;
import com.teja.coursescheduling.model.Command;
import com.teja.coursescheduling.model.Course;
import com.teja.coursescheduling.concrete.CommandExecutionFactory;
import com.teja.coursescheduling.service.CommandExecutor;
import com.teja.coursescheduling.service.CommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class AddCourseCommandExecutorImplTest {

    Command command1;
    CommandExecutor executor;
    private  TreeMap<String , Course> courses;
    private  Map<String,Course> registrationIdCourseMap;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws InvalidInputException {
        System.setOut(new PrintStream(outContent));
        command1 = CommandService.getInstance().getCommandFromString("ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2");
        executor = CommandExecutionFactory.getExecutor(command1);
        courses = new TreeMap<>();
        registrationIdCourseMap = new HashMap<>();
    }

    @Test
    public void testExecute(){
        assertDoesNotThrow( ()->executor.executeCommand(courses , registrationIdCourseMap , command1));
    }


    @Test
    public void testSuccessMessage() throws InvalidInputException, CourseFullException {
        executor.executeCommand(courses , registrationIdCourseMap , command1);
        assertEquals("OFFERING-JAVA-JAMES",outContent.toString().trim());
    }
}
