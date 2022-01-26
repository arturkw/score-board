package org.ahk.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyEqualsAndHashCodeTest {

    @Test
    public void symmetric_keys_are_equal_test() {
        Key k1 = new Key("a", "b");
        Key k2 = new Key("b", "a");

        assertEquals(k1.hashCode(), k2.hashCode());
        assertEquals(k1, k2);
    }

    @Test
    public void identity_keys_are_equal() {
        Key k1 = new Key("a", "b");
        Key k2 = new Key("a", "b");

        assertEquals(k1.hashCode(), k2.hashCode());
        assertEquals(k1, k2);
    }

    @Test
    public void diff_away_team_name_for_keys_test() {
        Key k1 = new Key("a", "ba");
        Key k2 = new Key("a", "a");

        assertNotEquals(k1.hashCode(), k2.hashCode());
        assertNotEquals(k1, k2);
    }

    @Test
    public void diff_home_team_name_test() {
        Key k1 = new Key("a", "a");
        Key k2 = new Key("ba", "a");

        assertNotEquals(k1.hashCode(), k2.hashCode());
        assertNotEquals(k1, k2);
    }
}
