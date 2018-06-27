package cz.geek.ubyport;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class UbyportTest {

	private static final Instant TIME = ZonedDateTime.of(
			LocalDate.of(2018, 6, 27),
			LocalTime.of(22, 57),
			ZoneId.systemDefault()).toInstant();

	@Test
	public void shouldExportEmptyUbytovatel() throws Exception {
		assertThat(new Ubyport(new Ubytovatel()).setExported(TIME).asString())
				.isEqualTo("A|2||||||||||||27.06.2018 22:57:00||");
	}

	@Test
	public void shouldExportEmptyUbytovany() throws Exception {
		assertThat(new Ubyport(new Ubytovatel()).add(new Ubytovany()).setExported(TIME).asString())
				.isEqualTo("A|2||||||||||||27.06.2018 22:57:00||\r\nU|||||||||||||||");
	}

	@Test
	public void shouldExportTwoEmptyUbytovany() throws Exception {
		assertThat(new Ubyport(new Ubytovatel()).add(new Ubytovany(), new Ubytovany()).setExported(TIME).asString())
				.endsWith("U|||||||||||||||\r\nU|||||||||||||||");
	}

	@Test
	public void shouldExportUbytovatel() throws Exception {
		Ubytovatel ubytovatel = new Ubytovatel();
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

		assertThat(new Ubyport(ubytovatel).setExported(TIME).asString())
				.isEqualTo("A|2|id1|abbr1|Ubyt|Kontakt|Okr|Obc|Cast|Ulice|2A|id1|11150|27.06.2018 22:57:00||");
	}

	@Test
	public void shouldExportUbytovany() throws Exception {
		Ubytovany ubytovany = new Ubytovany();
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

		assertThat(new Ubyport(new Ubytovatel()).add(ubytovany).setExported(TIME).asString())
				.isEqualTo("A|2||||||||||||27.06.2018 22:57:00||\r\n"
						+ "U|27.06.2018|30.06.2018|Prijmeni|Jmeno||12.05.1980|||SVK|Bydl|AK47|V123|10||Pozn");
	}

}