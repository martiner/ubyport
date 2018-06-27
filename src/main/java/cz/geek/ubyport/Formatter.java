package cz.geek.ubyport;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

class Formatter {

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("dd.MM.yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

	private final StringJoiner joiner = new StringJoiner("|");

	Formatter add(String item) {
		if (item != null) {
			joiner.add(item);
		} else {
			add();
		}
		return this;
	}

	Formatter add() {
		return add("");
	}

	Formatter add(LocalDate date) {
		if (date != null) {
			add(dateFormatter.format(date));
		} else {
			add();
		}
		return this;
	}

	Formatter add(Instant date) {
		if (date != null) {
			add(dateTimeFormatter.format(date));
		}
		return this;
	}

	Formatter add(Kod kod) {
		if (kod != null) {
			add(kod.getKod());
		} else {
			add();
		}
		return this;
	}

	@Override
	public String toString() {
		return joiner.toString();
	}
}
