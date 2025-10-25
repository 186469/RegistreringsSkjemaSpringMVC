package no.hvl.dat108.oblig4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import no.hvl.dat108.oblig4.model.Deltager;

class DeltagerTest {

    @Test
    void passordMatcher_trueNarLike() {
        Deltager d = new Deltager("Ola", "Nordmann", "12345678", "pass", "mann");
        d.setRepetertPassord("pass");
        assertTrue(d.passordMatcher());
    }

    @Test
    void passordMatcher_falseNarUlike() {
        Deltager d = new Deltager("Ola", "Nordmann", "12345678", "pass", "mann");
        d.setRepetertPassord("p4ss");
        assertFalse(d.passordMatcher());
    }

    @Test
    void kjonn_settesKorrekt() {
        Deltager d = new Deltager("Eva", "Nordmann", "87654321", "x", "kvinne");
        d.setRepetertPassord("x");
        assertEquals("kvinne", d.getKjonn());
    }
}
