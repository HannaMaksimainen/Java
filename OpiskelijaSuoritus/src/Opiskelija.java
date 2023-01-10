/**
 * Otetaan mukaan kirjastot, jotka sisältävät tarvittavat valmiit
 * luokat. */
import java.io.Serializable;
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
 * Luokka Opiskelija kasittelee henkilon perustietoja: opiskelija_ID, etunimi, sukunimi, lähiosoite,
 * postitoimipaikka, postinumero, email ja puhelinnumero.
 */
public class Opiskelija implements Serializable {
    protected int opiskelija_id;
    protected String etunimi;
    protected String sukunimi;
    protected String lahiosoite;
    protected String postitoimipaikka;
    protected String postinumero;
    protected String email;
    protected String puhelinnro;

    /**
     * Luodaan LinkedList, jonne kootaan opiskelijan suoritukset ja se palauttaa
     * kokoelman opiskelijan suorituksista
     */
    LinkedList<Suoritus> Suoritukset = new LinkedList<Suoritus>();

    /**
     * Luokan oletuskontruktori
     */
    public Opiskelija(){

    }

    /**
     * 1. Alustaja eli 1.konstruktori joka saa parametreina kaikki opiskelijan tiedot.
     * @param opiskelija_id Opiskelijan ID
     * @param etunimi Opiskelijan etunimi
     * @param sukunimi Opiskelijan sukunimi
     * @param lahiosoite Opiskelijan lahiosoite
     * @param postitoimipaikka Opiskelijan postitoimipaikka
     * @param postinumero Opiskelijan postitoimipaikka
     * @param email Opiskelijan sahkopostiosoite
     * @param puhelinnro Opiskelijan puhelinnumero
     */
    public Opiskelija(int opiskelija_id, String etunimi, String sukunimi, String lahiosoite,
                      String postitoimipaikka, String postinumero, String email, String puhelinnro){
        this.opiskelija_id = opiskelija_id;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinumero = postinumero;
        this.email = email;
        this.puhelinnro = puhelinnro;

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
     * getEtunimi palauttaa opiskelijan etunimen
     * @return Opiskelijan etunimi
     */
    public String getEtunimi() {

        return etunimi;
    }

    /**
     * setEtunimi asettaa opiskelijan etunimen
     * @param etunimi Opiskelijan etunimi
     */
    public void setEtunimi(String etunimi) {

        this.etunimi = etunimi;
    }

    /**
     * getSukunimi palauttaa opiskelijan sukunimen
     * @return Opiskelijan sukunimi
     */
    public String getSukunimi() {

        return sukunimi;
    }

    /**
     * setSukunimi asettaa opiskelijan sukunimen
     * @param sukunimi Opiskelijan sukunimi
     */
    public void setSukunimi(String sukunimi) {

        this.sukunimi = sukunimi;
    }

    /**
     * getLahiosoite palauttaa opiskelijan lahiosoitteen
     * @return Opiskelijan osoite
     */
    public String getLahiosoite() {

        return lahiosoite;
    }

    /**
     * setLahiosoite asettaa opiskelijan lahiosoitteen
     * @param lahiosoite Opiskelijan lahiosoite
     */
    public void setLahiosoite(String lahiosoite) {

        this.lahiosoite = lahiosoite;
    }

    /**
     * getPostitoimipaikka palauttaa opiskelijan postitoimipaikan
     * @return Opiskelijan postitoimipaikka
     */
    public String getPostitoimipaikka() {

        return postitoimipaikka;
    }

    /**
     * setPostitoimipaikka asettaa opiskelijan postitoimipaikan
     * @param postitoimipaikka Opiskelijan postitoimipaikka
     */
    public void setPostitoimipaikka(String postitoimipaikka) {

        this.postitoimipaikka = postitoimipaikka;
    }

    /**
     * getPostinumero palauttaa opiskelijan postinumeron
     * @return Opiskelijan postinumero
     */
    public String getPostinumero() {

        return postinumero;
    }

    /**
     * setPostinumero asettaa opiskelijan postinumeron
     * @param postinumero Opiskelijan postinumero
     */
    public void setPostinumero(String postinumero) {

        this.postinumero = postinumero;
    }

    /**
     * getEmail palauttaa opiskelijan sahkopostiosoitteen
     * @return Opiskelijan sahkopostiosoite
     */
    public String getEmail() {

        return email;
    }

    /**
     * setEmail asettaa opiskelijan sahkopostiosoitteen
     * @param email Opiskelijan sahkopostiosoite
     */
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * getPuhelinnro palauttaa opiskelijan puhelinnumeron
     * @return Opiskelijan puhelinnumero
     */
    public String getPuhelinnro() {

        return puhelinnro;
    }

    /**
     * setPuhelinnro asettaa opiskelijan puhelinnumeron
     * @param puhelinnro Opiskelijan puhelinnumero
     */
    public void setPuhelinnro(String puhelinnro) {

        this.puhelinnro = puhelinnro;
    }

    /**
     * getOpiskelijanSuoritukset palauttaa opiskelijan suoritukset LinkedListilla
     * @return Opiskelijan Suoritukset
     */
    public LinkedList<Suoritus> getOpiskelijanSuoritukset() {

        return this.Suoritukset;
    }


    /**
     * lisaaSuoritus lisaa LinkedListille uuden suorituksen
     *
     */
     public void lisaaSuoritus (Suoritus newSuoritus) {
      this.Suoritukset.add(newSuoritus);
     }

    /**
     * toString metodi palauttaa kuvauksen opiskelijasta.
     * @return Opiskelija olion tiedot.
     */
    public String toString(){
        return "Opiskelijan tiedot: ID " + opiskelija_id + " Nimi: " + etunimi + " " + sukunimi + " Osoite: " + lahiosoite
                + " " + postitoimipaikka + " " + postinumero + " Email: " + email + " Puhelinnumero: " + puhelinnro;
    }

}
