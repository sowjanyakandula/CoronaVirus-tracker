package io.javapratice.CoronoVirustracker.controller;

import io.javapratice.CoronoVirustracker.models.locationStats;
import io.javapratice.CoronoVirustracker.services.CoronoVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronoVirusDataService coronoVirusDataService;

    @GetMapping("/")  //root url
    public String home(Model model){
        List<locationStats> allStats = coronoVirusDataService.getAllstats();
        int totalCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat->stat.getDifffromPrevday()).sum();
        model.addAttribute( "locationStats", allStats);
        model.addAttribute( "totalReportedCases", totalCases);
        model.addAttribute( "totalNewCases", totalNewCases);
        return "home";
    }
}
