package com.smk.cashier.service;

import com.smk.cashier.dao.BarangDao;
import com.smk.cashier.model.Barang;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class BarangServiceTest {

    @Test
    @Order(2)
    void getBarangList() {
        List<Barang> barangList = BarangService.getInstance().getBarangList();
        assertEquals(barangList.size(),3);
    }
    @Test
    @Order(3)
    void findByName(){
        List<Barang> resultList = BarangService.getInstance().findByName("Laptop");
        assertEquals(resultList.size(),2);

    }

    @Test
    @Order(1)
    void addBarang() {
        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(5000000);
        BarangService.getInstance().addBarang(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("MO001");
        mouse.setNamaBarang("Mouse");
        mouse.setHargaBarang(100000);
        BarangService.getInstance().addBarang(mouse);

        Barang laptopgaming = new Barang();
        laptopgaming.setKodeBarang("LP0002");
        laptopgaming.setNamaBarang("Laptop Gaming");
        laptopgaming.setHargaBarang(20000000);
        BarangService.getInstance().addBarang(laptopgaming);

    }

    @Test
    @Order(4)
    void addToDB() {
        BarangDao barangDao = new BarangDao();

        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(5000000);
        laptop.setDateCreated(new Date());
        laptop.setLastModified(new Date());
        barangDao.save(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("MO001");
        mouse.setNamaBarang("Mouse");
        mouse.setHargaBarang(100000);
        mouse.setDateCreated(new Date());
        mouse.setLastModified(new Date());
        barangDao.save(mouse);

        Barang laptopgaming = new Barang();
        laptopgaming.setKodeBarang("LP0002");
        laptopgaming.setNamaBarang("Laptop Gaming");
        laptopgaming.setHargaBarang(20000000);
        laptopgaming.setDateCreated(new Date());
        laptopgaming.setLastModified(new Date());
        barangDao.save(laptopgaming);
    }

}