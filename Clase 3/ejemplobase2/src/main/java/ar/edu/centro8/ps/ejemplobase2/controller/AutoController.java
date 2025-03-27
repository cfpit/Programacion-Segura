package ar.edu.centro8.ps.ejemplobase2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.ps.ejemplobase2.model.Auto;
import ar.edu.centro8.ps.ejemplobase2.repository.AutoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AutoController {

    @Autowired
    private AutoRepository autoRepo;

    @GetMapping("/auto/traer")
    public List<Auto> traerAutos() {
        return autoRepo.findAll() ;
    }

    @PostMapping("/auto/crear")
    public void crearAuto(@RequestBody Auto a) {
        autoRepo.save(a);
    }

}
