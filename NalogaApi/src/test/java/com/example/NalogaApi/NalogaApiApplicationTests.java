package com.example.NalogaApi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class NalogaApiApplicationTests {


	@Test
	void callService(){
		//test if all texts are returned
		NalogaApiApplication nalogaApiApplication = new NalogaApiApplication();
		assertTrue(nalogaApiApplication.callService("4").contains("Če želiš reševati nerešljivo, sem ter tja narediti kak čudež, ustvariti vsaj za odtenek boljši svet in pomagati drugim vizionarjem in inovatorjem, potem se nam pridruži.") &&
				nalogaApiApplication.callService("4").contains("Vrhunska ekipa za informacijsko optimizacijo poslovanja. Prinaša popoln nadzor nad celotnim poslovanjem, manjše stroške in premoč nad konkurenti.") &&
				nalogaApiApplication.callService("4").contains("Vsebine, ki vam pomagajo do boljšega poslovanja. Redno spremljanje povzroča odlično počutje in boljše poslovne rezultate.") &&
				nalogaApiApplication.callService("4").contains("Naše rešitve so doma na šestih celinah in v več kot 100 državah sveta, že 30 let. Od globalnih multinacionalk, kot so SIXT, BOSCH in SCANIA, do super nišnih domačih igralcev."));
	}

}
