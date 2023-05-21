package com.rest.io.ipldashboardbackend.webcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ApiHealthCheck {

    @GetMapping("/status")
    public String getAppHealth()
    {
        return "<html><body><h1> IPL Dashboard is up and running ! </h1></body></html>";
    }
}
