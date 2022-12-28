import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CFTest {

    @Test
    void generalCF(){

        assertEquals(new CF(5,17).toString(), "[0, 3, 2, 2]" );
        assertEquals(new CF(7,13).toString(), "[0, 1, 1, 6]" );
        assertEquals(new CF(184,221).toString(), "[0, 1, 4, 1, 36]" );
        assertEquals(new CF(43,19).toString(), "[2, 3, 1, 4]" );
    }

}