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
 * Paaohjelma, jolla testataan luokkien Opiskelija, Kurssi ja Suoritus toimintaa.
 * 546 Ohjelmoinnin perusteet: Talla kurssilla opiskelet ohjelmoinnin perusteita
 * 243 Logiikka: Talla kurssilla opiskelet logiikkaa
 * 753 Ammatillinen kasvu: Talla kurssilla opiskelet ammatilliseen kasvuun liittyvia asioita
 */
public class MainApp {
    public static void main(String[] args) {

        /**
         * 1 opiskelija: Matti Ruohonen
         * 2 opiskelija: Teppo Ruohonen
         *
         * Ensimmaisen opiskelija-olion luominen ja arvojen antaminen set -metodeille
         */
        Opiskelija opiskelijaMatti = new Opiskelija();
        opiskelijaMatti.setOpiskelija_id(1);
        opiskelijaMatti.setEtunimi("Matti");
        opiskelijaMatti.setSukunimi("Ruohonen");
        opiskelijaMatti.setLahiosoite("VauhtiKiihtyy 75");
        opiskelijaMatti.setPostitoimipaikka("Joensuu");
        opiskelijaMatti.setPostinumero("80160");
        opiskelijaMatti.setEmail("matti@gmail.com");
        opiskelijaMatti.setPuhelinnro("123456789");

        /**
         * Toisen opiskelija-olion luominen ja arvojen antaminen set -metodeille
         */
        Opiskelija opiskelijaTeppo = new Opiskelija();
        opiskelijaTeppo.setOpiskelija_id(2);
        opiskelijaTeppo.setEtunimi("Teppo");
        opiskelijaTeppo.setSukunimi("Ruohonen");
        opiskelijaTeppo.setLahiosoite("Kissankultaa 66");
        opiskelijaTeppo.setPostitoimipaikka("Joensuu");
        opiskelijaTeppo.setPostinumero("80140");
        opiskelijaTeppo.setEmail("teppo@gmail.com");
        opiskelijaTeppo.setPuhelinnro("987654321");


        /**
         * 546 Ohjelmoinnin perusteet: Talla kurssilla opiskelet ohjelmoinnin perusteita
         * 243 Logiikka: Talla kurssilla opiskelet logiikkaa
         * 753 Ammatillinen kasvu: Talla kurssilla opiskelet ammatilliseen kasvuun liittyvia asioita
         *
         * Ensimmaisen kurssi-olion luominen, Ohjelmoinnin perusteet, ja arvojen antaminen set -metodeille
         */
        Kurssi ekaKurssi = new Kurssi();
        ekaKurssi.setKurssi_id(546);
        ekaKurssi.setNimi("Ohjelmoinnin perusteet");
        ekaKurssi.setOpintopisteet(5);
        ekaKurssi.setKuvaus("Talla kurssilla opiskelet ohjelmoinnin perusteita");

        /**
         * Toisen kurssi-olion luominen, Logiikka, ja arvojen antaminen sille
         */
        Kurssi tokaKurssi = new Kurssi();
        tokaKurssi.setKurssi_id(243);
        tokaKurssi.setNimi("Logiikka");
        tokaKurssi.setOpintopisteet(2);
        tokaKurssi.setKuvaus("Talla kurssilla opiskelet logiikkaa");

        /**
         * Kolmannen kurssi-olion luominen, Ammatillinen kasvu, ja arvojen antaminen sille
         */
        Kurssi kolmasKurssi = new Kurssi();
        kolmasKurssi.setKurssi_id(753);
        kolmasKurssi.setNimi("Ammatillinen kasvu");
        kolmasKurssi.setOpintopisteet(2);
        kolmasKurssi.setKuvaus("Talla kurssilla opiskelet ammatilliseen kasvuun liittyvia asioita");


        /**
         * Ensimmaisen suoritus-olion luominen ja arvojen antaminen sille
         * 1 opiskelijan Matti suoritus kurssista Ohjelmoinnin perusteet
         */
        Suoritus ekaSuoritus = new Suoritus();
        ekaSuoritus.setOpiskelija_id(1);
        ekaSuoritus.setKurssi_id(546);
        ekaSuoritus.setArvonsana(3);
        ekaSuoritus.setSuoritus_pvm("2021/10/15");

        /**
         * Toisen suoritus-olion luominen ja arvojen antaminen sille
         * 1 opiskelijan Matti suoritus kurssista Logiikka
         */
        Suoritus tokaSuoritus = new Suoritus();
        tokaSuoritus.setOpiskelija_id(1);
        tokaSuoritus.setKurssi_id(243);
        tokaSuoritus.setArvonsana(4);
        tokaSuoritus.setSuoritus_pvm("2021/12/09");

        /**
         * Kolmannen suoritus-olion luominen ja arvojen antaminen sille
         * 1 opiskelijan Matti suoritus kurssista Ammatillinen kasvu
         */
        Suoritus kolmasSuoritus = new Suoritus();
        kolmasSuoritus.setOpiskelija_id(1);
        kolmasSuoritus.setKurssi_id(753);
        kolmasSuoritus.setArvonsana(5);
        kolmasSuoritus.setSuoritus_pvm("2021/10/10");

        /**
         * Neljannen suoritus-olion luominen ja arvojen antaminen sille
         * 2 opiskelija Teppo suoritus kurssista Ohjelmoinnin perusteet
         */
        Suoritus neljasSuoritus = new Suoritus();
        neljasSuoritus.setOpiskelija_id(2);
        neljasSuoritus.setKurssi_id(546);
        neljasSuoritus.setArvonsana(1);
        neljasSuoritus.setSuoritus_pvm("2021/12/09");

        /**
         * Viidennen suoritus-olion luominen ja arvojen antaminen sille
         * 2 opiskelija Teppo suoritus kurssista Logiikka
         */
        Suoritus viidesSuoritus = new Suoritus();
        viidesSuoritus.setOpiskelija_id(2);
        viidesSuoritus.setKurssi_id(243);
        viidesSuoritus.setArvonsana(0);
        viidesSuoritus.setSuoritus_pvm("2021/09/09");

        /**
         * Kuudennen suoritus-olion luominen ja arvojen antaminen sille
         * 2 opiskelija Teppo suoritus kurssista Ammatillinen kasvu
         */
        Suoritus kuudesSuoritus = new Suoritus();
        kuudesSuoritus.setOpiskelija_id(2);
        kuudesSuoritus.setKurssi_id(753);
        kuudesSuoritus.setArvonsana(2);
        kuudesSuoritus.setSuoritus_pvm("2021/10/01");

        /**
         * Seitsamannen suoritus-olion luominen ja arvojen antaminen sille
         * 2 opiskelija Teppo suoritus kurssista Logiikka
         */
        Suoritus seitsemasSuoritus = new Suoritus();
        seitsemasSuoritus.setOpiskelija_id(2);
        seitsemasSuoritus.setKurssi_id(243);
        seitsemasSuoritus.setArvonsana(3);
        seitsemasSuoritus.setSuoritus_pvm("2021/10/10");


        /**
         * Tietyn opiskelijan suoritusten listaus Suoritus LinkedListin avulla ja arvojen tulostaminen for-loopilla
         */
        System.out.println("Tietyn opiskelijan suoritusten listaus");
        LinkedList<Suoritus> getOpiskelijanSuoritukset = new LinkedList<Suoritus>();
        getOpiskelijanSuoritukset.add(ekaSuoritus);
        getOpiskelijanSuoritukset.add(tokaSuoritus);
        getOpiskelijanSuoritukset.add(kolmasSuoritus);

        for(Suoritus getOpiskelijanSuoritus: getOpiskelijanSuoritukset){
            System.out.println(getOpiskelijanSuoritus);
        }

        System.out.println("..................................................................................");


        /**
        * Tietyn kurssin suoritusten listaus ja arvojen tulostaminen for-loopilla: Kurssina Logiikka
        */
        System.out.println("Tietyn kurssin suoritusten listaus: Logiikka");

        LinkedList<Suoritus> kurssinSuoritukset = new LinkedList<Suoritus>();
        kurssinSuoritukset.add(tokaSuoritus);
        kurssinSuoritukset.add(viidesSuoritus);
        kurssinSuoritukset.add(seitsemasSuoritus);

        for(Suoritus kurssinSuoritus: kurssinSuoritukset){
            System.out.println(kurssinSuoritus);
        }

        System.out.println("..................................................................................");


        /**
         * Tietyn kurssin suoritusten opiskelijoiden ja arvosanojen haku linkedlistiin seka
         * arvojen tulostaminen for-loopilla
         */
        System.out.println("Tietyn kurssin opiskelijoiden ja arvosanojen listaus: Ohjelmoinnin perusteet");
        LinkedList<Suoritus> opiskelijat = new LinkedList<Suoritus>();
        opiskelijat.add(ekaSuoritus);
        opiskelijat.add(neljasSuoritus);

        for(Suoritus Suoritus: opiskelijat){
            System.out.println(Suoritus);
        }

        System.out.println("..................................................................................");

        System.out.println(" ");
        /**
         * Esimerkki getOpiskelijanSuoritukset metodin toiminnasta ja lisaaSuoritus toiminnasta opiskelijaluokassa
         */
        System.out.println("Esimerkki getOpiskelijanSuoritukset metodin toiminnasta ja lisaaSuoritus toiminnasta opiskelijaluokassa:");
        opiskelijaMatti.lisaaSuoritus(viidesSuoritus);
        opiskelijaMatti.getOpiskelijanSuoritukset();
        System.out.println(getOpiskelijanSuoritukset);

        System.out.println(" ");

        /**
         * Esimerkki getKurssinSuoritukset ja getKurssinOpiskelijat kaytosta kurssiluokassa
         */
        System.out.println("Esimerkki getKurssinSuoritukset ja getKurssinOpiskelijat kaytosta kurssiluokassa:");
        System.out.println(ekaKurssi.getKurssinOpiskelijat(opiskelijaMatti));
        System.out.println(ekaKurssi.getKurssinSuoritukset(ekaSuoritus));

        System.out.println("..................................................................................");


    }
}
