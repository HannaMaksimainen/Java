package sample;
/**
 * Otetaan mukaan kirjastot, jotka sisältävät tarvittavat valmiit
 * luokat.
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Harjoitustyö, Käyttöliittymäohjelmointi, kevät 2022
 *
 *  @Hanna Maksimainen
 *  Karelian ammattikorkeakoulu
 *  Tietojenkäsittelyn koulutusohjelma
 *  @Version 1.0 30.03.2022
 *
 */

/**
 * Luokka Suoritus kasittelee suorituksen perustietoja: Opiskelija ID, kurssi ID, kurssin arvosana,
 * kurssin suorituspaiva muodossa vvvkkpp
 *
 */
public class Suoritus {
    protected int opiskelija_id;
    protected int kurssi_id;
    protected String arvonsana;
    protected String suoritus_pvm;

    public Suoritus() {
        this.opiskelija_id = 0;
        this.kurssi_id = 0;
        this.arvonsana = "";
        this.suoritus_pvm = "";
    }

    /**
     * getOpiskelija_id palauttaa opiskelijan opiskelija ID:n
     * @return Opiskelijan ID
     *
     */
    public int getOpiskelija_id() {

        return opiskelija_id;
    }

    /**
     * setOpiskelija_id asettaa opiskelijan ID:n
     * @param opiskelija_id Opiskelijan ID
     *
     */
    public void setOpiskelija_id(int opiskelija_id) {

        this.opiskelija_id = opiskelija_id;
    }

    /**
     * getKurssi_id palauttaa kurssin ID:n
     * @return Kurssin ID
     *
     */
    public int getKurssi_id() {

        return kurssi_id;
    }

    /**
     * setKurssi_id asettaa kurssin ID:n
     * @param kurssi_id Kurssin ID
     *
     */
    public void setKurssi_id(int kurssi_id) {

        this.kurssi_id = kurssi_id;
    }

    /**
     * getArvonsana palauttaa kurssin arvosanan
     * @return Kurssin arvosana
     *
     */
    public String getArvonsana() {

        return arvonsana;
    }

    /**
     * setArvonsana asettaa kurssin arvosanan
     * @param arvonsana Kurssin arvosana
     *
     */
    public void setArvonsana(String arvonsana) {
        this.arvonsana = arvonsana;
    }

    /**
     * getSuoritus_pvm palauttaa kurssin suorituspaivamaaran
     * @return Kurssin suorituspaivamaara
     *
     */
    public String getSuoritus_pvm() {

        return suoritus_pvm;
    }

    /**
     * setSuoritus_pvm asettaa kurssin suorituspaivamaaran
     * @param suoritus_pvm Kurssin suorituspaivamaara
     *
     */
    public void setSuoritus_pvm(String suoritus_pvm) {

        this.suoritus_pvm = suoritus_pvm;
    }

    /**
     * toString metodi palauttaa kuvauksen suorituksesta.
     * @return Suoritus olion tiedot.
     *
     */
    @Override
    public String toString() {
        return "Suoritus{" +
                "opiskelija_id=" + opiskelija_id +
                ", kurssi_id=" + kurssi_id +
                ", arvonsana=" + arvonsana +
                ", suoritus_pvm='" + suoritus_pvm + '\'' +
                '}';
    }

