package io.javapratice.CoronoVirustracker.services;

import io.javapratice.CoronoVirustracker.models.locationStats;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

@Service  //makes it a spring service
public class CoronoVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";


    private List<locationStats> allstats = new ArrayList<>();

    public List<locationStats> getAllstats() {
        return allstats;
    }

    @PostConstruct //annotation that says, execute this method when you create an instance of this class
    @Scheduled(cron = "* * 1 * * *") //run the method on every day
    public void fetchVirusData() throws IOException, InterruptedException {
         List<locationStats> newstats = new ArrayList<>();
           //connect to http via http client available from java 11
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            locationStats locStat = new locationStats();
            locStat.setState(record.get("Province/State"));
            locStat.setCountry(record.get("Country/Region"));

            int latestCases = Integer.parseInt(record.get(record.size()-1));
            int prevDayCases = Integer.parseInt(record.get(record.size()-2));
            locStat.setLatestTotalCases(latestCases);
            locStat.setDifffromPrevday(latestCases-prevDayCases);
            //System.out.println(locStat);
            newstats.add(locStat);

        }

        this.allstats=newstats;

    }


}

