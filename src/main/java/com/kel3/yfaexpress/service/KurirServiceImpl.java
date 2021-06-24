package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Kurir;
import com.kel3.yfaexpress.repository.KurirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Transactional
@Service
public class KurirServiceImpl implements KurirService {

    @Autowired
    private KurirRepository kurirRepository;

    @Override
    public Kurir latTransactional() {
        return null;
    }

    @Override
    public Kurir saveKurirMaterDetail(Kurir kurir) {
        kurir = kurirRepository.save(kurir);

        return kurir;
    }
}



