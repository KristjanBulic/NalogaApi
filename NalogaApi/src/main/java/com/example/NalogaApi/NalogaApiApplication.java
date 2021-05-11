package com.example.NalogaApi;

import com.example.NalogaApi.counter.Counter;
import com.example.NalogaApi.scrapper.Scrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

@SpringBootApplication
@RestController
public class NalogaApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(NalogaApiApplication.class, args);
	}

	@RequestMapping(path = "/{numberOfParalelCalls}")
	public static String callService(@PathVariable("numberOfParalelCalls") String numberOfParalelCalls) {

		int paralelCalls = Integer.parseInt(numberOfParalelCalls);

		Vector<String> text = new Vector<>();

		Counter counter = new Counter();

		String[] urls = {"https://www.result.si/projekti/",
				"https://www.result.si/o-nas/",
				"https://www.result.si/kariera/",
				"https://www.result.si/blog/"
		};

		String classToGet = "et_pb_header_content_wrapper";

		if (paralelCalls < 1 || paralelCalls > 4){
			return "Only values between 1 and 4";
		}

		if (paralelCalls == 1) {
			for (String url : urls) {
				Scrapper.getContent(url, classToGet, text, counter);
			}
		}else {
			for (int i = 0; i < paralelCalls; i++){
				createThread(urls[i], classToGet, text, counter);
			}

			for (int j = 4; j > paralelCalls; j--){
				//zaporedno klice vse strani ki niso bile klicane
				//vzporedno
				Scrapper.getContent(urls[j-1], classToGet, text, counter);
			}
		}
		while (true){ //poskrbi da vse niti zakljucijo
			if (text.size() == 4){
				break;
			}
		}
		return (text.toString() + "\n" + counter.toString());
	}

	private static void createThread(String url, String className, Vector<String> storage, Counter counter){
		//ustvari novo nit za vzporedni klic strani
		new Thread(new Scrapper(url, className, storage, counter)).start();
	}
}


