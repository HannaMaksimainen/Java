/**Harjoitustyön paketti
 *
 */
package sample;
/**
 * Otetaan mukaan kirjastot, jotka sisältävät tarvittavat valmiit
 * luokat.
 *
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

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
 * Paaohjelma, joka toimii opiskelijoiden kurssisuoritusten hallintasovelluksena.
 * Tässä testataan luokkien Opiskelija, Kurssi ja Suoritus toimintaa sekä opiskelija, kurssin ja suoritusten
 * tietojen hakua, lisäystä, muutosta ja poistoa sekä suoritusten listausta.
 *
 */
public class KayttoliittymaGUI extends Application {

    /**
     * Luodaan luokista Opiskelija, Kurssi ja Suoritus oliot:
     * private Opiskelija opiskelija, Luokan Opiskelijan, opiskelija olio.
     * private Kurssi kurssi, Luokan Kurssi, kurssi olio.
     * private Suoritus suoritus, Luokan Suoritus, suoritus olio.
     * private Connection connection, Luodaan Connection connection avulla yhteys tietokantaan.
     *
     */
    private Opiskelija opiskelija = new Opiskelija();
    private Kurssi kurssi = new Kurssi();
    private Suoritus suoritus = new Suoritus();
    private Connection connection;

    /**
     * Luodaan labelit eli ns. otsikot opiskelijan tiedoille:
     * protected Label lblOpiskelijaID, jolla asetetaan opiskelijan Opiskelijanumero,
     * protected Label lblEtunimi, jolla asetetaan opiskelijan Etunimi,
     * protected Label lblSukunimi, jolla asetetaan opiskelijan Sukunimi,
     * protected Label lblLahiosoite, jolla asetetaan opiskelijan Lähiosoite,
     * protected Label lblPostiNro, jolla asetetaan opiskelijan Postinumero,
     * protected Label lblPostitoimipaikka, jolla asetetaan opiskelijan Postitoimipaikka,
     * protected Label lblEmail, jolla asetetaan opiskelijan Email,
     * protected Label lblPuhelin, jolla asetetaan opiskelijan Puhelin.
     *
     */
    protected Label lblOpiskelijaID = new Label("Opiskelijanumero");
    protected Label lblEtunimi = new Label("Etunimi");
    protected Label lblSukunimi = new Label("Sukunimi");
    protected Label lblLahiosoite = new Label("Lähiosoite");
    protected Label lblPostiNro = new Label("Postinumero");
    protected Label lblPostitoimipaikka = new Label("Postitoimipaikka");
    protected Label lblEmail = new Label("Email");
    protected Label lblPuhelin = new Label("Puhelin");

    /**
     * Luodaan TextFieldit opiskelijan tiedoille:
     * protected TextField txtOpiskelijaID, tekstikenttä opiskelijanIDlle,
     * protected TextField txtEtunimi, tekstikenttä opiskelijan etunimelle,
     * protected TextField txtSukunimi, tekstikenttä opiskelijan sukunimelle,
     * protected TextField txtOsoite, tekstikenttä opiskelijan osoitteelle,
     * protected TextField txtPostinro, tekstikenttä opiskelijan postinumerolla,
     * protected TextField txtPostitoimipaikka, tekstikenttä opiskelijan postitoimipaikalle,
     * protected TextField txtEmail, tekstikenttä opiskelijan emailille,
     * protected TextField txtPuhelin, tekstikenttä opiskelijan puhelinnumerolle,
     *
     */
    protected TextField txtOpiskelijaID = new TextField();
    protected TextField txtEtunimi = new TextField();
    protected TextField txtSukunimi = new TextField();
    protected TextField txtOsoite = new TextField();
    protected TextField txtPostinro = new TextField();
    protected TextField txtPostitoimipaikka = new TextField();
    protected TextField txtEmail = new TextField();
    protected TextField txtPuhelin = new TextField();

    /** Luodaan TableView eli laatikko, joka koostuu TableColumneista eli sarakkeista, johon syötetään
     * halutut tiedot näkyviin.
     * TableView näyttää sarakkeiden tiedot.
     * protected TableView<Suoritus> tbwSuoritukset, suoritusten TableView.
     *
     * TableCloumnit ovat opiskelija, kurssi,arvosana ja päivämäärä tiedot:
     * protected TableColumn tcCol1, annetaan teksti "Opiskelija", ensimmäinen sarake.
     * protected TableColumn tcCol2, annetaan teksti "Kurssi", toinen sarake.
     * protected TableColumn tcCol3, annetaan teksti "Arvosana", kolmas sarake.
     * protected TableColumn tcCol4, annetaan teksti "Paivamaara", neljäs sarake.
     *
     */
    protected TableView<Suoritus> tbwSuoritukset = new TableView<Suoritus>();
    protected TableColumn<Suoritus, Integer> tcCol1 = new TableColumn<>("Opiskelija");
    protected TableColumn<Suoritus, Integer> tcCol2 = new TableColumn<>("Kurssi");
    protected TableColumn<Suoritus, String> tcCol3 = new TableColumn<>("Arvosana");
    protected TableColumn<Suoritus, String> tcCol4 = new TableColumn<>("Paivamaara");

    /**
     *Luodaan Buttonit eli napit hae opiskelija, muuta opiskelija, lisää opiskelija ja poista opiskelija.
     * protected Button btnHaeOpiskelija, nappia painamalla, nappi hakee opiskelijan tiedot,
     * protected Button btnMuutaOpiskelija, nappia painamalla, nappi muuttaa opiskelijan tiedot,
     * protected Button btnLisaaOpiskelija, nappia painamalla, nappi lisää opiskelijan tiedot,
     * protected Button btnPoistaOpiskelija, nappia painamalla, nappi poistaa opiskelijan tiedot.
     *
     */
    protected Button btnHaeOpiskelija = new Button("Hae opiskelija");
    protected Button btnMuutaOpiskelija = new Button("Muuta opiskelijan tietoja");
    protected Button btnLisaaOpiskelija = new Button("Lisää opiskelija");
    protected Button btnPoistaOpiskelija = new Button("Poista opiskelija");

    /**
     * Luodaan labelit eli ns. otsikot kurssin tiedoille:
     * protected Label lblKurssiID, jolla asetetaan Kurssin ID,
     * protected Label lblKurssinNimi, jolla asetetaan Kurssin nimi,
     * protected Label lblOpintopisteet, jolla asetetaan Kurssin opintopisteet,
     * protected Label lblKurssinKuvaus, jolla asetetaan Kurssin kuvaus.
     *
     */
    protected Label lblKurssiID = new Label("Kurssin ID");
    protected Label lblKurssinNimi = new Label("Kurssin nimi");
    protected Label lblOpintopisteet = new Label("Opintopisteet");
    protected Label lblKurssinKuvaus = new Label("Kurssin kuvaus");

    /**
     * Luodaan TextFieldit kurssin tiedoille:
     * protected TextField txtKurssiID, tekstikenttä kurssinIDlle,
     * protected TextField txtKurssinNimi, tekstikenttä kurssin nimelle,
     * protected TextField txtOpintopisteet, tekstikenttä kurssin opintopisteille,
     * protected TextField txtKurssinKuvaus, tekstikenttä kurssin kuvaukselle.
     *
     */
    protected TextField txtKurssiID = new TextField();
    protected TextField txtKurssinNimi = new TextField();
    protected TextField txtOpintopisteet = new TextField();
    protected TextField txtKurssinKuvaus = new TextField();

    /**
     *Luodaan Buttonit eli napit Hae kurssi, Muuta Kurssi, Lisaa Kurssi ja Poista Kurssi.
     * protected Button btnHaeKurssi, nappi hakee kurssin tiedot,
     * protected Button btnMuutaKurssi, nappi muuttaa kurssin tiedot,
     * protected Button btnLisaaKurssi, nappi lisää kurssin tiedot,
     * protected Button btnPoistaKurssi, nappi poistaa kurssin tiedot.
     *
     */
    protected Button btnHaeKurssi = new Button("Hae kurssi");
    protected Button btnMuutaKurssi = new Button("Muuta kurssi");
    protected Button btnLisaaKurssi = new Button("Lisää kurssi");
    protected Button btnPoistaKurssi = new Button("Poista kurssi");

    /**
     * Luodaan labelit eli ns. otsikot, suoritusten tiedoille
     * protected Label lblOpiskelijaNro, jolla asetetaan opiskelijan ID,
     * protected Label lblKurssinNro, jolla asetetaan Kurssin nimi,
     * protected Label lblarvonsana, jolla asetetaan Kurssin arvosana,
     * protected Label lblsuoritus_pvm, jolla asetetaan Kurssin suoritus päivämäärä.
     *
     */
    protected Label lblOpiskelijaNro = new Label("Opiskelijanumero");
    protected Label lblKurssinNro = new Label("Kurssin ID");
    protected Label lblarvonsana = new Label("Arvosana");
    protected Label lblsuoritus_pvm = new Label("Suoritus päivämäärä " + '\n' + " (Esim. 2022.03.30)");

