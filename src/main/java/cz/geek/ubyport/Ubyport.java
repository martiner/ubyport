package cz.geek.ubyport;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Ubyport {

	private final Ubytovatel ubytovatel;

	private final List<Ubytovany> ubytovany = new ArrayList<>();

	private Instant exported;

	public Ubyport(Ubytovatel ubytovatel) {
		this.ubytovatel = requireNonNull(ubytovatel, "ubytovatel");
	}

	public Ubyport add(Ubytovany... ubytovany) {
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

	private String formatUbytovany(Ubytovany ubytovany) {
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
}
