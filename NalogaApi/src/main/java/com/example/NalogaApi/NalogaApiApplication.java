package com.example.NalogaApi;
import com.example.NalogaApi.counter.Counter;
import com.example.NalogaApi.scrapper.Scrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@RestController
public class NalogaApiApplication {
	
	private ExecutorService executorService = Executors.newFixedThreadPool(4);

	public static void main(String[] args) {
		SpringApplication.run(NalogaApiApplication.class, args);
	}

	@GetMapping(path = "/{numberOfParalelCalls}")
	public static String callService(@PathVariable("numberOfParalelCalls") String numberOfParalelCalls) {

		int paralelCalls = Integer.parseInt(numberOfParalelCalls);
		Vector<String> text = new Vector<>(); //here will be stored text from webpages
		Counter counter = new Counter();
		String classToGet = "et_pb_header_content_wrapper";
		String[] urls = {
				"https://www.result.si/projekti/",
				"https://www.result.si/o-nas/",
				"https://www.result.si/kariera/",
				"https://www.result.si/blog/"
		};

		if (paralelCalls < 1 || paralelCalls > 4){
			return "Only values between 1 and 4";
		}

		for(int i = 0; i < 4; i++){
			if(i < paralelCalls - 1){
				executorService.submit(new Scrapper(urls[i], classToGet, text, counter));
			}else {
				Scrapper.getContent(urls[i], classToGet, text, counter);
			}
		}
			while (true){ //waits for all threads to end
				if (text.size() == 4){
					break;
				}
			}
		}

		return (text.toString() + "\n" + counter.toString());
	}
}


