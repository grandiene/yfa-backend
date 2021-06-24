package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.entity.Penerima;
import com.kel3.yfaexpress.repository.PenerimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penerima")
public class ApiPenerima {
    @Autowired
    private PenerimaRepository penerimaRepository;

    @GetMapping
    public List<Penerima> getAll () {
        return penerimaRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Penerima> getById(@PathVariable Integer idPenerima) {
        return penerimaRepository.findAllByIdPenerima(idPenerima);
    }

    @PostMapping
    public Penerima save(@RequestBody Penerima penerima) {
        return penerimaRepository.save(penerima);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer idPenerima) {
        penerimaRepository.deleteById(idPenerima);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTablePenerima() {
        penerimaRepository.deleteAll();
    }
}
