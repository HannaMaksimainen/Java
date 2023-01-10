/**
 * Otetaan mukaan kirjastot, jotka sisältävät tarvittavat valmiit
 * luokat. */
import java.util.*;

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
 * Luokka Suoritus kasittelee suorituksen perustietoja: Opiskelija ID, kurssi ID, kurssin arvosana,
 * kurssin suorituspaiva muodossa vvvkkpp
 */
public class Suoritus{
    protected int opiskelija_id;
    protected int kurssi_id;
    protected int arvonsana;
    protected String suoritus_pvm;

    /**
     * Luokan oletuskontruktori
     */
    public Suoritus(){

    }

    /**
     * 1. Alustaja eli 1.konstruktori joka saa parametreina kaikki suorituksen tiedot.
     * @param opiskelija_id Opiskelijan ID
     * @param kurssi_id Kurssin ID
     * @param arvonsana Kurssin arvosana
     * @param suoritus_pvm Kurssin suorituspaiva
     */
    public Suoritus(int opiskelija_id, int kurssi_id, int arvonsana, String suoritus_pvm){
        this.opiskelija_id = opiskelija_id;
        this.kurssi_id = kurssi_id;
        this.arvonsana = arvonsana;
        this.suoritus_pvm = suoritus_pvm;

    }

    /**
     * getOpiskelija_id palauttaa opiskelijan opiskelija ID:n
     * @return Opiskelijan ID
     */
    public int getOpiskelija_id() {

        return opiskelija_id;
    }

    /**
     * setOpiskelija_id asettaa opiskelijan ID:n
     * @param opiskelija_id Opiskelijan ID
     */
    public void setOpiskelija_id(int opiskelija_id) {

        this.opiskelija_id = opiskelija_id;
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
     * getArvonsana palauttaa kurssin arvosanan
     * @return Kurssin arvosana
     */
    public int getArvonsana() {

        return arvonsana;
    }

    /**
     * setArvonsana asettaa kurssin arvosanan
     * @param arvonsana Kurssin arvosana
     */
    public void setArvonsana(int arvonsana) {

        this.arvonsana = arvonsana;
    }

    /**
     * getSuoritus_pvm palauttaa kurssin suorituspaivamaaran
     * @return Kurssin suorituspaivamaara
     */
    public String getSuoritus_pvm() {

        return suoritus_pvm;
    }

    /**
     * setSuoritus_pvm asettaa kurssin suorituspaivamaaran
     * @param suoritus_pvm Kurssin suorituspaivamaara
     */
    public void setSuoritus_pvm(String suoritus_pvm) {

        this.suoritus_pvm = suoritus_pvm;
    }

    /**
     * toString metodi palauttaa kuvauksen suorituksesta.
     * @return Suoritus olion tiedot.
     */
    public String toString() {
        return "Suoritus: Opiskelija_ID " + opiskelija_id + ", Kurssi_ID: " + kurssi_id +
                ", Arvosana " + arvonsana + ", Suorituksen_pvm " + suoritus_pvm;
    }
}



