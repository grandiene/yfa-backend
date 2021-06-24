package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Transaksi;

public interface TransaksiService {
    Transaksi saveTransaksiMaterDetail(Transaksi transaksi);

    Transaksi getResi(Transaksi transaksi);

}
