package com.example.imagestorage.data;

import com.example.imagestorage.model.Image;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ImgJdbcDao implements ImgDao {

    private final JdbcTemplate jdbcTemplate;

    public ImgJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(Image image) {
        String sql = "INSERT INTO Image(name,type,data) VALUES (?,?,?);";
        this.jdbcTemplate.update(sql, image.getName(), image.getType(), image.getData());
    }

    @Override
    public Image findByName(String name) {

        final String sql = "SELECT id,name,type,data from Image WHERE name = ?";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Image(
                    rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getBytes("data")
                ),name
        ).get(0);

    }

    public List<String> retrieveImageNames() {

        final String sql = "SELECT name from Image;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<String> imageNames = new ArrayList<>();

        while(results.next()) {
           imageNames.add(results.getString("name"));
        }

        return imageNames;
    }

}