    /**
     *haeSuoritusKurssiJaOpiskelija() metodilla haetaan opiskelijan suorituksen ja kurssin tiedot.
     *
     *Tässä metodissa otetaan myös yhteystietokantaan.
     *@return SuoritusOlio.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public static Suoritus haeSuoritusKurssiJaOpiskelija (Connection connection, int id1, int id2) throws SQLException, Exception {
        // Tietokannasta haetaan suoritusta, jonka opiskelija_id = id ja kurssi_id = id2. Asetetaan ehdot opiskelija_id ja kurssi_id edempänä
        String sql = "SELECT opiskelija_id, kurssi_id, arvosana, suoritus_pvm "
                + " FROM suoritus WHERE opiskelija_id = ? AND kurssi_id = ?";
        ResultSet tulosjoukko1 = null;
        PreparedStatement lause1 = null;

        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause1 = connection.prepareStatement(sql);
            // Asetetaan arvot where ehtoihin.
            lause1.setInt( 1, id1);
            lause1.setInt( 2, id2);
            // Suoritetaan SQL-lause
            tulosjoukko1 = lause1.executeQuery();
            if (tulosjoukko1 == null) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Suoritusta ei loydy");
            }
        } catch (SQLException see) {
            // SQL virheet
            throw see;
        } catch (Exception ee) {
            // JDBC virheet
            throw ee;
        }
        // Laitetaan tiedot SuoritusOliolle.
        Suoritus SuoritusOlio = new Suoritus ();

        try {
            if (tulosjoukko1.next () == true){
                SuoritusOlio.setOpiskelija_id (tulosjoukko1.getInt("opiskelija_id"));
                SuoritusOlio.setKurssi_id (tulosjoukko1.getInt("kurssi_id"));
                SuoritusOlio.setArvonsana(tulosjoukko1.getString("arvosana"));
                SuoritusOlio.setSuoritus_pvm (tulosjoukko1.getString("suoritus_pvm"));
            }

        }catch (SQLException ee) {
            throw ee;
        }

        return SuoritusOlio;
    }

    /**
     * haeOpiskelijanID netodissa haetaan opiskelijan ID:n perusteella tiedot kurssista ja suorituksesta.
     *
     * Tässä metodissa otetaan myös yhteystietokantaan.
     * @return SuoritusOlio2.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public static Suoritus haeOpiskelijanID (Connection connection, int id) throws SQLException, Exception {
        // Tietokannasta haetaan opiskelijaa, jonka opiskelija_id = id. Asetetaan opiskelija_id edempänä
        String sql = "SELECT opiskelija_id, kurssi_id, arvosana, suoritus_pvm "
                + " FROM suoritus WHERE opiskelija_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, id);
            // Suoritetaan SQL-lause
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Opiskelijan suoritusta ei loydy");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Laitetaan tiedot SuoritusOlio1selle.
        Suoritus SuoritusOlio1 = new Suoritus();
        try {
            if (tulosjoukko.next () == true){
                //Opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro
                SuoritusOlio1.setOpiskelija_id (tulosjoukko.getInt("opiskelija_id"));
                SuoritusOlio1.setKurssi_id (tulosjoukko.getInt("kurssi_id"));
                SuoritusOlio1.setArvonsana(tulosjoukko.getString("arvosana"));
                SuoritusOlio1.setSuoritus_pvm(tulosjoukko.getString("suoritus_pvm"));
            }

        }catch (SQLException e) {
            throw e;
        }

        return SuoritusOlio1;
    }

    /**
     * haeKurssinID metodissa haetaan kurssin ID:n perusteella tiedot opiskelijan kurssista ja suorituksesta.
     *
     * Tässä metodissa otetaan myös yhteys tietokantaan.
     * @return SuoritusOlio1.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public static Suoritus haeKurssinID (Connection connection, int id) throws SQLException, Exception {
        // Tietokannasta haetaan opiskelijaa, jonka opiskelija_id = id. Asetetaan  opiskelija_id edempänä
        String sql = "SELECT opiskelija_id, kurssi_id, arvosana, suoritus_pvm "
                + " FROM suoritus WHERE opiskelija_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, id);
            // Suoritetaan SQL-lause
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Kurssin suoritusta ei loydy");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Laitetaan tiedot SuoritusOlio2selle.
        Suoritus SuoritusOlio2 = new Suoritus();
        try {
            if (tulosjoukko.next () == true){
                //Opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro
                SuoritusOlio2.setOpiskelija_id (tulosjoukko.getInt("opiskelija_id"));
                SuoritusOlio2.setKurssi_id (tulosjoukko.getInt("kurssi_id"));
                SuoritusOlio2.setArvonsana(tulosjoukko.getString("arvosana"));
                SuoritusOlio2.setSuoritus_pvm(tulosjoukko.getString("suoritus_pvm"));

            }

        }catch (SQLException e) {
            throw e;
        }

        return SuoritusOlio2;
    }

    /**
     * lisaaSuoritus() metodissa haetaan ja lisätään suorituksen tiedot.
     *
     * Tässä metodissa otetaan myös yhteystietokantaan.
     * @return 0.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public int lisaaSuoritus (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        // Tietokannasta haetaan opiskelijaa, jonka opiskelija_id = id. Asetetaan opiskelija_id edempänä. Suoritusta ei voi lisätä, jos se on jo kannassa.
        String sql = "SELECT opiskelija_id, kurssi_id"
                + " FROM suoritus WHERE opiskelija_id = ? AND kurssi_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;

        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, getOpiskelija_id());
            lause.setInt( 2, getKurssi_id());

            // Suoritetaan SQL-lause
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko.next () == true) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Suoritus on jo olemassa");
            }
        } catch (SQLException see) {
            // SQL virheet
            throw see;
        } catch (Exception ee) {
            // JDBC virheet
            throw ee;
        }
        // Asetetaan INSERT INTO SQLssä suorituksen tiedot.
        sql = "INSERT INTO suoritus "
                + "(opiskelija_id, kurssi_id, arvosana, suoritus_pvm) "
                + " VALUES (?, ?, ?, ?)";

        lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            // Asetetaan arvot INSERTIIN
            lause.setInt( 1, getOpiskelija_id());
            lause.setInt(2, getKurssi_id());
            lause.setString(3, getArvonsana());
            lause.setString(4, getSuoritus_pvm());

            // Suoritetaan SQL-lause
            int lkm2 = lause.executeUpdate();

            if (lkm2 == 0) {
                throw new Exception("Suorituksen lisaaminen ei onnistu");
            }
        } catch (SQLException see) {
            // SQL virheet
            throw see;
        } catch (Exception ee) {
            // JDBC ym. virheet
            throw ee;
        }
        return 0;
    }

    /**
     * muutaSuoritus() metodilla muutetaan opiskelijan suorituksen tiedot.
     * Tässä metodissa otetaan myös yhteystietokantaan.
     *
     * Tässä metodissa on mahdollista muokata muut tiedot, paitsi opiskelija_ID ja kurssi_ID
     * @return 0.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public int muutaSuoritus (Connection connection) throws SQLException, Exception {
        // Tietokannasta haetaan opiskelijaa, jonka opiskelija_id = id. Asetetaan  opiskelija_id edempänä.
        String sql = "SELECT opiskelija_id, kurssi_id"
                + " FROM suoritus WHERE opiskelija_id = ? AND kurssi_id = ?";

        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, getOpiskelija_id());
            lause.setInt( 2, getKurssi_id());
            // Suoritetaan SQL-lause
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko.next () == false) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Suoritusta ei loydy tietokannasta");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Asetetaan olion arvot UPDATEen SQLssä suorituksen tiedot.
        sql = "UPDATE  suoritus "
                + "SET arvosana = ?, suoritus_pvm = ? "
                + " WHERE opiskelija_id = ? AND kurssi_id = ?";

        lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);

            // Asetetaan arvot UPDATEn.
            lause.setString(1, getArvonsana());
            lause.setString(2, getSuoritus_pvm());

            // Asetetaan arvot opiskelija_id:seen ja kurssi_id:seen
            lause.setInt( 3, getOpiskelija_id());
            lause.setInt( 4, getKurssi_id());
            // Suoritetaan SQL-lause
            int lkm = lause.executeUpdate();
            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Suorituksen muuttaminen ei onnistu");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC ym. virheet
            throw e;
        }
        return 0;
    }

    /**
     * poistaSuoritus() metodilla poistetaan suorituksen tiedot opiskelija_ID:n perusteella.
     *
     * Tässä metodissa otetaan myös yhteystietokantaan.
     * @return 0.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public int poistaSuoritus (Connection connection1) throws SQLException, Exception {
        // Tietokannasta haetaan suorituksen tiedot opiskelija_id:n ja kurssi_id:n perusteella ja poistetaan ne tietokannasta DELETEn avulla.
        String sql = "DELETE FROM suoritus WHERE opiskelija_id = ? AND kurssi_id = ?";
        PreparedStatement lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection1.prepareStatement(sql);
            // Asetetaan olion arvot DELETEen.
            lause.setInt( 1, getOpiskelija_id());
            lause.setInt( 2, getKurssi_id());
            // Suoritetaan SQL-lause
            int lkm = lause.executeUpdate();
            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Suorituksen poistaminen ei onnistu");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        return 0; // toiminto ok
    }
}
