package by.ita.je.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Controller
public class SalesController {

    private final RestTemplate restTemplate;

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/flight/list")
    public String getListFlights() {
        return "flights";
    }


}
