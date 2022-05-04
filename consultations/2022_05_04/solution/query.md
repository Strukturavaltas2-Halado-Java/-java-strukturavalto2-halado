Adott egy Person és egy Child entitás. A Person-nek van neve és életkora, a Child-nak neve és születési éve.
Ezenkívül a Person tartalmaz egy List<Child> típusú attribútumot, a Child pedig egy Person típusú attribútumot.
A két entitás között kétirányú egy-több kapcsolat van.
A feladat a megfelelő JPQL lekérdezések megírása az alábbiakra:

Keressük meg:

- azokat a gyerekeket, akik egy megadott év után születtek
  "select c from Child c where c.yearOfBirth > :year"
  
- azokat a felnőtteket, akiknek több gyerekük is van
  "select p from Person p where p.children.size > 1 order by p.name"
  
- azt a felnőttet, akinek a legtöbb gyereke van
  "select p from Person p where p.children.size = (select max(p.children.size) from Person p)"
  
- azt a gyereket, akinek a szülője a megadott nevű, és a megadott évben született (a gyerek)
  "select c from Child c where c.person.name = :name and c.yearOfBirth = :year"
  
- azt a szülőt, akihez a paraméterül megadott nevű gyerek tartozik
  "select c.person from Child c where c.name = :name"
  
- az átlagos gyerekszámot
  "select avg(p.children.size) from Person p"
  
- azokat a gyerekeket, akik a legtöbben vannak testvérek
  "select c from Child c where c.person.children.size = (select max(q.children.size) from Person q)"