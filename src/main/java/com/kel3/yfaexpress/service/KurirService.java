package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Kurir;

public interface KurirService {
    Kurir latTransactional();

    Kurir saveKurirMaterDetail(Kurir kurir);
}
