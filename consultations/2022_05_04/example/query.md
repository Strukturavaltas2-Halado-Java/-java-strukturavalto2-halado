Adott egy Person és egy Child entitás. A Person-nek van neve és életkora, a Child-nak neve és születési éve.
Ezenkívül a Person tartalmaz egy List<Child> típusú attribútumot, a Child pedig egy Person típusú attribútumot.
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