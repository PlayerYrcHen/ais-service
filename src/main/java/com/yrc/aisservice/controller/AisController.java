package com.yrc.aisservice.controller;

import com.yrc.aisservice.entity.DataSeg;
import com.yrc.aisservice.service.AisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AisController {

    @Autowired
    private AisService aisService;

    @RequestMapping("/ais")
    public List<DataSeg> ais() {
        return aisService.findAll();
    }
}
