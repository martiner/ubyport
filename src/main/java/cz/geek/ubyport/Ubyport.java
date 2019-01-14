package cz.geek.ubyport;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Ubyport {

	private final UbyportUbytovatel ubytovatel;

	private final List<UbyportUbytovany> ubytovany = new ArrayList<>();

	private Instant exported;

	public Ubyport(UbyportUbytovatel ubytovatel) {
		this.ubytovatel = requireNonNull(ubytovatel, "ubytovatel");
	}

	public Ubyport add(UbyportUbytovany... ubytovany) {
		this.ubytovany.addAll(asList(ubytovany));
		return this;
	}

	public Ubyport setExported(Instant exported) {
		this.exported = exported;
		return this;
	}

	public String asString() {
		StringJoiner joiner = new StringJoiner("\r\n");
		joiner.add(formatUbytovatel());
		ubytovany.stream()
				.map(this::formatUbytovany)
				.forEach(joiner::add);
		;
		return joiner.toString();
	}

	private String formatUbytovatel() {
		Formatter formatter = new Formatter()
				.add("A")
				.add("2")
				.add(ubytovatel.getIdub())
				.add(ubytovatel.getZkratka())
				.add(ubytovatel.getUbytovatel())
				.add(ubytovatel.getKontakt())
				.add(ubytovatel.getOkres())
				.add(ubytovatel.getObec())
				.add(ubytovatel.getCastObce())
				.add(ubytovatel.getUlice())
				.add(ubytovatel.getCisloDomovni())
				.add(ubytovatel.getCisloOrientacni())
				.add(ubytovatel.getPsc())
				.add(exported != null ? exported : Instant.now())
				.add()
				.add();
		return formatter.toString();
	}

	private String formatUbytovany(UbyportUbytovany ubytovany) {
		Formatter formatter = new Formatter()
				.add("U")
				.add(ubytovany.getUbytovaniOd())
				.add(ubytovany.getUbytovaniDo())
				.add(ubytovany.getPrijmeni())
				.add(ubytovany.getJmeno())
				.add()
				.add(ubytovany.getNarozeni())
				.add()
				.add()
				.add(ubytovany.getStatniPrislusnost())
				.add(ubytovany.getBydliste())
				.add(ubytovany.getDoklad())
				.add(ubytovany.getViza())
				.add(ubytovany.getUcelPobytu())
				.add()
				.add(ubytovany.getPoznamka());
		return formatter.toString();
	}

	public void export(OutputStream stream) throws IOException {
		requireNonNull(stream, "stream");

		Charset charset = Charset.forName("Windows-1250");
		OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(stream), charset);
		writer.write(asString());
		writer.flush();
	}

	public byte[] export() throws IOException {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		export(byteArray);
		return byteArray.toByteArray();
	}
}
