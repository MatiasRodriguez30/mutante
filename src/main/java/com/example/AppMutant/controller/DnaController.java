package com.example.AppMutant.controller;

import com.example.AppMutant.service.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class DnaController {

    @Autowired
    private DnaService dnaService;
    @PostMapping
    public ResponseEntity<Void> checkMutant(@RequestBody String[] dna){
        boolean isMutant = dnaService.isMutant(dna);
        if(isMutant){
            dnaService.saveDna(String.join(",",dna), true);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            dnaService.saveDna(String.join(",",dna),false);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
