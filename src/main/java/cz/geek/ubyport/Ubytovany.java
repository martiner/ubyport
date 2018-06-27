package cz.geek.ubyport;

import java.time.LocalDate;

public class Ubytovany {

	private LocalDate ubytovaniOd;
	private LocalDate ubytovaniDo;
	private String prijmeni;
	private String jmeno;
	private LocalDate narozeni;
	private StatniPrislusnost statniPrislusnost;
	private String bydliste;
	private String doklad;
	private String viza;
	private UcelPobytu ucelPobytu;
	private String poznamka;

	public LocalDate getUbytovaniOd() {
		return ubytovaniOd;
	}

	public void setUbytovaniOd(LocalDate ubytovaniOd) {
		this.ubytovaniOd = ubytovaniOd;
	}

	public LocalDate getUbytovaniDo() {
		return ubytovaniDo;
	}

	public void setUbytovaniDo(LocalDate ubytovaniDo) {
		this.ubytovaniDo = ubytovaniDo;
	}

	public String getPrijmeni() {
		return prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}

	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public LocalDate getNarozeni() {
		return narozeni;
	}

	public void setNarozeni(LocalDate narozeni) {
		this.narozeni = narozeni;
	}

	public StatniPrislusnost getStatniPrislusnost() {
		return statniPrislusnost;
	}

	public void setStatniPrislusnost(StatniPrislusnost statniPrislusnost) {
		this.statniPrislusnost = statniPrislusnost;
	}

	public String getBydliste() {
		return bydliste;
	}

	public void setBydliste(String bydliste) {
		this.bydliste = bydliste;
	}

	public String getDoklad() {
		return doklad;
	}

	public void setDoklad(String doklad) {
		this.doklad = doklad;
	}

	public String getViza() {
		return viza;
	}

	public void setViza(String viza) {
		this.viza = viza;
	}

	public UcelPobytu getUcelPobytu() {
		return ucelPobytu;
	}

	public void setUcelPobytu(UcelPobytu ucelPobytu) {
		this.ucelPobytu = ucelPobytu;
	}

	public String getPoznamka() {
		return poznamka;
	}

	public void setPoznamka(String poznamka) {
		this.poznamka = poznamka;
	}
}
