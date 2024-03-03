package at.ac.fhcampuswien.fhmdb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    //for testing JUnit
    @Test
    void testaddone(){
        //given
        HomeController homeController = new HomeController();
        int n = 3;
        //when
        int actual = homeController.addOne(n);
        //then
        int expected = 4;
        assertEquals(expected,actual);
    }
}