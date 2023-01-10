/**Harjoitustyön paketti
 *
 */
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
 * Luokka Opiskelija kasittelee opiskelijan perustietoja: opiskelija_ID, etunimi, sukunimi, lähiosoite,
 * postitoimipaikka, postinumero, email ja puhelinnumero.
 *
 */
public class Opiskelija {
    protected int opiskelija_id;
    protected String etunimi;
    protected String sukunimi;
    protected String lahiosoite;
    protected String postitoimipaikka;
    protected String postinumero;
    protected String email;
    protected String puhelinnro;

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
     * getEtunimi palauttaa opiskelijan etunimen
     * @return Opiskelijan etunimi
     *
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     * setEtunimi asettaa opiskelijan etunimen
     * @param etunimi Opiskelijan etunimi
     *
     */
    public void setEtunimi(String etunimi) {

        this.etunimi = etunimi;
    }

    /**
     * getSukunimi palauttaa opiskelijan sukunimen
     * @return Opiskelijan sukunimi
     *
     */
    public String getSukunimi() {

        return sukunimi;
    }

    /**
     * setSukunimi asettaa opiskelijan sukunimen
     * @param sukunimi Opiskelijan sukunimi
     *
     */
    public void setSukunimi(String sukunimi) {

        this.sukunimi = sukunimi;
    }

    /**
     * getLahiosoite palauttaa opiskelijan lahiosoitteen
     * @return Opiskelijan osoite
     *
     */
    public String getLahiosoite() {

        return lahiosoite;
    }

    /**
     * setLahiosoite asettaa opiskelijan lahiosoitteen
     * @param lahiosoite Opiskelijan lahiosoite
     *
     */
    public void setLahiosoite(String lahiosoite) {

        this.lahiosoite = lahiosoite;
    }

    /**
     * getPostitoimipaikka palauttaa opiskelijan postitoimipaikan
     * @return Opiskelijan postitoimipaikka
     *
     */
    public String getPostitoimipaikka() {

        return postitoimipaikka;
    }

    /**
     * setPostitoimipaikka asettaa opiskelijan postitoimipaikan
     * @param postitoimipaikka Opiskelijan postitoimipaikka
     *
     */
    public void setPostitoimipaikka(String postitoimipaikka) {

        this.postitoimipaikka = postitoimipaikka;
    }

    /**
     * getPostinumero palauttaa opiskelijan postinumeron
     * @return Opiskelijan postinumero
     *
     */
    public String getPostinumero() {

        return postinumero;
    }

    /**
     * setPostinumero asettaa opiskelijan postinumeron
     * @param postinumero Opiskelijan postinumero
     *
     */
    public void setPostinumero(String postinumero) {

        this.postinumero = postinumero;
    }

    /**
     * getEmail palauttaa opiskelijan sahkopostiosoitteen
     * @return Opiskelijan sahkopostiosoite
     *
     */
    public String getEmail() {

        return email;
    }

    /**
     * setEmail asettaa opiskelijan sahkopostiosoitteen
     * @param email Opiskelijan sahkopostiosoite
     *
     */
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * getPuhelinnro palauttaa opiskelijan puhelinnumeron
     * @return Opiskelijan puhelinnumero
     *
     */
    public String getPuhelinnro() {

        return puhelinnro;
    }

    /**
     * setPuhelinnro asettaa opiskelijan puhelinnumeron
     * @param puhelinnro Opiskelijan puhelinnumero
     *
     */
    public void setPuhelinnro(String puhelinnro) {

        this.puhelinnro = puhelinnro;
    }


    /**
     * toString metodi palauttaa kuvauksen opiskelijasta.
     * @return Opiskelija olion tiedot.
     *
     */
    @Override
    public String toString() {
        return "Opiskelija{" +
                "opiskelija_id=" + opiskelija_id +
                ", etunimi='" + etunimi + '\'' +
                ", sukunimi='" + sukunimi + '\'' +
                ", lahiosoite='" + lahiosoite + '\'' +
                ", postitoimipaikka='" + postitoimipaikka + '\'' +
                ", postinumero='" + postinumero + '\'' +
                ", email='" + email + '\'' +
                ", puhelinnro='" + puhelinnro + '\'' +
                '}';
    }

    /**
     * haeOpiskelija() -metodi hakee opiskelijan tiedot.
     * Tässä metodissa otetaan myös yhteys tietokantaan.
     *
     * @return opiskelijaOlio
     * @throws SQLException
     * @throws Exception
     *
     */
    public static Opiskelija haeOpiskelija (Connection connection, int id) throws SQLException, Exception {
        // Haetaan tietokannasta opiskelija, jonka id = opiskelija_id. opiskelija_id asetetaan etenpänä.
        String sql = "SELECT opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinumero, email, puhelinnumero "
                + " FROM opiskelija WHERE opiskelija_id = ?";

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
                throw new Exception("Opiskelijaa ei loydy");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Asetetaan tiedot OpiskelijaOliolle.
        Opiskelija OpiskelijaOlio = new Opiskelija ();

        try {
            if (tulosjoukko.next () == true){
                //Tiedot: opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnumero
                OpiskelijaOlio.setOpiskelija_id (tulosjoukko.getInt("opiskelija_id"));
                OpiskelijaOlio.setEtunimi (tulosjoukko.getString("etunimi"));
                OpiskelijaOlio.setSukunimi(tulosjoukko.getString("sukunimi"));
                OpiskelijaOlio.setLahiosoite (tulosjoukko.getString("lahiosoite"));
                OpiskelijaOlio.setPostitoimipaikka (tulosjoukko.getString("postitoimipaikka"));
                OpiskelijaOlio.setPostinumero (tulosjoukko.getString("postinumero"));
                OpiskelijaOlio.setEmail (tulosjoukko.getString("email"));
                OpiskelijaOlio.setPuhelinnro (tulosjoukko.getString("puhelinnumero"));
            }

        }catch (SQLException e) {
            throw e;
        }

        return OpiskelijaOlio;
    }

