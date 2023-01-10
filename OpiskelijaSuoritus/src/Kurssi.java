/**
 * Otetaan mukaan kirjastot, jotka sisältävät tarvittavat valmiit
 * luokat. */
import java.util.LinkedList;

/**
 *  Harjoitustyö, Olio-ohjelmoinnin perusteet, syksy 2021
 *
 *  @Hanna Maksimainen
 *  Karelian ammattikorkeakoulu
 *  Tietojenkäsittelyn koulutusohjelma
 *  @Version 1.0 15. joulukuuta
 *
 */

/**
 * Luokka Kurssi kasittelee kurssin perustietoja: Kurssi ID, kurssin nimi, kurssin opintopisteet,
 * kurssin kuvaus.
 */
public class Kurssi {
    protected int kurssi_id;
    protected String nimi;
    protected int opintopisteet;
    protected String kuvaus;

    /**
     * Luodaan LinkedList kurssinSuoritukset, jonne kootaan kurssin suoritukset ja se palauttaa
     * kokoelman kurssisuorituksista
     */
    LinkedList<Suoritus> kurssinSuoritukset = new LinkedList<Suoritus>();

    /**
     * Luodaan LinkedList kurssinOpiskelijat, jonne kootaan kurssin opiskelijat ja se palauttaa
     * kokoelman kurssin opiskelijoista
     */

    LinkedList<Opiskelija> kurssinOpiskelijat = new LinkedList<Opiskelija>();

    /**
     * Luokan oletuskontruktori
     */
    public Kurssi(){

    }

    /**
     * 1. Alustaja eli 1.konstruktori joka saa parametreina kaikki kurssin tiedot.
     * @param kurssi_id Kurssin ID
     * @param nimi Kurssin nimi
     * @param opintopisteet Kurssin opintopisteet
     * @param kuvaus Kurssin kuvaus
     */
    public Kurssi(int kurssi_id, String nimi, int opintopisteet, String kuvaus){
        this.kurssi_id = kurssi_id;
        this.nimi = nimi;
        this.opintopisteet = opintopisteet;
        this.kuvaus = kuvaus;

    }

    /**
     * getKurssi_id palauttaa kurssin ID:n
     * @return Kurssin ID
     */
    public int getKurssi_id() {

        return kurssi_id;
    }

    /**
     * setKurssi_id asettaa kurssin ID:n
     * @param kurssi_id Kurssin ID
     */
    public void setKurssi_id(int kurssi_id) {

        this.kurssi_id = kurssi_id;
    }

    /**
     * getNimi palauttaa kurssin nimen
     * @return Kurssin nimi
     */
    public String getNimi() {

        return nimi;
    }

    /**
     * setNimi asettaa kurssin nimen
     * @param nimi Kurssin nimi
     */
    public void setNimi(String nimi) {

        this.nimi = nimi;
    }

    /**
     * getOpintopisteet palauttaa kurssin opintopisteet
     * @return Kurssin opintopisteet
     */
    public int getOpintopisteet() {

        return opintopisteet;
    }

    /**
     * setOpintopisteet asettaa kurssin opintopisteet
     * @param opintopisteet Kurssin opintopisteet
     */
    public void setOpintopisteet(int opintopisteet) {

        this.opintopisteet = opintopisteet;
    }

    /**
     * getKuvaus palauttaa kurssin kuvauksen
     * @return Kurssin kuvaus
     */
    public String getKuvaus() {

        return kuvaus;
    }

    /**
     * setKuvaus asettaa kurssin kuvauksen
     * @param kuvaus Kurssin kuvaus
     */
    public void setKuvaus(String kuvaus) {

        this.kuvaus = kuvaus;
    }

    /**
     * getKurssinSuoritukset palauttaa kurssin suoritusten nimet
     * @return Kurssin suoritusten nimet
     */
    public Suoritus getKurssinSuoritukset(Suoritus suoritustenNimet){

        return suoritustenNimet;
    }

    /**
     * getKurssinOpiskelijat palauttaa kurssin opiskelijoiden nimet
     * @return Kurssin opiskelijoiden nimet
     */
    public Opiskelija getKurssinOpiskelijat(Opiskelija opiskelijoidenNimet){

        return opiskelijoidenNimet;
    }

    /**
     * toString metodi palauttaa kuvauksen kurssista.
     * @return Kurssi olion tiedot.
     */
    public String toString(){

        return "Kurssin ID: " + kurssi_id + " Kurssin nimi: "+ nimi + " Opintopisteet: " + opintopisteet + " Kuvaus: "+ kuvaus;
    }

}
