# Konzultációs gyakorlati feladatok

## Week 01

### 2022-04-12 (Csoportmunka)
Írjatok egy webshop üzemeltetését szimuláló backend alkalmazást. A webshopban felhasználók és termékek vannak. <br>
Funkcionális követelmények:
* Felhasználó regisztráció/bejelentkezés
* Termékek belehelyezése a vásárló kosarába
* Termék kivétele vásárló kosarából 
* Termék mennyiség növelése
* Rendelés leadása
* Termékek listázása/betöltése fájlból


## Week 02
### 2022-04-21

Adott a `lab-solutions/mockito-lab-demo` project. A feladat, hogy a `MovieService` osztályra írj unit teszteket!

## Week 03
### 2022-04-26
A filmes projektet egészítsd ki a következő elemekkel. Legyen egy Rating osztályod ami embeddable.
Ebben legyen egy `double rating` és egy `String username` attribútum. A movie osztályban vedd fel attribútumként ratingek listáját és egy metódust is amivel ratinget lehet hozzáadni a filmhez.<br>
A `MovieRepository` osztályt bővítsd egy metódussal amiben film cím alapján lehet lekérni az adatokat (ratinggel együtt)!

### 2022-04-29

Készíts egy `Team` nevű entitást mely egy focicsapatot fog reprezentálni. Minden focicsapatnak legyen egy neve, egy ország ahol található, egy `Enum` típusú attribútum ami azt reprezentálja, hogy hányadosztályban szerepel a csapat, egy aktuális pontszám és játékosok listája akik a focicsapatban játszanak. A lista egyszerűen a játékosok nevét tárolja. Legyen egy `addPlayer` metódus amivel játékost tudok hozzáadni a listához. 

Készíts egy `TeamRepository` osztályt melyen keresztül le tudsz menteni egy csapatot. Legyen egy metódus amiben le tudsz kérdezni egy csapatot játékosokkal együtt csapatnév alapján. Ezen kívül ami frissíti egy csapat pontszámát a kapott értékre id alapján és egy metódus ami ország és osztály alapján visszaadja az összes csaptot pontszám alapján csökkenő sorrendben.     

## Week 04
### 2022-05-04
Adott egy `Person` és egy `Child` entitás. A `Person`-nek van neve és életkora, a `Child`-nak neve és születési éve.
Ezenkívül a `Person` tartalmaz egy `List<Child>` típusú attribútumot, a `Child` pedig egy `Person` típusú attribútumot.
A két entitás között kétirányú egy-több kapcsolat van.
A feladat a megfelelő JPQL lekérdezések megírása az alábbiakra:

Keressük meg:

- azokat a gyerekeket, akik egy megadott év után születtek
- azokat a felnőtteket, akiknek több gyerekük is van
- azt a felnőttet, akinek a legtöbb gyereke van
- azt a gyereket, akinek a szülője a megadott nevű, és a megadott évben született (a gyerek)
- azt a szülőt, akihez a paraméterül megadott nevű gyerek tartozik
- az átlagos gyerekszámot
- azokat a gyerekeket, akik a legtöbben vannak testvérek