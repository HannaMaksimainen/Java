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
 * Luokka Kurssi kasittelee kurssin perustietoja: Kurssi ID, kurssin nimi, kurssin opintopisteet,
 * kurssin kuvaus.
 *
 */
public class Kurssi {
    protected int kurssi_id;
    protected String nimi;
    protected String opintopisteet;
    protected String kuvaus;

    /**
     * Luokan oletuskontruktori
     *
     */
    public Kurssi(){

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
     * getNimi palauttaa kurssin nimen
     * @return Kurssin nimi
     *
     */
    public String getNimi() {

        return nimi;
    }

    /**
     * setNimi asettaa kurssin nimen
     * @param nimi Kurssin nimi
     *
     */
    public void setNimi(String nimi) {

        this.nimi = nimi;
    }

    /**
     * getOpintopisteet palauttaa kurssin opintopisteet
     * @return Kurssin opintopisteet
     *
     */
    public String getOpintopisteet() {

        return opintopisteet;
    }

    /**
     * setOpintopisteet asettaa kurssin opintopisteet
     * @param opintopisteet Kurssin opintopisteet
     *
     */
    public void setOpintopisteet(String opintopisteet) {

        this.opintopisteet = opintopisteet;
    }

    /**
     * getKuvaus palauttaa kurssin kuvauksen
     * @return Kurssin kuvaus
     *
     */
    public String getKuvaus() {

        return kuvaus;
    }

    /**
     * setKuvaus asettaa kurssin kuvauksen
     * @param kuvaus Kurssin kuvaus
     *
     */
    public void setKuvaus(String kuvaus) {

        this.kuvaus = kuvaus;
    }

    /**
     * toString metodi palauttaa kuvauksen kurssista.
     * @return Kurssi olion tiedot.
     *
     */
    @Override
    public String toString() {
        return "Suoritus{" +
                "kurssi_id=" + kurssi_id +
                ", nimi='" + nimi + '\'' +
                ", opintopisteet=" + opintopisteet +
                ", kuvaus='" + kuvaus + '\'' +
                '}';
    }

    /**
     * haeKurssi(9 metodilla haetaan kurssin tiedot.
     * Tässä metodissa otetaan myös yhteys tietokantaan.
     *
     * @return kurssiOlio.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public static Kurssi haeKurssi (Connection connection, int id) throws SQLException, Exception {
        // Tietokannasta haetaan opiskelijaa, jonka opiskelija_id = id. Asetetaan  opiskelija_id edempänä.
        String sql = "SELECT kurssi_id, nimi, opintopisteet, kuvaus"
                + " FROM kurssi WHERE kurssi_id = ?";
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
                throw new Exception("Kurssia ei loydy");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Laitetaan tiedot kurssiOliolle.
        Kurssi kurssiOlio = new Kurssi();

        try {
            if (tulosjoukko.next () == true){
                //Opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro
                kurssiOlio.setKurssi_id((tulosjoukko.getInt("kurssi_id")));
                kurssiOlio.setNimi((tulosjoukko.getString("nimi")));
                kurssiOlio.setOpintopisteet((tulosjoukko.getString("opintopisteet")));
                kurssiOlio.setKuvaus((tulosjoukko.getString("kuvaus")));
            }

        }catch (SQLException e) {
            throw e;
        }

        return kurssiOlio;
    }

    /**
     * lisaaKurssi() metodilla lisätään kurssin tiedot.
     * Tässä metodissa otetaan myös yhteys tietokantaan.
     *
     * @return 0.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public int lisaaKurssi (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        // Tietokannasta haetaan opiskelijaa, jonka opiskelija_id = id. Asetetaan opiskelija_id edempänä. Kurssia ei voi lisätä, jos se on jo kannassa.
        String sql = "SELECT kurssi_id"
                + " FROM kurssi WHERE kurssi_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;

        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, getKurssi_id()); // asetetaan where ehtoon (?) arvo, olion Opiskelijaid
            // Suoritetaan SQL-lause
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko.next () == true) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Kurssi on jo olemassa");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Asetetaan INSERT INTO SQLssä kurssin tiedot.
        sql = "INSERT INTO kurssi "
                + "(kurssi_id, nimi, opintopisteet, kuvaus) "
                + " VALUES (?, ?, ?, ?)";

        lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            // laitetaan arvot INSERTtiin
            lause.setInt( 1, getKurssi_id());
            lause.setString(2, getNimi());
            lause.setString(3, getOpintopisteet());
            lause.setString(4, getKuvaus());

            // Asetetaan arvot INSERTIIN
            int lkm = lause.executeUpdate();

            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Kurssin lisaaminen ei onnistu");
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
     * muutaKurssi() metodilla muutetaan kurssin tiedot.
     * Tässä metodissa otetaan myös yhteystietokantaan.
     * Tässä metodissa on mahdollista muokata muut tiedot, paitsi opiskelija_ID
     *
     * @return 0.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public int muutaKurssi(Connection connection) throws SQLException, Exception {
        // Tietokannasta haetaan kurssia, kurssia_id = id. Asetetaan kurssia_id edempänä. Annetaan virhe, jos suoritusta ei voi lisätä.
        String sql = "SELECT kurssi_id"
                + " FROM kurssi WHERE kurssi_id = ?";
        ResultSet tulosjoukko1 = null;
        PreparedStatement lause1 = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause1 = connection.prepareStatement(sql);
            lause1.setInt(1, getKurssi_id());
            // Suoritetaan SQL-lause
            tulosjoukko1 = lause1.executeQuery();
            if (tulosjoukko1.next() == false) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Kurssia ei loydy tietokannasta");
            }
        } catch (SQLException see) {
            // SQL virheet
            throw see;
        } catch (Exception ee) {
            // JDBC virheet
            throw ee;
        }
        // Asetetaan olion arvot UPDATEen SQLssä suorituksen tiedot.
        sql = "UPDATE kurssi "
                + "SET nimi = ?, opintopisteet = ?, kuvaus = ? "
                + " WHERE kurssi_id = ?";

        lause1 = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause1 = connection.prepareStatement(sql);

            // Asetetaan arvot UPDATEn.
            lause1.setString(1, getNimi());
            lause1.setString(2, getOpintopisteet());
            lause1.setString(3, getKuvaus());

            // Asetetaan arvo kurssi_id:seen
            lause1.setInt(4, getKurssi_id());
            // Suoritetaan SQL-lause
            int lkm = lause1.executeUpdate();
            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Kurssin muuttaminen ei onnistu");
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
     * poistaKurssi() metodilla poistetaan kurssin tiedot opiskelija_ID:n perusteella.
     * Tässä metodissa otetaan myös yhteystietokantaan.
     * @return 0.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public int poistaKurssi (Connection connection) throws SQLException, Exception {
        // Tietokannasta haetaan kurssin tiedot kurssi_id:n perusteella ja poistetaan ne tietokannasta DELETEn avulla.
        String sql = "DELETE FROM  kurssi WHERE kurssi_id = ?";
        PreparedStatement lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            // Asetetaan olion arvot DELETEen.
            lause.setInt( 1, getKurssi_id());
            // Suoritetaan SQL-lause
            int lkm = lause.executeUpdate();
            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Kurssin poistaminen ei onnistu");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        return 0;
    }

}
