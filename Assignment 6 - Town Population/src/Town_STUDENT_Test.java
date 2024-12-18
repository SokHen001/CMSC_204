import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;

public class Town_STUDENT_Test {
    
    private Town townA;
    private Town townB;
    private Town townC;
    
    @Before
    public void setUp() {
        townA = new Town("Bethesda");
        townB = new Town("Silver Spring");
        townC = new Town("Rockville");
    }
    
    @After
	public void tearDown() throws Exception {
		townA = townB = townC = null;
	}

    @Test
    public void testConstructorAndGetName() {
        Town town = new Town("Chevy Chase");
        assertEquals("Chevy Chase", town.getName());
    }

    @Test
    public void testCopyConstructor() {
        Town copiedTown = new Town(townA);
        assertEquals(townA.getName(), copiedTown.getName());
        assertNotSame(townA, copiedTown);  
    }

    @Test
    public void testCompareTo() {
        Town townA = new Town("Bethesda");
        Town townB = new Town("Silver Spring");
        Town townC = new Town("Bethesda");

        int result1 = townA.compareTo(townB);
        assertTrue(result1 < 0); 

        int result2 = townA.compareTo(townC);
        assertEquals(0, result2); 

        int result3 = townB.compareTo(townA);
        assertTrue(result3 > 0); 
    }
    
    @Test
    public void testToString() {
        assertEquals("Bethesda", townA.toString());
        assertEquals("Silver Spring", townB.toString());
        assertEquals("Rockville", townC.toString());
    }

    @Test
    public void testHashCode() {
        int hashA = townA.hashCode();
        int hashB = townB.hashCode();
        int hashC = townC.hashCode();

        assertNotEquals(hashA, hashB);
        assertNotEquals(hashB, hashC);
        assertNotEquals(hashA, hashC);
        
        Town sameTown = new Town("Bethesda");
        assertEquals(townA.hashCode(), sameTown.hashCode());
    }

    @Test
    public void testEquals() {
        Town townAClone = new Town("Bethesda");
        
        assertTrue(townA.equals(townAClone));
        assertFalse(townA.equals(townB));
        assertTrue(townA.equals(townA));
        assertFalse(townA.equals(null));
    }
}
