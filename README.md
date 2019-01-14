# UbyPort Java

[![Build Status](https://travis-ci.org/martiner/ubyport.png?branch=master)](https://travis-ci.org/martiner/ubyport)

* https://ubyport.policie.cz/
* https://www.policie.cz/clanek/vyplnovani-prihlasovaciho-tiskopisu.aspx
* https://www.policie.cz/clanek/unl-soubor.aspx

```xml
<dependency>
	<groupId>cz.geek</groupId>
	<artifactId>ubyport</artifactId>
	<version>VERSION</version>
</dependency>
```

## Usage

```java
UbyportUbytovatel ubytovatel = new UbyportUbytovatel();
ubytovatel.setIdub("id1");
ubytovatel.setZkratka("abbr1");
ubytovatel.setUbytovatel("Ubyt");
ubytovatel.setKontakt("Kontakt");
ubytovatel.setOkres("Okr");
ubytovatel.setObec("Obc");
ubytovatel.setCastObce("Cast");
ubytovatel.setUlice("Ulice");
ubytovatel.setCisloDomovni("2A");
ubytovatel.setCisloOrientacni("id1");
ubytovatel.setPsc("11150");

UbyportUbytovany ubytovany = new UbyportUbytovany();
ubytovany.setUbytovaniOd(LocalDate.of(2018, 6, 27));
ubytovany.setUbytovaniDo(LocalDate.of(2018, 6, 30));
ubytovany.setPrijmeni("Prijmeni");
ubytovany.setJmeno("Jmeno");
ubytovany.setNarozeni(LocalDate.of(1980, 5, 12));
ubytovany.setStatniPrislusnost(StatniPrislusnost.SVK);
ubytovany.setBydliste("Bydl");
ubytovany.setDoklad("AK47");
ubytovany.setViza("V123");
ubytovany.setUcelPobytu(UcelPobytu.U10);
ubytovany.setPoznamka("Pozn");

Ubyport ubyport = new Ubyport(ubytovatel);
ubyport.add(ubytovany);

System.out.println(ubyport.asString());

ubyport.export(outputStream);

```
