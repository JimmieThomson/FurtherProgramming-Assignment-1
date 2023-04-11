package classes;

/*
 *  Date Made: 11/4/2023
 *
 *  James Thomson
 *
 *  Description: Testing Methods in the class csvFileImporter
 *
 * */

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

public class csvFileImporterTest {

    private csvFileImporter csvImport;

    @Before
    public void setUp() {
        csvImport = new csvFileImporter();
    }
    @After
    public void tearDown() {
        csvImport = null;
    }

    //Testing to see if there is a valid output of variables in the HashMap
    //Basically if there is any in it and using that as a validation point
    @Test
    public void contentImportingCorrectlyTest() {
        assertNotNull(csvImport.getCsvContent());
    }
}