    /**
     * Luodaan TextFieldit suoritusten tiedoille:
     * protected TextField txtOpiskelijaNro, tekstikenttä opiskelijanIDlle,
     * protected TextField txtKurssinNro, tekstikenttä kurssinIDlle,
     * protected TextField txtarvonsana, tekstikenttä kurssin arvosanalle,
     * protected TextField txtsuoritus_pvm, tekstikenttä kurssin suorituspäivämäärälle.
     *
     */
    protected TextField txtOpiskelijaNro = new TextField();
    protected TextField txtKurssinNro = new TextField();
    protected TextField txtarvonsana = new TextField();
    protected TextField txtsuoritus_pvm = new TextField();

    /**
     *Luodaan Buttonit eli napit Hae kurssin suoritukselle, Muuta Kurssin suoritusta, Lisaa Kurssin suoritus ja Poista Kurssin suoritus.
     * protected Button btnHaeSuoritusOpiskelijaID, joka hakee kurssin suorituksen opiskelija ID:lla,
     * protected Button btnHaeSuoritusKurssiID, joka hakee kurssin suorituksen kurssin ID:lla,
     * protected Button btnMuutaSuoritus, joka muuttaa kurssin suorituksen,
     * protected Button btnLisaaSuoritus, joka lisää kurssin suorituksen,
     * protected Button btnPoistaSuoritus, joka poistaa kurssin suorituksen.
     *
     */
    protected Button btnHaeSuoritusOpiskelijaID = new Button("Hae suoritus opiskelijan ID:llä");
    protected Button btnHaeSuoritusKurssiID = new Button("Hae suoritus kurssin ID:llä");
    protected Button btnMuutaSuoritus = new Button("Muuta suoritus");
    protected Button btnLisaaSuoritus = new Button("Lisää suoritus");
    protected Button btnPoistaSuoritus = new Button("Poista suoritus");

