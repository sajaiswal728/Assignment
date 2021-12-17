package com.example.Assignment.DaoImpl;

import com.example.Assignment.Dao.FetchNumberDao;
import com.example.Assignment.Model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class FetchNumberDaoImpl implements FetchNumberDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String tableName = "ASSIGN.DATA";
    private final String getCCode = "SELECT * FROM " + tableName + " WHERE CATEGORY_CODE=?";
    private final String save = "INSERT INTO " + tableName + " (CATEGORY_CODE,C_VALUES,UPDATED_DATE ,UPDATED_TS ) VALUES(?,?,CURRENT_DATE(),CURRENT_TIMESTAMP())";
    private final String update = "UPDATE " + tableName + " SET C_VALUES = ?, WHERE CATEGORY_CODE=?";


    public static RowMapper<Data> userMapper = (rs, rowNum) ->
            new Data(rs.getLong("ID"), rs.getString("CATEGORY_CODE"),rs.getString("C_VALUES"),
                    rs.getDate("UPDATED_DATE"),
                    rs.getTimestamp("UPDATED_TS")
            );
    @Override
    public Data getValue(String categoryCode) {
        Data data = jdbcTemplate.queryForStream(getCCode
                ,userMapper
                ,new Object[]{categoryCode})
                .findFirst()
                .orElse(null);
        return data;
    }

    @Override
    public Data save(Data data) {
        jdbcTemplate.update(save, data.getCategoryCode(),data.getValue());
        return data;
    }

    @Override
    public int update(Data data) {
        return jdbcTemplate.update(update,data.getCategoryCode(),data.getValue() );

    }
}
