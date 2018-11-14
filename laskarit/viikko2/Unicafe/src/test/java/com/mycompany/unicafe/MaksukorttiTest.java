package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(2);
        assertEquals("saldo: 0.12", kortti.toString());
    }
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTapreepksi() {
        kortti.otaRahaa(4);
        assertEquals("saldo: 0.06", kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(12);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void metodiPalauttaaFalseJosRahatEivatRiitta() {
        assertFalse(kortti.otaRahaa(12));
    }
    @Test
    public void metodiPalauttaaTrueJosRahatRiittivat() {
        assertTrue(kortti.otaRahaa(5));
    }
    @Test 
    public void metodiPalauttaaSaldonOikein() {
        assertEquals("10", (kortti.saldo()+""));
    }
}
