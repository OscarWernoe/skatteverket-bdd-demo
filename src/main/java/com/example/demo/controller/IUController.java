package com.example.demo.controller;

import com.example.demo.domain.IU;
import com.example.demo.domain.SK001IU;
import com.example.demo.service.IUService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IUController {

    private final IUService iuService;

    public IUController(IUService iuService) {
        this.iuService = iuService;
    }

    @PostMapping("/iu")
    public void processIU(@RequestBody IU iu) {
        iuService.processIU(iu);
    }

    @PostMapping("/SK001")
    public void processIU(@RequestBody SK001IU iu) {

    }
}
