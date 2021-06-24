package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.entity.Pengirim;
import com.kel3.yfaexpress.repository.PengirimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pengirim")
public class ApiPengirim {
    @Autowired
    private PengirimRepository pengirimRepository;

    @GetMapping
    public List<Pengirim> getAll () {
        return pengirimRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Pengirim> getById(@PathVariable Integer idPengirim) {
        return pengirimRepository.findAllByIdPengirim(idPengirim);
    }

    @PostMapping
    public Pengirim save(@RequestBody Pengirim pengirim) {
        return pengirimRepository.save(pengirim);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer idPengirim) {
        pengirimRepository.deleteById(idPengirim);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTablePengirim() {
        pengirimRepository.deleteAll();
    }
}
