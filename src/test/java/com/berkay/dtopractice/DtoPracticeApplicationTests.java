package com.berkay.dtopractice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class DtoPracticeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void toplam()
    {
        int sonuc =2+3;
        assertEquals(5, sonuc);
    }
     @Test
     void assertTrueTesti() {
         int yas = 20;

         assertTrue(yas >= 18);
     }
    @Test
    void assertFalseTesti() {
        boolean girisBasarili = false;

        assertFalse(girisBasarili);
    }
    @Test
    void assertNullTesti() {
        String username = null;

        assertNull(username);
    }
    @Test
    void assertNotNullTesti() {
        String username = "berkay";

        assertNotNull(username);
    }
    @Test
    void sifiraBolmeTesti() {
        assertThrows(ArithmeticException.class, () -> {
            int sonuc = 10 / 0;
        });
    }
    @Test
    void assertNotEqualsTesti() {
        int beklenenOlmayanDeger = 10;
        int gercekDeger = 5;

        assertNotEquals(beklenenOlmayanDeger, gercekDeger);
    }
    @Test
    void kullaniciBilgileriTesti() {
        String username = "berkay";
        int yas = 20;

        assertAll(
                () -> assertEquals("berkay", username),
                () -> assertNotNull(username),
                () -> assertTrue(yas >= 18)
        );
    }
}
