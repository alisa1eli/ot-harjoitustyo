/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alisaelizarova
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        this.kassa = new Kassapaate();
        this.kortti = new Maksukortti(1000);
    }
    
    @Test
    public void luodunKassapäätteenRahamääräOnOikea() {
        assertTrue(100000 == kassa.kassassaRahaa());
    }
    @Test 
    public void luodunKassapaatteenMyytyjenLounaidenMaaraOnOikea() {
        assertTrue(0 == kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    // käteisosto toimii edullisten lounaiden osalta 
    @ Test
    public void maksuOnRiittavaKassassaOlevaRahamaaraKasvaaLuonaanHinnalla() {
        assertTrue(0 == kassa.syoEdullisesti(240) && kassa.kassassaRahaa() == 100240); 
    }
    @Test
    public void kunMaksuOnRiittavaVaihtorahaOnOikeinLaskettu() {
        assertTrue(10 == kassa.syoEdullisesti(250));
    }
    @Test 
    public void kunMaksuOnRiittavaMyytyjenLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(240);
        assertTrue(1 == kassa.edullisiaLounaitaMyyty());
    }
    @Test 
    public void josMaksuEiOleRiittäväKassassaOlevaRahamääräEiMuutu () {
        kassa.syoEdullisesti(200);
        assertTrue(kassa.kassassaRahaa() == 100000); 
    }
    @Test 
    public void josMaksuEiOleRiittäväKaikkiRahatPalautetaaVaihtorahana() {
        assertTrue(200 == kassa.syoEdullisesti(200));
    }
    @Test
    public void josMaksuEiOleRiittäväMyytyjenLounaidenMäärässäEiMuutosta() {
        kassa.syoEdullisesti(200);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    // käteisosto toimii maukkaiden lounaiden osalta
    @ Test
    public void maksuOnRiittavaKassassaOlevaRahamaaraKasvaaMaukkaanLuonaanHinnalla() {
        assertTrue(0 == kassa.syoMaukkaasti(400) && kassa.kassassaRahaa() == 100400); 
    }
    @Test
    public void kunMaksuOnRiittavaMaukkaalleLounaalleVaihtorahaOnOikeinLaskettu() {
        assertTrue(10 == kassa.syoMaukkaasti(410));
    }
    @Test 
    public void kunMaksuOnRiittavaMyytyjenMaukkaidenLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(400);
        assertTrue(1 == kassa.maukkaitaLounaitaMyyty());
    }
    @Test 
    public void josMaksuEiOleRiittäväMaukkaalleLounaalleKassassaOlevaRahamääräEiMuutu () {
        kassa.syoMaukkaasti(300);
        assertTrue(kassa.kassassaRahaa() == 100000); 
    }
    @Test 
    public void josMaksuEiOleRiittäväMaukkaalleLounaalleKaikkiRahatPalautetaaVaihtorahana() {
        assertTrue(390 == kassa.syoMaukkaasti(390));
    }
    @Test
    public void josMaksuEiOleRiittäväMyytyjenMaukkaidenLounaidenMäärässäEiMuutosta() {
        kassa.syoMaukkaasti(200);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    // Korttiosto toimii edullisten lounaiden osalta
    @Test 
    public void kunKortillaOnTarpeeksiRahaaVeloitetaanSummaKortilta() {
        kassa.syoEdullisesti(kortti);
        assertTrue( 760 == kortti.saldo() );
    }
    @Test
    public void kunKortillaOnTarpeeksiRahaaKassapaatePalauttaaTrue() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    @Test 
    public void kunKortillaOnTarpeeksiRahaaMyytyjenLuonaidenMaaraKasvaa() {
        kassa.syoEdullisesti(kortti);
        assertTrue( 1 == kassa.edullisiaLounaitaMyyty() );
    }
    @Test
    public void kunKortillaOnTarpeeksiRahaaKassassaOlevaRahamaaraEiMuutu() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000); 
    }
    @Test
    public void josKortillaEiOleTarpeeksiRahaaKortinSaldoEiMuutu() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertTrue(200 == kortti.saldo());
    }
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMyytyjenLounaidenMääräMuuttumaton() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    @Test 
    public void josKortillaEiOleTarpeeksiRahaaKassapaatePalauttaaFalse() {
        kortti.otaRahaa(800);
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    @Test
    public void kunKortillaEiOleTarpeeksiRahaaKassassaOlevaRahamaaraEiMuutu() {
        kortti.otaRahaa(800);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000); 
    }
    // Korttiosto toimii maukkaiden lounaiden osalta
    @Test 
    public void kunKortillaOnTarpeeksiMaukkaalleLounaalleRahaaVeloitetaanSummaKortilta() {
        kassa.syoMaukkaasti(kortti);
        assertTrue( 600 == kortti.saldo() );
    }
    @Test
    public void kunKortillaOnTarpeeksiMaukkaalleLounaalleRahaaKassapaatePalauttaaTrue() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    @Test 
    public void kunKortillaOnTarpeeksiRahaaMaukkaalleLounaalleMyytyjenMaukkaidenLuonaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(kortti);
        assertTrue( 1 == kassa.maukkaitaLounaitaMyyty() );
    }
    @Test
    public void kunKortillaOnTarpeeksiRahaaMaukkaalleLounaalleKassassaOlevaRahamaaraEiMuutu() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000); 
    }
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMaukkaalleLounaalleKortinSaldoEiMuutu() {
        kortti.otaRahaa(800);
        kassa.syoMaukkaasti(kortti);
        assertTrue(200 == kortti.saldo());
    }
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMaukkaalleLounaalleMyytyjenMaukkaidenLounaidenMääräMuuttumaton() {
        kortti.otaRahaa(800);
        kassa.syoMaukkaasti(kortti);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    @Test 
    public void josKortillaEiOleTarpeeksiRahaaMaukkaalleLounaalleKassapaatePalauttaaFalse() {
        kortti.otaRahaa(800);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    @Test
    public void kunKortillaEiOleTarpeeksiRahaaMaukkaalleLounaalleKassassaOlevaRahamaaraEiMuutu() {
        kortti.otaRahaa(800);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000); 
    }
    // Kortille Rahaa Ladattaessa
    @Test
    public void kortilleRahaaLadattaessaKortinSaldoMuuttuu () {
        kassa.lataaRahaaKortille(kortti, 10);
        assertTrue(1010 == kortti.saldo());
    }
    @Test
    public void kortilleRahaaLadattaessaKassassaOlevaRahamääräKasvaaLadatullaSummalla() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertTrue(kassa.kassassaRahaa() == 100000 + 10);
    }
    @Test
    public void kortilleYritetaanLadataNegatiivisenSummanMitaanEiTapahdu() {
        kassa.lataaRahaaKortille(kortti, -10);
        assertTrue(1000 == kortti.saldo());

    }
}