    /**
     * Luodaan BorderPane paneeliKaikki() -metodi, johon asetetaan kaikki paneeliin osat näkyviin.
     * @return paneeli
     *
     */
    protected BorderPane paneeliKaikki() {

        /**
         * Luodaan BorderPane paneeli, johon luodaan osiot ylä, vasen, keski, oikea ja ala,
         * jotta saadaan jaettua BorderPane osioihin ja osioihin halutut komponentit.
         * paneYla, johon asetetaan vbox komponentti.
         * vboxVasen, johon asetetaan vbox komponentti.
         * vboxKeski, johon asetetaan vbox komponentti.
         * vboxOikea, johon asetetaan vbox komponentti.
         * hboxAla, johon asetetaan hbox komponentti.
         *
         */
        BorderPane paneeli = new BorderPane();
        VBox vboxYla = new VBox();
        VBox vboxVasen = new VBox();
        VBox vboxKeski = new VBox();
        VBox vboxOikea = new VBox();
        HBox hboxAla = new HBox();

        /**
         * Asetetaan paneeliin taustaväri "Lavender", joka tuo ilmettä käyttöliittymään.
         *
         */
        paneeli.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));

        /**
         * Aseteteaan komponentit paneelin eri osioihin:
         * setLeft, johon asetetaan vboxVasen.
         * setRight, johon asetetaan vboxKeski.
         * setTop, johon asetetaan vboxOikea.
         * setBottom, johon asetetaan hboxAla.
         *
         */
        paneeli.setLeft(vboxVasen);
        paneeli.setCenter(vboxKeski);
        paneeli.setRight(vboxOikea);
        paneeli.setTop(vboxYla);
        paneeli.setBottom(hboxAla);

        /**
         * Luodaan Label lblOtsikko, johon asetetaan otsikkotieto työlle sekä
         * muutetaan fontti Calibriksi ja fontin kooksi 30. setTranslateYlla asetetaan
         * tekstin kohta keskelle.
         *
         */
        Label lblOtsikko = new Label ("Opiskelijoiden kurssisuoritusten hallintasovellus");
        lblOtsikko.setFont(new Font("Calibri", 30));
        lblOtsikko.setTranslateY(50);

        /**
         * Lisätään getChildrenin avulla otsikko vboxYla osioon näkyviin.
         *
         */
        vboxYla.getChildren().addAll(lblOtsikko);

        /**
         * Listätään getChildrenin avulla kaikki Label-ja teksti kentät sekä buttonit VboxVasen -osioon näkyviin:
         * lblOpiskelijaID, txtOpiskelijaID, lblEtunimi, txtEtunimi, lblSukunimi, txtSukunimi, lblLahiosoite,
         * txtOsoite, lblPostiNro, txtPostinro, lblPostitoimipaikka, txtPostitoimipaikka,
         * lblEmail, txtEmail, lblPuhelin, txtPuhelin, btnHaeOpiskelija, btnMuutaOpiskelija, btnLisaaOpiskelija, btnPoistaOpiskelija.
         *
         */
        vboxVasen.getChildren().addAll(lblOpiskelijaID, txtOpiskelijaID, lblEtunimi, txtEtunimi, lblSukunimi, txtSukunimi, lblLahiosoite,
                txtOsoite, lblPostiNro, txtPostinro, lblPostitoimipaikka, txtPostitoimipaikka, lblEmail, txtEmail, lblPuhelin, txtPuhelin, btnHaeOpiskelija, btnMuutaOpiskelija, btnLisaaOpiskelija, btnPoistaOpiskelija);

        /**
         * Listätään getChildrenin avulla kaikki Label-ja teksti kentät sekä buttonit vboxKeski -osioon näkyviin:
         * lblKurssiID, txtKurssiID, lblKurssinNimi, txtKurssinNimi, lblOpintopisteet, txtOpintopisteet,
         * lblKurssinKuvaus, txtKurssinKuvaus, btnHaeKurssi, btnMuutaKurssi, btnLisaaKurssi, btnPoistaKurssi.
         *
         */
        vboxKeski.getChildren().addAll(lblKurssiID, txtKurssiID, lblKurssinNimi, txtKurssinNimi, lblOpintopisteet, txtOpintopisteet,
                lblKurssinKuvaus, txtKurssinKuvaus, btnHaeKurssi, btnMuutaKurssi, btnLisaaKurssi, btnPoistaKurssi);

        /**
         * Listätään getChildrenin avulla kaikki Label-ja teksti kentät sekä buttonit vboxOikea -osioon näkyviin:
         * lblOpiskelijaNro, txtOpiskelijaNro, lblKurssinNro, txtKurssinNro, lblarvonsana, txtarvonsana,
         * lblsuoritus_pvm, txtsuoritus_pvm, btnHaeKurssiSuoritus, btnHaeSuoritusID,
         * btnMuutaSuoritus, btnLisaaSuoritus, btnPoistaSuoritus.
         *
         */
        vboxOikea.getChildren().addAll(lblOpiskelijaNro, txtOpiskelijaNro, lblKurssinNro, txtKurssinNro, lblarvonsana, txtarvonsana,
                lblsuoritus_pvm, txtsuoritus_pvm,  btnHaeSuoritusOpiskelijaID, btnHaeSuoritusKurssiID, btnMuutaSuoritus, btnLisaaSuoritus, btnPoistaSuoritus);

        /**
         * Listätään getChildrenin avulla tbwSuoritukset hboxAla -osioon näkyviin.
         *
         */
        hboxAla.getChildren().addAll(tbwSuoritukset);

        /**
         * Asetetaan vboxYla, vboxVasen, vboxKeski, vboxOikea, hboxAla sijainniltaan
         * keskelle ylös, paitsi vboxYla vain ylös, jotta asettelu on selkeämpi.
         *
         */
        vboxYla.setAlignment(Pos.CENTER);
        vboxVasen.setAlignment(Pos.TOP_CENTER);
        vboxKeski.setAlignment(Pos.TOP_CENTER);
        vboxOikea.setAlignment(Pos.TOP_CENTER);
        hboxAla.setAlignment(Pos.TOP_CENTER);

        vboxVasen.setPrefWidth(300);
        vboxVasen.setPrefHeight(600);
        vboxKeski.setPrefWidth(300);
        vboxKeski.setPrefHeight(600);
        vboxOikea.setPrefWidth(300);
        vboxOikea.setPrefHeight(600);

        /**
         * Asetetaan marginaalit lblOpiskelijaID labelille ja txtOpiskelijaID tekstikentälle.
         * Marginaalit lblOpiskelijaID:
         * Ylhäällä: 100, oikealla 5, alhaalla 5, vasemmalla 2.
         * Marginaalit txtOpiskelijaID:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblOpiskelijaID, new Insets(100, 5, 5, 5));
        vboxVasen.setMargin(txtOpiskelijaID, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblEtunimi labelille ja txtEtunimi tekstikentälle.
         * Marginaalit lblEtunimi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtEtunimi:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblEtunimi, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtEtunimi, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblSukunimi labelille ja txtSukunimi tekstikentälle.
         * Marginaalit lblSukunimi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtSukunimi:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblSukunimi, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtSukunimi, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblLahiosoite labelille ja txtOsoite tekstikentälle.
         * Marginaalit lblLahiosoite:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtOsoite:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblLahiosoite, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtOsoite, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblPostiNro labelille ja txtPostinro tekstikentälle.
         * Marginaalit lblPostiNro:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtPostinro:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblPostiNro, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtPostinro, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblPostitoimipaikka labelille ja txtPostitoimipaikka tekstikentälle.
         * Marginaalit lblPostitoimipaikka:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtPostitoimipaikka:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblPostitoimipaikka, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtPostitoimipaikka, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblEmail labelille ja txtEmail tekstikentälle.
         * Marginaalit lblEmail:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtEmail:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblEmail, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtEmail, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblPuhelin labelille ja txtPuhelin tekstikentälle.
         * Marginaalit lblPuhelin:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtPuhelin:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(lblPuhelin, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtPuhelin, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit buttoneille btnHaeOpiskelija, btnMuutaOpiskelija, btnLisaaOpiskelija, btnPoistaOpiskelija.
         * Marginaalit btnHaeOpiskelija:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnMuutaOpiskelija:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnLisaaOpiskelija:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnPoistaOpiskelija:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         *
         */
        vboxVasen.setMargin(btnHaeOpiskelija, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(btnMuutaOpiskelija, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(btnLisaaOpiskelija, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(btnPoistaOpiskelija, new Insets(5, 5, 5, 5));


        /**
         * Asetetaan marginaalit lblKurssiID labelille ja txtKurssiID tekstikentälle.
         * Marginaalit lblKurssiID:
         * Ylhäällä: 100, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtKurssiID:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxKeski.setMargin(lblKurssiID, new Insets(100, 5, 5, 5));
        vboxVasen.setMargin(txtKurssiID, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblKurssinNimi labelille ja txtKurssinNimi tekstikentälle.
         * Marginaalit lblKurssinNimi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtKurssinNimi:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxKeski.setMargin(lblKurssinNimi, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtKurssinNimi, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblOpintopisteet labelille ja txtOpintopisteet tekstikentälle.
         * Marginaalit lblOpintopisteet:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtOpintopisteet:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxKeski.setMargin(lblOpintopisteet, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtOpintopisteet, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblKurssinKuvaus labelille ja txtKurssinKuvaus tekstikentälle.
         * Marginaalit lblKurssinKuvaus:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtKurssinKuvaus:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxKeski.setMargin(lblKurssinKuvaus, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(txtKurssinKuvaus, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit buttoneille btnHaeKurssi, btnMuutaKurssi, btnLisaaKurssi, btnPoistaKurssi.
         * Marginaalit btnHaeKurssi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnMuutaKurssi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnLisaaKurssi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnPoistaKurssi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         *
         */
        vboxKeski.setMargin(btnHaeKurssi, new Insets(15, 5, 5, 5));
        vboxVasen.setMargin(btnMuutaKurssi, new Insets(5, 5, 5, 5));
        vboxKeski.setMargin(btnLisaaKurssi, new Insets(5, 5, 5, 5));
        vboxVasen.setMargin(btnPoistaKurssi, new Insets(5, 5, 5, 5));


        /**
         * Asetetaan marginaalit lblOpiskelijaNro labelille ja txtOpiskelijaNro tekstikentälle.
         * Marginaalit lblOpiskelijaNro:
         * Ylhäällä: 100, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtOpiskelijaNro:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxOikea.setMargin(lblOpiskelijaNro, new Insets(100, 5, 5, 5));
        vboxOikea.setMargin(txtOpiskelijaNro, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblKurssinNro labelille ja txtKurssinNro tekstikentälle.
         * Marginaalit lblKurssinNro:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtKurssinNro:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxOikea.setMargin(lblKurssinNro, new Insets(5, 5, 5, 5));
        vboxOikea.setMargin(txtKurssinNro, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblarvonsana labelille ja txtarvonsana tekstikentälle.
         * Marginaalit lblarvonsana:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtarvonsana:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxOikea.setMargin(lblarvonsana, new Insets(5, 5, 5, 5));
        vboxOikea.setMargin(txtarvonsana, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit lblsuoritus_pvm labelille ja txtsuoritus_pvm tekstikentälle.
         * Marginaalit lblsuoritus_pvm:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit txtsuoritus_pvm:
         * Ylhäällä: 2, oikealla 5, alhaalla 2, vasemmalla 5.
         *
         */
        vboxOikea.setMargin(lblsuoritus_pvm, new Insets(5, 5, 5, 5));
        vboxOikea.setMargin(txtsuoritus_pvm, new Insets(2, 5, 2, 5));

        /**
         * Asetetaan marginaalit buttoneille btnHaeSuoritusOpiskelijaID, btnHaeSuoritusOpiskelijaID, btnMuutaSuoritus, btnLisaaSuoritus, btnPoistaKurssi.
         * Marginaalit btnHaeSuoritusOpiskelijaID:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnHaeSuoritusOpiskelijaID:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnMuutaSuoritus:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnLisaaSuoritus:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         * Marginaalit btnPoistaKurssi:
         * Ylhäällä: 5, oikealla 5, alhaalla 5, vasemmalla 5.
         *
         */
        vboxOikea.setMargin(btnHaeSuoritusOpiskelijaID, new Insets(15, 5, 5, 5));
        vboxOikea.setMargin(btnHaeSuoritusOpiskelijaID, new Insets(5, 5, 5, 5));
        vboxOikea.setMargin(btnMuutaSuoritus, new Insets(5, 5, 5, 5));
        vboxOikea.setMargin(btnLisaaSuoritus, new Insets(5, 5, 5, 5));
        vboxOikea.setMargin(btnPoistaSuoritus, new Insets(5, 5, 5, 5));

        /**
         * Asetetaan opiskelija_id, kurssi_id, arvosana, suoritus_pvm tableViewin columnien "arvoiksi":
         * tcCol1, asetetaan tälle tieto opiskelija_idsta,
         * tcCol2, asetetaan tälle tieto kurssi_idsta,
         * tcCol3, asetetaan tälle tieto arvosanasta,
         * tcCol4, asetetaan tälle tieto suoritus_pvmsta.
         *
         */
        tcCol1.setCellValueFactory(new PropertyValueFactory<Suoritus, Integer>("opiskelija_id"));
        tcCol2.setCellValueFactory(new PropertyValueFactory<Suoritus, Integer>("kurssi_id"));
        tcCol3.setCellValueFactory(new PropertyValueFactory<Suoritus, String>("arvosana"));
        tcCol4.setCellValueFactory(new PropertyValueFactory<Suoritus, String>("suoritus_pvm"));

        /**
         * Annetaan tableViewillw koko (korkeus ja leveys) ja asetetaan kolumnit
         * TableViewiin.
         *
         */
        tbwSuoritukset.setMaxWidth(500);
        tbwSuoritukset.setMaxHeight(300);
        tbwSuoritukset.getColumns().add(tcCol1);
        tbwSuoritukset.getColumns().add(tcCol2);
        tbwSuoritukset.getColumns().add(tcCol3);
        tbwSuoritukset.getColumns().add(tcCol4);

        /**
         * Luodaan tapahtumankuuntelijat ja otetaan yhteys metodeihin.
         *
         */

        /**
         * Lisätään tapahtumankuuntelija btnHaeOpiskelija -napille.
         * Nappi ottaa yhteyden metodista haeOpiskelijanTiedot().
         *
         */
        btnHaeOpiskelija.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        haeOpiskelijanTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnMuutaOpiskelija -napille.
         * Nappi ottaa yhteyden metodista muutaOpiskelijanTiedot().
         *
         */
        btnMuutaOpiskelija.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        muutaOpiskelijanTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnPoistaOpiskelija -napille.
         * Nappi ottaa yhteyden metodista poistaOpiskelijanTiedot().
         *
         */
        btnPoistaOpiskelija.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        poistaOpiskelijanTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnLisaaOpiskelija -napille.
         * Nappi ottaa yhteyden metodista lisaaOpiskelijanTiedot().
         *
         */
        btnLisaaOpiskelija.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        lisaaOpiskelijanTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnHaeKurssi -napille.
         * Nappi ottaa yhteyden metodista haeKurssinTiedot().
         *
         */
        btnHaeKurssi.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        haeKurssinTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnMuutaKurssi -napille.
         * Nappi ottaa yhteyden metodista muutaKurssinTiedot().
         *
         */
        btnMuutaKurssi.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        muutaKurssinTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnLisaaKurssi -napille.
         * Nappi ottaa yhteyden metodista lisaaKurssinTiedot().
         *
         */
        btnLisaaKurssi.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        lisaaKurssinTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnPoistaKurssi -napille.
         * Nappi ottaa yhteyden metodista poistaKurssinTiedot().
         *
         */
        btnPoistaKurssi.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        poistaKurssinTiedot();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnHaeSuoritusOpiskelijaID -napille.
         * Nappi ottaa yhteyden metodista hae_tiedotOpiskelijaID().
         *
         */
        btnHaeSuoritusOpiskelijaID.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        hae_tiedotOpiskelijaID();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnHaeSuoritusKurssiID -napille.
         * Nappi ottaa yhteyden metodista haeOpiskelijanKurssinID().
         *
         */
        btnHaeSuoritusKurssiID.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        haeOpiskelijanKurssinID();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnMuutaSuoritus -napille.
         * Nappi ottaa yhteyden metodista muutaOpiskelijanSuoritus().
         *
         */
        btnMuutaSuoritus.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        muutaOpiskelijanSuoritus();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnLisaaSuoritus -napille.
         * Nappi ottaa yhteyden metodista lisaaOpiskelijanSuoritus().
         *
         */
        btnLisaaSuoritus.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        lisaaOpiskelijanSuoritus();
                    }
                }
        );

        /**
         * Lisätään tapahtumankuuntelija btnPoistaSuoritus -napille.
         * Nappi ottaa yhteyden metodista poistaOpiskelijanSuoritus().
         *
         */
        btnPoistaSuoritus.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        poistaOpiskelijanSuoritus();
                    }
                }
        );

        return paneeli;
    }


    /**
     * start -metodissa asetetaan Scenelle scenen avulla kaikki metodin paneeliKaikki() tiedot.
     * Asetetaan myös paneelin koko ja otsikko tässä sekä primaryStagelle näkyviin.
     *
     * Tässä metodissa myös luodaan yhteys azuressa olevaan tietokantaan, luodaan tietokannan
     * taulut (opiskelija, kurssi, suoritus) ja asetetaan tiedot kantaan.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Paneelin koko 1 000 x 900 ja otsikko.
        Scene scene = new Scene(paneeliKaikki(), 1000, 900);
        primaryStage.setTitle("Opintosuoritusten hallinta");

        // Asetetaan kaikki näkyviin primaryStage.
        primaryStage.setScene(scene);
        primaryStage.show();

        //Otetaan yhteys Azuren kantaan ja määritellään tietokannan nimi.
        Connection conn = openConnection(
                "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");

        //Tietokannan tunnus
        createDatabase(conn, "Opintosuoritustenhallinta_2108401");

        // Luodaan taulu opiskelija ja asetetaan sinne tiedot ja arvot.
        createTable(conn,
                "CREATE TABLE opiskelija ("
                        + "opiskelija_id INT,"
                        + "etunimi VARCHAR(50),"
                        + "sukunimi VARCHAR(50),"
                        + "lahiosoite VARCHAR(50),"
                        + "postitoimipaikka VARCHAR(50),"
                        + "postinumero CHAR(50),"
                        + "email VARCHAR(50),"
                        + "puhelinnumero VARCHAR(50))"
        );

        // Luodaan taulu kurssi ja asetetaan sinne tiedot ja arvot.
        createTable(conn,
                "CREATE TABLE kurssi ("
                        + "kurssi_id INT,"
                        + "nimi VARCHAR(50),"
                        + "opintopisteet INT,"
                        + "kuvaus VARCHAR(50))"
        );

        // Luodaan taulu suoritus ja asetetaan sinne tiedot ja arvot.
        createTable(conn,
                "CREATE TABLE suoritus ("
                        + "opiskelija_id INT,"
                        + "kurssi_id INT,"
                        + "arvosana VARCHAR(50),"
                        + "suoritus_pvm DATETIME)"
        );

        // Lisätään testiopiskelijoiden tiedot.
        addOpiskelija(conn, 1, "Hanna", "Maksimainen", "Koodikaari 2", "Helsinki", 80130, "maksih@gmail.com", "123456789");
        addOpiskelija(conn, 2, "Pertti", "Korhonen", "Koulukatu 5", "Joensuu", 80100, "pertti.korhonen@gmail.com", "987654321");
        addOpiskelija(conn, 3, "Mira", "Mattila", "Kastetie 59", "Sipoo", 95264, "MiraM@gmail.com", "963258741");

        // Lisätään testikurssien tiedot.
        addKurssi(conn, 1, "Ohjelmoinnin perusteet", "5", "Opiskellaan Javan perusteita");
        addKurssi(conn, 2, "Logiikka", "2", "Logiikan perusteet ja hallinta");
        addKurssi(conn, 3, "Pythonin perusteet", "2", "Opiskellaan Pythonin perusteita.");
        addKurssi(conn, 4, "Ammatillinen kasvu", "2", "Opiskellaan olemaan ja elämään.");

        // Lisätään testisuoritusten tiedot.
        addSuoritus(conn,1, 1, "5", "20211010");
        addSuoritus(conn,1, 4, "Hyvaksytty", "20211103");
        addSuoritus(conn,2, 2, "3", "20211215");
        addSuoritus(conn,2, 3, "2", "20220515");
        addSuoritus(conn,3, 1, "4", "20211010");
        addSuoritus(conn,3, 2, "5", "20220515");

        // Lisätään tiedot Connection con
        opiskelijaTulostus(conn);
        kurssiTulostus(conn);
        suoritusTulostus(conn);

        // Tehdään try-catch rakenne, joka ilmoittaa, jos virhetilanne tapahtuu suorittaessa ohjelmaa.
        try {
            yhdista ();
        } catch (SQLException se) {
            // Tulostetaan SQL virhe
            System.out.println("Tapahtui tietokantavirhe tietokantaa avattaessa.");
        } catch (Exception e) {
            // Tulostetaan JDBC virhe
            System.out.println("Tapahtui JDBCvirhe tietokantaa avattaessa.");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * yhdista() -metodissa luodaan yhteys tietokantaan ja ilmoitetaan, jos
     * yhdistäminen tietokantaan ei onnistu eli annetaan virhe.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void yhdista() throws SQLException, Exception {
        connection = null;
        // Vaadittu Azuren tietokanta
        String url = "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306/Opintosuoritustenhallinta_2108401?user=opiskelija&password=opiskelija1";

        try {
            connection=DriverManager.getConnection(url);
            // Jos kaikki ok, ilmoitetaan käyttäjälle, että yhteys on luotu
            System.out.println("Yhteys tietokantaan luotu.");
        }

        // Annetaan virhe jos tietokantaan ei saada yhteyttä.
        catch (SQLException e) {
            connection = null;
            throw e;
        }
        catch (Exception e ) {
            throw e;
        }

    }

    /**
     * openConnection() -metodissa luodaan avataan tietokantaan.
     *
     * @throws SQLException
     * @return connection
     *
     */
    private static Connection openConnection(String connString) throws SQLException {
        //Javassa yhteyden avaus
        Connection connection = DriverManager.getConnection(connString);

        // Ilmoitetaan käyttäjälle, että yhteys on ok
        System.out.println("\t>> Yhteys ok");

        return connection;
    }

    /**
     * closeConnection() -metodissa suljetaan yhteys tietokantaan.
     *
     * @throws SQLException
     *
     */
    private static void closeConnection(Connection c) throws SQLException {
        //virhe nousee, jos ei onnistu sulkemaan yhteyttä
        if(c!= null){
            c.close();
        }
        // Tulostetaan käyttäjälle tieto yhteyden sulkemisesta
        System.out.println("\t>> Tietokantayhteys suljettu");
    }

    /**
     * createDatabase() -metodissa tuhotaan entinen tietokanta ja käytetään
     * uutta luotua tietokantaa.
     *
     * @throws SQLException
     *
     */
    private static void createDatabase(Connection c, String db) throws SQLException{
        Statement stmt = c.createStatement();

        // Pudotetaan/ poistetaan vanha tietokanta
        stmt.executeQuery("DROP DATABASE IF EXISTS " + db);

        // Tietokannan tuhoaminen
        System.out.println("\t>> Tietokanta " + db + " tuhottu");

        // Luodaan uusi tietokanta
        stmt.executeQuery("CREATE DATABASE " + db);
        System.out.println("\t>> Tietokanta " + db + " luotu");

        // Käytetään uutta tietokantaa
        stmt.executeQuery("USE " + db);
        System.out.println("\t>> Käytetään tietokantaa " + db);

    }

    /**
     * createTable() -metodissa luodaan taulut.
     *
     * @throws SQLException
     *
     */
    private static void createTable(Connection c, String sql) throws SQLException {
        // Luodaan statement
        Statement stmt = c.createStatement();
        stmt.executeQuery(sql);

        //Ilmoitetaan taulujen luonnista käyttäjälle
        System.out.println("\t>> Taulu luotu");

    }

    /**
     * opiskelijaTulostus() -metodissa tulostetaan mitä on lisätty tietokantaan.
     *
     * @throws SQLException
     *
     */
    private static void opiskelijaTulostus(Connection c) throws SQLException{
        // Luodaan statement
        Statement stmt = c.createStatement();

        // Luodaan resultSet rs, johon asetetaan tiedot
        ResultSet rs = stmt.executeQuery(
                "SELECT opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinumero, email, puhelinnumero FROM opiskelija"
        );

        // Tulostetaan konsoliin tiedot
       // System.out.println("\nOpiskelijatesti:\n=============");

       /* while(rs.next()){
            System.out.println(
                    "[" + rs.getInt("opiskelija_id") + "]"
                            + rs.getString("etunimi") + ": "
                            + rs.getString("sukunimi") + ": "
                            + rs.getString("lahiosoite") + ": "
                            + rs.getInt("postinumero")
                            + rs.getString("email") + ": "
                            + rs.getString("puhelinnumero")
            );
        }*/
    }

    /**
     * kurssiTulostus() -metodissa tulostetaan mitä on lisätty tietokantaan.
     *
     * @throws SQLException
     *
     */
    private static void kurssiTulostus(Connection c) throws SQLException{
        // Luodaan statement
        Statement stmt = c.createStatement();
        // Luodaan resultSet rs, johon asetetaan tiedot
        ResultSet rs = stmt.executeQuery(
                "SELECT kurssi_id, nimi, opintopisteet, kuvaus FROM kurssi"
        );

        // Tulostetaan konsoliin tiedot
       // System.out.println("\nKurssitesti:\n=============");

        /*while(rs.next()){
            System.out.println(
                    "[" + rs.getInt("kurssi_id") + "]"
                            + rs.getString("nimi") + ": "
                            + rs.getString("opintopisteet")
                            + rs.getString("kuvaus")
            );
        }*/
    }

    /**
     * suoritusTulostus() -metodissa tulostetaan mitä on lisätty tietokantaan.
     *
     * @throws SQLException
     *
     */
    private static void suoritusTulostus(Connection c) throws SQLException{
        // Luodaan statement
        Statement stmt = c.createStatement();
        // Luodaan resultSet rs, johon asetetaan tiedot
        ResultSet rs = stmt.executeQuery(
                "SELECT opiskelija_id, kurssi_id, arvosana, suoritus_pvm FROM suoritus"
        );

        // Tulostetaan konsoliin tiedot
       // System.out.println("\nSuoritustesti:\n=============");

       /* while(rs.next()){
            System.out.println(
                    "[" + rs.getInt("opiskelija_id") + "]"
                            + rs.getInt("kurssi_id") + ": "
                            + rs.getString("arvosana")
                            + rs.getString("suoritus_pvm")
            );

        }*/
    }

    /**
     * addOpiskelija -metodin avulla lisätään testiopiskelijat tietokantaan. Ilmoitetaan konsoliin, kun
     * tiedot on lisätty.
     *
     * @throws SQLException
     *
     */
    private static void addOpiskelija(Connection c, int opiskelija_id, String etunimi, String sukunimi, String lahiosoite,
                                      String postitoimipaikka, int postinumero, String email, String puhelinnumero) throws SQLException {
        // Luodaan PreparedStatement ps, johon asetetaan INSERT INTO, joka luo kantaan tiedot
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO opiskelija (opiskelija_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinumero, email, puhelinnumero) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
        );

        // Asetetaan ps:lle tiedot opiskelijasta
        ps.setInt(1, opiskelija_id);
        ps.setString(2, etunimi);
        ps.setString(3, sukunimi);
        ps.setString(4, lahiosoite);
        ps.setString(5, postitoimipaikka);
        ps.setInt(6, postinumero);
        ps.setString(7, email);
        ps.setString(8, puhelinnumero);

        ps.execute();
        // Tulostus käyttäjälle
        System.out.println("\t>> Lisätty " + opiskelija_id);

    }

    /**
     * addKurssi -metodin avulla lisätään testikurssit tietokantaan. Ilmoitetaan konsoliin, kun
     * tiedot on lisätty.
     *
     * @throws SQLException
     *
     */
    private static void addKurssi(Connection c, int kurssi_id, String nimi, String opintopisteet, String kuvaus) throws SQLException {
        // Luodaan PreparedStatement ps, johon asetetaan INSERT INTO, joka luo kantaan tiedot
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO kurssi (kurssi_id, nimi, opintopisteet, kuvaus) "
                        + "VALUES(?, ?, ?, ?)"
        );

        // Asetetaan ps:lle tiedot kurssista
        ps.setInt(1, kurssi_id);
        ps.setString(2, nimi);
        ps.setString(3, opintopisteet);
        ps.setString(4, kuvaus);
        ps.execute();
        // Tulostus käyttäjälle
        System.out.println("\t>> Lisätty " + kurssi_id);

    }

    /**
     * addSuoritus -metodin avulla lisätään testisuoritukset tietokantaan. Ilmoitetaan konsoliin, kun
     * tiedot on lisätty.
     *
     * @throws SQLException
     *
     */
    private static void addSuoritus(Connection c, int opiskelija_id, int kurssi_id, String arvosana, String suoritus_pvm) throws SQLException {
        // Luodaan PreparedStatement ps, johon asetetaan INSERT INTO, joka luo kantaan tiedot
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO suoritus (opiskelija_id, kurssi_id, arvosana, suoritus_pvm) "
                        + "VALUES(?, ?, ?, ?)"
        );
        // Asetetaan ps:lle tiedot suorituksesta
        ps.setInt(1, opiskelija_id);
        ps.setInt(2, kurssi_id);
        ps.setString(3, arvosana);
        ps.setString(4, suoritus_pvm);
        ps.execute();
        // Tulostus käyttäjälle
        System.out.println("\t>> Lisätty suoritus " + opiskelija_id);

    }

    /**
     * haeOpiskelijanTiedot() -metodin avulla haetaan opiskelijan tiedot ID:n perusteella ja ilmoitetaan,
     * jos opiskelijaa ei löydy tai tulee virhetilanne.
     *
     * @throws Exception
     *
     */
    public void haeOpiskelijanTiedot() {
        // Alustetaan arvot nulliksi
        opiskelija = null;
        ArrayList<Suoritus> lstSuoritukset = null;

        // Yhteys tietokantaan, haetaan opiskelijaID:n perusteella
        try {
            System.out.println(connection);
            opiskelija = Opiskelija.haeOpiskelija(connection, Integer.parseInt(txtOpiskelijaID.getText()));

        } catch (SQLException se) {
            // SQL virheet, jos suoritus ei mene oikein, tulostetaan teksti
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelijaa ei loydy.");
            alert.showAndWait();

        } catch (Exception e) {
            // muut virheet, jos suoritus ei mene oikein, tulostetaan teksti
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelijaa ei loydy.");
            alert.showAndWait();
        }

        if (opiskelija.getEtunimi() == null) {
            // muut virheet
            txtEtunimi.setText("");
            txtSukunimi.setText("");
            txtOsoite.setText("");
            txtPostinro.setText("");
            txtPostitoimipaikka.setText("");
            txtEmail.setText("");
            txtPuhelin.setText("");

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelijaa ei loydy.");
            alert.showAndWait();

        } else {
            txtEtunimi.setText(opiskelija.getEtunimi());
            txtSukunimi.setText(opiskelija.getSukunimi());
            txtOsoite.setText(opiskelija.getLahiosoite());
            txtPostinro.setText(opiskelija.getPostinumero());
            txtPostitoimipaikka.setText(opiskelija.getPostitoimipaikka());
            txtEmail.setText(opiskelija.getEmail());
            txtPuhelin.setText(opiskelija.getPuhelinnro());

            try {
                // Arraylistilta opiskelijan suoritusten haku opiskelijan ID:n perusteella
                lstSuoritukset = haeOpiskelijanSuoritukset(opiskelija.getOpiskelija_id());
            } catch (SQLException se) {
                // SQL virheet
                se.printStackTrace();

            } catch (Exception e) {
                // Muut virheet
                e.printStackTrace();

            }
        }

    }

    /**
     * haeOpiskelijanSuoritukset() -metodin avulla haetaan opiskelijan suoritukset ID:n perusteella ja ilmoitetaan,
     * jos suoritusta ei löydy tai tulee virhetilanne.
     *
     * @throws Exception
     * @return suoritusLista
     *
     */
    public ArrayList<Suoritus> haeOpiskelijanSuoritukset(int id) throws SQLException, Exception {
        // Otetaan tiedot tietokannasta
        String sql = "SELECT opiskelija_id, kurssi_id, arvosana, suoritus_pvm "
                + " FROM suoritus WHERE opiskelija_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;

        try {

            lause = connection.prepareStatement(sql);
            lause.setInt(1, id);
            tulosjoukko = lause.executeQuery();

        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        }

        // Luodaan suorituslista
        ArrayList<Suoritus> suoritusLista = new ArrayList<Suoritus>();

        try {

            while (tulosjoukko.next()) {
                // Luodaan suoritusOlio
                Suoritus suoritusOlio = new Suoritus();

                // Asetetaan arvot suoritusOliolle
                suoritusOlio.setOpiskelija_id(tulosjoukko.getInt("opiskelija_id"));
                suoritusOlio.setKurssi_id(tulosjoukko.getInt("kurssi_id"));
                suoritusOlio.setArvonsana(tulosjoukko.getString("arvosana"));
                java.util.Date suorpvm = tulosjoukko.getDate("suoritus_pvm");
                SimpleDateFormat dmyFormat = new SimpleDateFormat("dd.MM.yyyy");
                String dmy = dmyFormat.format(suorpvm);
                suoritusOlio.setSuoritus_pvm(dmy);
                suoritusLista.add(suoritusOlio);
            }

        } catch (SQLException e) {
            throw e;
        }

        return suoritusLista;
    }

    /**
     * hae_tiedotOpiskelijaID() -metodin avulla haetaan opiskelija tiedot opiskelijan ID:n perusteella ja ilmoitetaan,
     * jos opiskelijaa ei löydy tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void hae_tiedotOpiskelijaID() {
        // Asetetaan arvot nulliksi
        suoritus = null;
        ArrayList <Suoritus> lstSuoritukset = null;

        try {
            // Haetaan suorituksen tiedot opiskelijan ID:n perusteella
            suoritus = Suoritus.haeOpiskelijanID (connection, Integer.parseInt(txtOpiskelijaNro.getText()));

        } catch (SQLException se) {
            // SQL virheet, tulostetaan jos tulee virhetilanne
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelijaa ei loydy.");
            alert.showAndWait();

        } catch (Exception e) {
            // muut virheet, tulostetaan jos tulee virhetilanne
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelijaa ei loydy.");
            alert.showAndWait();

        }
        // IF-ELSE ehto
        if (suoritus.getOpiskelija_id() == 0) {
            // muut virheet
            txtOpiskelijaNro.setText("");
            txtKurssinNro.setText("");
            txtarvonsana.setText("");
            txtsuoritus_pvm.setText("");

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelijaa ei loydy.");
            alert.showAndWait();

        }
        else {

            try {
               // Suoritukset arraylistille, opiskelijan ID:n avulla
                lstSuoritukset = haeOpiskelijanSuoritukset (Integer.parseInt(txtOpiskelijaNro.getText()));

            } catch (SQLException se) {
                // SQL virheet
                se.printStackTrace ();

            } catch (Exception e) {
                // muut virheet
                e.printStackTrace ();

            }

            // Opiskelijan suoritusten läpikäynti ja lisääminen TableViewille
            finally {

                for(Suoritus opiskelijanSuoritus : lstSuoritukset)
                {
                    // Lisätään suoritus TableViewille
                    tbwSuoritukset.getItems().add(opiskelijanSuoritus);
                }
            }
        }
    }

    /**
     * lisaaOpiskelijanTiedot() -metodin avulla lisätään opiskelija ja ilmoitetaan,
     * jos tietojen lisääminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void lisaaOpiskelijanTiedot() {
        // Asetetaan boolean todeksi opiskelijan lisäämisen suhteen
        boolean opiskelija_lisatty = true;

        // Asetaan arvo nulliksi
        opiskelija = null;
        try {
            // hyödynnetään opiskelija ID:tä lisäyksessä
            opiskelija = Opiskelija.haeOpiskelija(connection, Integer.parseInt(txtOpiskelijaID.getText()));
        } catch (SQLException se) {
            // SQL virheet
            opiskelija_lisatty = false;
            se.printStackTrace();

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Opiskelijan tietojen lisääminen ei onnistu.");
            alert.showAndWait();

        } catch (Exception e) {
            // muut virheet
            opiskelija_lisatty = false;
            e.printStackTrace();

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Opiskelijan tietojen lisääminen ei onnistu.");
            alert.showAndWait();
        }

        // Jos opiskelija on jo olemassa, annetaan tiedot käyttäjälle
        if (opiskelija.getEtunimi() != null) {
            opiskelija_lisatty = false;

            txtEtunimi.setText(opiskelija.getEtunimi());
            txtSukunimi.setText(opiskelija.getSukunimi());
            txtOsoite.setText(opiskelija.getLahiosoite());
            txtPostinro.setText(opiskelija.getPostinumero());
            txtPostitoimipaikka.setText(opiskelija.getPostitoimipaikka());
            txtEmail.setText(opiskelija.getEmail());
            txtPuhelin.setText(opiskelija.getPuhelinnro());

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelija on jo olemassa.");
            alert.showAndWait();

        } else {

            opiskelija.setOpiskelija_id(Integer.parseInt(txtOpiskelijaID.getText()));
            opiskelija.setEtunimi(txtEtunimi.getText());
            opiskelija.setSukunimi(txtSukunimi.getText());
            opiskelija.setLahiosoite(txtOsoite.getText());
            opiskelija.setPostinumero(txtPostinro.getText());
            opiskelija.setPostitoimipaikka(txtPostitoimipaikka.getText());
            opiskelija.setEmail(txtEmail.getText());
            opiskelija.setPuhelinnro(txtPuhelin.getText());

            try {
                opiskelija.lisaaOpiskelija(connection);
            } catch (SQLException se) {
                // SQL virheet
                opiskelija_lisatty = false;

                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Opiskelijan tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Opiskelijan tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                se.printStackTrace();
            } catch (Exception e) {
                // muut virheet
                opiskelija_lisatty = false;

                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Opiskelijan tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Opiskelijan tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                // Ilmoitetaan käyttäjälle, että lisäys on ok
                if (opiskelija_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Opiskelijan tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Opiskelijan tiedot lisatty tietokantaan.");
                    alert.showAndWait();

                }
            }
        }
    }

    /**
     * muutaOpiskelijanTiedot() -metodin avulla muutetaan opiskelijan tietoja ja ilmoitetaan,
     * jos tietojen muuttaminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void muutaOpiskelijanTiedot() {
        // Asetetaan boolean todeksi
        boolean opiskelija_muutettu = true;
        opiskelija.setEtunimi(txtEtunimi.getText());
        opiskelija.setSukunimi(txtSukunimi.getText());
        opiskelija.setLahiosoite(txtOsoite.getText());
        opiskelija.setPostinumero(txtPostinro.getText());
        opiskelija.setPostitoimipaikka(txtPostitoimipaikka.getText());
        opiskelija.setEmail(txtEmail.getText());
        opiskelija.setPuhelinnro(txtPuhelin.getText());

        try {
            opiskelija.muutaOpiskelija(connection);
        } catch (SQLException se) {
            // SQL virheet
            opiskelija_muutettu = false;

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Opiskelijan tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet
            opiskelija_muutettu = false;

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Opiskelijan tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            // Jos tietojen muuttaminen on ok, ilmoitetaan käyttäjälle
            if (opiskelija_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opiskelijan tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Opiskelijan tiedot muutettu.");
                alert.showAndWait();
            }
        }
    }

    /**
     * poistaOpiskelijanTiedot() -metodin avulla poistetaan opiskelijan tiedot ja ilmoitetaan,
     * jos tietojen poistaminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void poistaOpiskelijanTiedot() {
        // Asetetaan arvo nulliksi
        opiskelija = null;

        // Asetetaan boolean falseksi
        boolean opiskelija_poistettu = false;

        try {
            // Tiedot on mahdollista poistaa opiskelijaID:n avulla
            opiskelija = Opiskelija.haeOpiskelija(connection, Integer.parseInt(txtOpiskelijaID.getText()));
        } catch (SQLException se) {
            // SQL virheet

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Opiskelijan tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Opiskelijan tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }

        // Poistetaan opiskelijan tiedot
        if (opiskelija.getEtunimi() == null) {
            txtEtunimi.setText("");
            txtSukunimi.setText("");
            txtOsoite.setText("");
            txtPostinro.setText("");
            txtPostitoimipaikka.setText("");
            txtEmail.setText("");
            txtPuhelin.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen poisto");
            alert.setHeaderText("Virhe");
            alert.setContentText("Opiskelijaa ei loydy.");
            alert.showAndWait();

            return;

        } else {

            txtEtunimi.setText(opiskelija.getEtunimi());
            txtSukunimi.setText(opiskelija.getSukunimi());
            txtOsoite.setText(opiskelija.getLahiosoite());
            txtPostinro.setText(opiskelija.getPostinumero());
            txtPostitoimipaikka.setText(opiskelija.getPostitoimipaikka());
            txtEmail.setText(opiskelija.getEmail());
            txtPuhelin.setText(opiskelija.getPuhelinnro());
        }

        // Vahvistetaan käyttäjälle tietojen poisto
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Opiskelijan tietojen poisto");
            alert.setHeaderText("Vahvista");
            alert.setContentText("Haluatko todella poistaa opiskelijan?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                opiskelija.poistaOpiskelija(connection);
                opiskelija_poistettu = true;
            }
        } catch (SQLException se) {
            // SQL virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen poisto");
            alert.setHeaderText("Results:");
            alert.setContentText("Opiskelijan tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opiskelijan tietojen poisto");
            alert.setHeaderText("Results:");
            alert.setContentText("Opiskelijan tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            // Kun tiedot on poistettu, vahvistetaan tämä. Asetetaan arvot "" eli tyhjiksi
            if (opiskelija_poistettu == true) {
                txtOpiskelijaID.setText("");
                txtEtunimi.setText("");
                txtSukunimi.setText("");
                txtOsoite.setText("");
                txtPostinro.setText("");
                txtPostitoimipaikka.setText("");
                txtEmail.setText("");
                txtPuhelin.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opiskelijan tietojen poisto");
                alert.setHeaderText("Tulokset:");
                alert.setContentText("Opiskelijan tiedot poistettu tietokannasta.");
                alert.showAndWait();

                opiskelija = null;
            }
        }
    }

    /**
     * haeKurssinTiedot() -metodin avulla haetaan kurssin tiedot ID:n perusteella ja ilmoitetaan,
     * jos kurssia ei löydy tai tulee virhetilanne.
     *
     * @throws Exception
     *
     */
    public void haeKurssinTiedot() {
        // Asetetaan arvot nulliksi
        kurssi = null;
        ArrayList<Suoritus> lstSuoritukset = null;

        //Kurssin haku ID:n perusteella
        try {
            kurssi = Kurssi.haeKurssi(connection, Integer.parseInt(txtKurssiID.getText()));

        } catch (SQLException se) {
            // SQL virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssia ei loydy.");
            alert.showAndWait();

        } catch (Exception e) {
            // muut virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssia ei loydy.");
            alert.showAndWait();

        }
        if (kurssi.getNimi() == null) {
            txtKurssinNimi.setText("");
            txtOpintopisteet.setText("");
            txtKurssinKuvaus.setText("");

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssia ei loydy.");
            alert.showAndWait();

        } else {
            txtKurssinNimi.setText(kurssi.getNimi());
            txtOpintopisteet.setText(kurssi.getOpintopisteet());
            txtKurssinKuvaus.setText(kurssi.getKuvaus());

            // Suoritusten haku ArrayListille
            try {
                lstSuoritukset = haeKurssinSuoritukset(kurssi.getKurssi_id());
            } catch (SQLException se) {
                // SQL virheet
                se.printStackTrace();

            } catch (Exception e) {
                // muut virheet
                e.printStackTrace();

            }
        }
    }

    /**
     * haeKurssinSuoritukset() -metodin avulla haetaan kurssin suoritukset ja ilmoitetaan,
     * jos suoritusta ei löydy tai tulee virhetilanne.
     *
     * @throws Exception
     * @return suoritusListaKurssi
     *
     */
    public ArrayList<Suoritus> haeKurssinSuoritukset(int id) throws SQLException, Exception {

        String sql = "SELECT opiskelija_id, kurssi_id, arvosana, suoritus_pvm "
                + " FROM suoritus WHERE kurssi_id = ?";
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;

        try {
            // Yhdistetään tietokantaan
            lause = connection.prepareStatement(sql);
            lause.setInt( 1, id);
            tulosjoukko = lause.executeQuery();

        }

        catch (SQLException se) { throw se; }
        catch (Exception e) { throw e; }

        // Luodaan ArrayList
        ArrayList<Suoritus> suoritusListaKurssi = new ArrayList<Suoritus>();

        // Lisätään tiedot ArrayListille, luodaan suoritusOlio
        try {
            while (tulosjoukko.next()) {

                Suoritus suoritusOlio = new Suoritus();
                suoritusOlio.setOpiskelija_id(tulosjoukko.getInt("opiskelija_id"));
                suoritusOlio.setKurssi_id(tulosjoukko.getInt("kurssi_id"));
                suoritusOlio.setArvonsana(tulosjoukko.getString("arvosana"));

                java.util.Date suorpvm = tulosjoukko.getDate("suoritus_pvm");
                SimpleDateFormat dmyFormat = new SimpleDateFormat("dd.MM.yyyy");
                String dmy = dmyFormat.format(suorpvm);
                suoritusOlio.setSuoritus_pvm(dmy);

                // Lisätään suoritusOliolle suoristusListaKurssi
                suoritusListaKurssi.add(suoritusOlio);

            }

        } catch (SQLException e) { throw e; }

        return suoritusListaKurssi;

    }

    /**
     * lisaaKurssinTiedot() -metodin avulla lisätään kurssin tiedot ja ilmoitetaan,
     * jos tietojen lisääminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void lisaaKurssinTiedot() {
        // Näytetään, että jotain tapahtuu ja lisätään tiedot tietokantaan
        System.out.println("Lisataan...");
       // System.out.println("\t>> Käytetään tietokantaa " + "Opintosuoritustenhallinta_2108401");

        // Boolean on totta, kun kurssi on lisätty
        boolean kurssi_lisatty = true;
        kurssi = null;
        try {
            // Haetaan kurssi ID:n perusteella
            kurssi = Kurssi.haeKurssi(connection, Integer.parseInt(txtKurssiID.getText()));
        } catch (SQLException se) {
            // SQL virheet
            kurssi_lisatty = false;
            se.printStackTrace();

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Kurssin tietojen lisääminen ei onnistu.");
            alert.showAndWait();

        } catch (Exception e) {
            // muut virheet
            kurssi_lisatty = false;
            e.printStackTrace();

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Kurssin tietojen lisääminen ei onnistu.");
            alert.showAndWait();
        }

        // Kurssia ei voi lisätä jos kurssi on jo olemassa ID:n perusteella
        if (kurssi.getNimi() != null) {
            kurssi_lisatty = false;
            txtKurssinNimi.setText(kurssi.getNimi());
            txtOpintopisteet.setText(kurssi.getOpintopisteet());
            txtKurssinKuvaus.setText(kurssi.getKuvaus());

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssi on jo olemassa.");
            alert.showAndWait();

        } else {
            // asetetaan tiedot
            kurssi.setKurssi_id(Integer.parseInt(txtKurssiID.getText()));
            kurssi.setNimi(txtKurssinNimi.getText());
            kurssi.setOpintopisteet(txtOpintopisteet.getText());
            kurssi.setKuvaus(txtKurssinKuvaus.getText());

            // Lisätään tietokantaan
            try {
                kurssi.lisaaKurssi(connection);
            } catch (SQLException se) {
                // SQL virheet
                kurssi_lisatty = false;

                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Kurssin tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Kurssin tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                se.printStackTrace();
            } catch (Exception e) {
                // muut virheet
                kurssi_lisatty = false;

                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Kurssin tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Kurssin tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                // Kun kurssin lisääminen on ok, ilmoitetaan käyttäjälle
                if (kurssi_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Kurssin tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Kurssin tiedot lisatty tietokantaan.");
                    alert.showAndWait();
                }
            }
        }
    }

    /**
     * muutaKurssinTiedot() -metodin avulla muutetaan kurssin tietoja ja ilmoitetaan,
     * jos tietojen muuttaminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void muutaKurssinTiedot() {
        // Asetetaan boolean todeksi
        boolean kurssi_muutettu = true;

        kurssi.setNimi(txtKurssinNimi.getText());
        kurssi.setOpintopisteet(txtOpintopisteet.getText());
        kurssi.setKuvaus(txtKurssinKuvaus.getText());

        try {
            kurssi.muutaKurssi(connection);
        } catch (SQLException se) {
            // SQL virheet
            kurssi_muutettu = false;

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Kurssin tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet
            kurssi_muutettu = false;

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Kurssin tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
            // Kun kurssin tiedot on muutettu, ilmoitetaan siitä käyttäjälle
        } finally {
            if (kurssi_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Kurssin tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Kurssin tiedot muutettu.");
                alert.showAndWait();
            }
        }
    }

    /**
     * poistaKurssinTiedot() -metodin avulla poistetaan kurssin tiedot ja ilmoitetaan,
     * jos tietojen poistaminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void poistaKurssinTiedot() {
        // Asetetaan arvot nulliksi
        kurssi = null;
        boolean kurssi_poistettu = false;

        try {
            // Haetaan kurssi ID:n perusteella
            kurssi = Kurssi.haeKurssi(connection, Integer.parseInt(txtKurssiID.getText()));
        } catch (SQLException se) {
            // SQL virheet

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Kurssin tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Kurssin tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }

        // Katsotaan löytyykö poistettavaa tietoa
        if (kurssi.getNimi() == null) {
            txtKurssinNimi.setText("");
            txtOpintopisteet.setText("");
            txtKurssinKuvaus.setText("");

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen poisto");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssia ei loydy.");
            alert.showAndWait();

            return;

        } else {
            txtKurssinNimi.setText(kurssi.getNimi());
            txtOpintopisteet.setText(kurssi.getOpintopisteet());
            txtKurssinKuvaus.setText(kurssi.getKuvaus());
        }

        // Ilmoitetaan käyttäjälle, että kurssi voidaan poistaa ja haluaako todella poistaa kurssin
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Kurssin tietojen poistaminen");
            alert.setHeaderText("Vahvista, että haluat poistaa kurssin");
            alert.setContentText("Haluatko todella poistaa kurssin?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                kurssi.poistaKurssi(connection);
                kurssi_poistettu = true;
            }
        } catch (SQLException se) {
            // SQL virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen poisto");
            alert.setHeaderText("Tulokset:");
            alert.setContentText("Kurssin tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin tietojen poisto");
            alert.setHeaderText("Results:");
            alert.setContentText("Kurssin tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();

        } finally {
            if (kurssi_poistettu == true) {
                txtKurssiID.setText("");
                txtKurssinNimi.setText("");
                txtOpintopisteet.setText("");
                txtKurssinKuvaus.setText("");

                // Ilmoitetaan käyttäjälle, että kurssin tiedot on poistettu
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Kurssin tietojen poisto");
                alert.setHeaderText("Tulokset:");
                alert.setContentText("Kurssin tiedot poistettu tietokannasta.");
                alert.showAndWait();

                kurssi = null;
            }
        }
    }

    /**
     * lisaaOpiskelijanSuoritus() -metodin avulla lisätään suorituksen tiedot ja ilmoitetaan,
     * jos tietojen lisääminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void lisaaOpiskelijanSuoritus() {
        // Asetetaan boolean todeksi
        boolean suoritus_lisatty = true;

        try {
            // Haetaan opiskelijan ID ja kurssin ID
            suoritus = Suoritus.haeSuoritusKurssiJaOpiskelija(connection, Integer.parseInt(txtOpiskelijaNro.getText()), Integer.parseInt(txtKurssinNro.getText()));
        } catch (SQLException se) {
            // SQL virheet
            suoritus_lisatty = false;
            se.printStackTrace();

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Suorituksen tietojen lisääminen ei onnistu.");
            alert.showAndWait();

        } catch (Exception e) {
            // muut virheet
            suoritus_lisatty = false;
            e.printStackTrace();

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Suorituksen tietojen lisääminen ei onnistu.");
            alert.showAndWait();
        }
        if (suoritus.getOpiskelija_id() != 0 && suoritus.getKurssi_id() != 0) {
            suoritus = null;

            ArrayList <Suoritus> lstSuoritukset = null;

            try {
                lstSuoritukset = haeOpiskelijanSuoritukset (Integer.parseInt(txtOpiskelijaNro.getText()));
            } catch (SQLException se) {
                // SQL virheet
                se.printStackTrace ();

            } catch (Exception e) {
                // muut virheet
                e.printStackTrace ();

            }

            // Asetetaan suorituksen tiedot TableViewille
            finally {
                for(Suoritus opiskelijanSuoritus : lstSuoritukset)
                {
                    tbwSuoritukset.getItems().add(opiskelijanSuoritus);
                }

            }
            suoritus_lisatty = false;
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Suoritus on jo olemassa.");
            alert.showAndWait();

        } else {
            suoritus.setOpiskelija_id(Integer.parseInt(txtOpiskelijaNro.getText()));
            suoritus.setKurssi_id(Integer.parseInt(txtKurssinNro.getText()));
            suoritus.setArvonsana(txtarvonsana.getText());
            suoritus.setSuoritus_pvm(txtsuoritus_pvm.getText());

            try {
                suoritus.lisaaSuoritus(connection);
            } catch (SQLException se) {
                // SQL virheet
                suoritus_lisatty = false;

                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Suorituksen tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Suorituksen tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                se.printStackTrace();
            } catch (Exception e) {
                // muut virheet
                suoritus_lisatty = false;

                // Virheen tullessa, ilmoitetaan käyttäjälle tieto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Suorituksen tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Suorituksen tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
                // Ilmoitetaan käyttäjälle, että suorituksen lisääminen on ok
            } finally {
                if (suoritus_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Suorituksen tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Suorituksen tiedot lisatty tietokantaan.");
                    alert.showAndWait();
                }
            }
        }
    }

    /**
     * muutaOpiskelijanSuoritus() -metodin avulla muutetaan suorituksen tietoja ja ilmoitetaan,
     * jos tietojen muuttaminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void muutaOpiskelijanSuoritus() {
        // Asetetaan boolean todeksi
        boolean suoritus_muutettu = true;

        suoritus.setOpiskelija_id(Integer.parseInt(txtOpiskelijaNro.getText()));
        suoritus.setKurssi_id(Integer.parseInt(txtKurssinNro.getText()));
        suoritus.setArvonsana(txtarvonsana.getText());
        suoritus.setSuoritus_pvm(txtsuoritus_pvm.getText());

        try {
            suoritus.muutaSuoritus(connection);
        } catch (SQLException se) {
            // SQL virheet
            suoritus_muutettu = false;

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Suorituksen tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet
            suoritus_muutettu = false;

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Suorituksen tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
            // Ilmoitetaan käyttäjälle, että tietojen muuttaminen on ok
        } finally {
            if (suoritus_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suorituksen tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Suorituksen tiedot muutettu.");
                alert.showAndWait();
            }
        }
    }

    /**
     * poistaOpiskelijanSuoritus() -metodin avulla poistetaan suorituksen tiedot ja ilmoitetaan,
     * jos tietojen poistaminen ei onnistu tai tulee virhetilanne.
     *
     * @throws SQLException
     * @throws Exception
     *
     */
    public void poistaOpiskelijanSuoritus() {
        // Asetetaan arvo nulliksi
        suoritus = null;
        boolean suoritus_poistettu = false;

        try {
            // Haetaan tiedot opiskelija ID:n ja kurssi ID:n perusteella
            suoritus = Suoritus.haeSuoritusKurssiJaOpiskelija(connection, Integer.parseInt(txtOpiskelijaNro.getText()), Integer.parseInt(txtKurssinNro.getText()));
        } catch (SQLException se) {
            // SQL virheet

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Suorituksen tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Suorituksen tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }
        if ((suoritus.getKurssi_id() == 0) && (suoritus.getOpiskelija_id() == 0)) {
            txtarvonsana.setText("");
            txtsuoritus_pvm.setText("");

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen poisto");
            alert.setHeaderText("Virhe");
            alert.setContentText("Suoritusta ei loydy.");
            alert.showAndWait();

            return;

        } else {
            txtarvonsana.setText(suoritus.getArvonsana());
            txtsuoritus_pvm.setText(suoritus.getSuoritus_pvm());
        }

        // Ilmoitetaan, että suorituksen tiedot voidaan poistaa
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suorituksen tietojen poisto");
            alert.setHeaderText("Vahvista tietojen poisto");
            alert.setContentText("Haluatko todella poistaa suorituksen?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                suoritus.poistaSuoritus(connection);
                suoritus_poistettu = true;
            }
        } catch (SQLException se) {
            // SQL virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen poisto");
            alert.setHeaderText("Tulokset:");
            alert.setContentText("Suorituksen tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            se.printStackTrace();
        } catch (Exception e) {
            // muut virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suorituksen tietojen poisto");
            alert.setHeaderText("Tulokset:");
            alert.setContentText("Suorituksen tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (suoritus_poistettu) { // ainoastaan, jos vahvistettiin ja poisto onnistui
                txtarvonsana.setText("");
                txtsuoritus_pvm.setText("");

                // Ilmoitetaan poistosta käyttäjälle
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suorituksen tietojen poisto");
                alert.setHeaderText("Tulokset:");
                alert.setContentText("Suorituksen tiedot poistettu tietokannasta.");
                alert.showAndWait();

                opiskelija = null;
            }
        }
    }

    /**
     * haeOpiskelijanKurssinID() -metodin avulla haetaan ID:n perusteella tiedot ja ilmoitetaan,
     * jos suoritusta ei löydy tai tulee virhetilanne.
     *
     * @throws Exception
     * @return suoritusListaKurssi
     *
     */
    public  void haeOpiskelijanKurssinID() {
        // Asetetaan arvot nulliksi
        suoritus = null;
        ArrayList <Suoritus> lstSuoritukset = null;

        try {
            suoritus = Suoritus.haeKurssinID(connection, Integer.parseInt(txtKurssinNro.getText()));
        } catch (SQLException se) {
            // SQL virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin suorituksen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssin suoritusta ei loydy.");
            alert.showAndWait();

        } catch (Exception e) {
            // muut virheet
            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin suorituksen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssin suoritusta ei loydy.");
            alert.showAndWait();

        }
        if (suoritus.getOpiskelija_id() == 0) {
            // muut virheet
            txtOpiskelijaNro.setText("");
            txtKurssinNro.setText("");
            txtarvonsana.setText("");
            txtsuoritus_pvm.setText("");

            // Virheen tullessa, ilmoitetaan käyttäjälle tieto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kurssin suorituksen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Kurssin suoritusta ei loydy.");
            alert.showAndWait();

        }
        else {
            try {
                lstSuoritukset = haeKurssinSuoritukset (Integer.parseInt(txtKurssinNro.getText()));
            } catch (SQLException se) {
                // SQL virheet
                se.printStackTrace ();

            } catch (Exception e) {
                // muut virheet
                e.printStackTrace ();

            }

            // Lisätään tiedot suorituksesta TableViewille
            finally {
                for(Suoritus opiskelijanSuoritus : lstSuoritukset) {
                    System.out.println(opiskelijanSuoritus);
                    tbwSuoritukset.getItems().add(opiskelijanSuoritus);
                }
            }
        }
    }
}


