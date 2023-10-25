package com.smk.cashier.dao;

import com.smk.cashier.model.Stok;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class StokDao implements Dao<Stok, Integer>{
    private final Optional<Connection> connection;

    public StokDao() {
        connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Stok> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Stok> stok = Optional.empty();
            String sql = "SELECT * from stok where stok_id = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    int idStok = rs.getInt("stok_id");
                    String kodeBarang = rs.getString("kode_barang");
                    int stokBarang = rs.getInt("stok_barang");

                    Stok stokResult = new Stok();
                    stokResult.setId(idStok);
                    stokResult.setKodeBarang(kodeBarang);
                    stokResult.setStokBarang(stokBarang);

                    stok = Optional.of(stokResult);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return stok;
        });
    }

    @Override
    public Collection<Stok> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Stok stok) {
        Stok nonNullBarang = Objects.requireNonNull(stok);
        String sql = "INSERT INTO Barang(kode_barang, stok_barang, created_by, updated_by, date_created, last_modified) VALUES(?,?,?,?,?,?)";
        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, stok.getKodeBarang());
                ps.setInt(2, stok.getStokBarang());
                ps.setString(3, stok.getCreatedBy());
                ps.setString(4, stok.getUpdatedBy());
                ps.setDate(5, new Date(stok.getDateCreated().getTime()));
                ps.setDate(6, new Date(stok.getLastModified().getTime()));
                int numberOfInsertRows = ps.executeUpdate();
                if (numberOfInsertRows > 0){
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()){
                        generatedId = Optional.of(rs.getInt(1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return generatedId;
        });
    }

    @Override
    public void update(Stok stok) {

    }

    @Override
    public void delete(Stok stok) {

    }
}