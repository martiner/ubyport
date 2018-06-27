package cz.geek.ubyport;

public enum UcelPobytu implements Kod {
	
	U00("ZDRAVOTNÍ"),
	U01("OBCHODNÍ (služební cesty)"),
	U02("KULTURNÍ"),
	U03("NÁVŠTĚVA RODINY NEBO PŘÁTEL"),
	U04("POZVÁNÍ"),
	U05("OFICIÁLNÍ (POLITICKÝ)"),
	U06("PODNIKÁNÍ - OSVČ"),
	U07("SPORTOVNÍ"),
	U10("TURISTIKA"),
	U11("STUDIUM (ŠKOLENÍ, STÁŽ)"),
	U12("TRANZIT (průjezd)"),
	U13("LETIŠTNÍ TRANZIT (letištní průjezd)"),
	U27("ZAMĚSTNÁNÍ"),
	U93("TZV. ADS vízum udělované občanu Číny"),
	U99("OSTATNÍ / JINÉ");

	private final String popis;

	UcelPobytu(String popis) {
		this.popis = popis;
	}

	public String getPopis() {
		return popis;
	}

	public String getKod() {
		return name().substring(1);
	}
}