    /**
     * lisaaOpiskelija() -metodi hakee ja lisää opiskelijan tiedot.
     * Tässä metodissa otetaan myös yhteys tietokantaan.
     *
     * @return 0.
     *
     * Välitetään parametrina tietokantayhteys tietokantaan.
     * @throws SQLException
     * @throws Exception
     *
     */
    public int lisaaOpiskelija (Connection connection) throws SQLException, Exception {
        // Tietokannasta haetaan opiskelijan tiedot opiskelija_id:n perusteella. opiskelija_id astetetaan etenpänä.
        String sql = "SELECT opiskelija_id"
                + " FROM opiskelija WHERE opiskelija_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;

        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, getOpiskelija_id());
            // Suoritetaan SQL-lause
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko.next () == true) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Opiskelija on jo olemassa");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Asetetaan INSERT INTO SQLssä opiskelijan tiedot.
        sql = "INSERT INTO opiskelija "
                + "(opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinumero, email, puhelinnumero) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            // Asetetaan arvot INSERTIIN
            lause.setInt( 1, getOpiskelija_id());
            lause.setString(2, getEtunimi());
            lause.setString(3, getSukunimi());
            lause.setString(4, getLahiosoite());
            lause.setString(5, getPostitoimipaikka ());
            lause.setString(6, getPostinumero());
            lause.setString(7, getEmail ());
            lause.setString(8, getPuhelinnro ());
            // Suoritetaan SQL-lause
            int lkm = lause.executeUpdate();

            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Opiskelijan lisaaminen ei onnistu");
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
     * muutaOpiskelija -metodi muuttaa opiskelijan tiedot.
     * Tässä metodissa otetaan myös yhteystietokantaan.
     * Tässä metodissa on mahdollista muokata muut tiedot, paitsi opiskelija_ID
     *
     * @return 0.
     *
     * Välitetään parametrina tietokantayhteys tietokantaan.
     * @throws SQLException
     * @throws Exception
     *
     */
    public int muutaOpiskelija (Connection connection) throws SQLException, Exception {
        // Tietokannasta haetaan opiskelijan tiedot opiskelija_id:n perusteella. opiskelija_id asetetaan etenpänä.
        String sql = "SELECT opiskelija_id"
                + " FROM opiskelija WHERE opiskelija_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, getOpiskelija_id());
            // Suoritetaan SQL-lause
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko.next () == false) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Opiskelijaa ei loydy tietokannasta");
            }
        } catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC virheet
            throw e;
        }
        // Päivitetään UPDATEN avulla SQLssä opiskelijan tiedot. Muut tiedot voidaan päivittää paitsi opiskelija_id, joka yksilöi opiskelijan
        sql = "UPDATE  opiskelija "
                + "SET etunimi = ?, sukunimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinumero = ?, email = ?, puhelinnumero = ? "
                + " WHERE opiskelija_id = ?";

        lause = null;
        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);

            // Asetetaan olion arvot UPDATEen SQLssä opiskelijan tiedot.

            lause.setString(1, getEtunimi());
            lause.setString(2, getSukunimi());
            lause.setString(3, getLahiosoite());
            lause.setString(4, getPostitoimipaikka ());
            lause.setString(5, getPostinumero());
            lause.setString(6, getEmail ());
            lause.setString(7, getPuhelinnro ());

            // Asetetaan arvot opiskelija_id:seen
            lause.setInt( 8, getOpiskelija_id());
            // Suoritetaan SQL-lause
            int lkm = lause.executeUpdate();
            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Opiskelijan muuttaminen ei onnistu");
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
     * poistaOpiskelija() -metodi poistaa opiskelijan tiedot opiskelija_ID:n perusteella.
     *
     * Tässä metodissa otetaan myös yhteystietokantaan.
     * @return 0.
     *
     * Välitetään parametrina tietokantayhteys tietokantaan.
     * @throws SQLException
     * @throws Exception
     *
     */
    public int poistaOpiskelija (Connection connection) throws SQLException, Exception {
        // Tietokannasta haetaan opiskelijan tiedot opiskelija_id:n perusteella ja poistetaan ne tietokannasta DELETEn avulla.
        String sql = "DELETE FROM  opiskelija WHERE opiskelija_id = ?";
        PreparedStatement lause = null;

        try {
            // Tällä luodaan PreparedStatement-olio SQL-lauseelle
            lause = connection.prepareStatement(sql);
            // Asetetaan olion arvot DELETEen.
            lause.setInt( 1, getOpiskelija_id());
            // Suoritetaan SQL-lause
            int lkm = lause.executeUpdate();
            if (lkm == 0) {
                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                throw new Exception("Opiskelijan poistaminen ei onnistu");
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
