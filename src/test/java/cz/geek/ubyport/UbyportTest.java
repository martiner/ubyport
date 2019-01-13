package cz.geek.ubyport;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import sun.misc.IOUtils;

public class UbyportTest {

	private static final Instant TIME = ZonedDateTime.of(
			LocalDate.of(2018, 6, 27),
			LocalTime.of(22, 57),
			ZoneId.systemDefault()).toInstant();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void shouldExportEmptyUbytovatel() throws Exception {
		assertThat(new Ubyport(new UbyportUbytovatel()).setExported(TIME).asString())
				.isEqualTo("A|2||||||||||||27.06.2018 22:57:00||");
	}

	@Test
	public void shouldExportEmptyUbytovany() throws Exception {
		assertThat(new Ubyport(new UbyportUbytovatel()).add(new UbyportUbytovany()).setExported(TIME).asString())
				.isEqualTo("A|2||||||||||||27.06.2018 22:57:00||\r\nU|||||||||||||||");
	}

	@Test
	public void shouldExportTwoEmptyUbytovany() throws Exception {
		assertThat(new Ubyport(new UbyportUbytovatel()).add(new UbyportUbytovany(), new UbyportUbytovany()).setExported(TIME).asString())
				.endsWith("U|||||||||||||||\r\nU|||||||||||||||");
	}

	@Test
	public void shouldExportUbytovatel() throws Exception {

		assertThat(new Ubyport(createUbytovatel()).setExported(TIME).asString())
				.isEqualTo("A|2|id1|abbr1|Ubyt|Kontakt|Okr|Obc|Cast|Ulice|2A|id1|11150|27.06.2018 22:57:00||");
	}

	@Test
	public void shouldExportUbytovany() throws Exception {
		assertThat(new Ubyport(new UbyportUbytovatel()).add(createUbytovany()).setExported(TIME).asString())
				.isEqualTo("A|2||||||||||||27.06.2018 22:57:00||\r\n"
						+ "U|27.06.2018|30.06.2018|Příjmení|Jmeno||12.05.1980|||SVK|Bydl|AK47|V123|10||Pozn");
	}

	@Test
	public void shouldExportToFile() throws Exception {
		Path file = temporaryFolder.newFile().toPath();
		try (OutputStream stream = Files.newOutputStream(file)) {
			new Ubyport(createUbytovatel()).add(createUbytovany()).setExported(TIME).export(stream);
		}
		assertThat(file).hasBinaryContent(readBytes("/example.unl"));
	}

	private static UbyportUbytovatel createUbytovatel() {
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
		return ubytovatel;
	}

	private static UbyportUbytovany createUbytovany() {
		UbyportUbytovany ubytovany = new UbyportUbytovany();
		ubytovany.setUbytovaniOd(LocalDate.of(2018, 6, 27));
		ubytovany.setUbytovaniDo(LocalDate.of(2018, 6, 30));
		ubytovany.setPrijmeni("Příjmení");
		ubytovany.setJmeno("Jmeno");
		ubytovany.setNarozeni(LocalDate.of(1980, 5, 12));
		ubytovany.setStatniPrislusnost(StatniPrislusnost.SVK);
		ubytovany.setBydliste("Bydl");
		ubytovany.setDoklad("AK47");
		ubytovany.setViza("V123");
		ubytovany.setUcelPobytu(UcelPobytu.U10);
		ubytovany.setPoznamka("Pozn");
		return ubytovany;
	}

	private byte[] readBytes(String resource) throws IOException {
		try (InputStream stream = getClass().getResourceAsStream(resource)) {
			if (stream == null) {
				throw new IllegalArgumentException("Resource doesn't exist: " + resource);
			}
			return IOUtils.readFully(stream, -1, true);
		}
	}
}