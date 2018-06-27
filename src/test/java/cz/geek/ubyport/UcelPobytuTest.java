package cz.geek.ubyport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class UcelPobytuTest {

	@Test
	public void shouldReturnKod() throws Exception {
		assertThat(UcelPobytu.U10.getKod()).isEqualTo("10");
	}
}