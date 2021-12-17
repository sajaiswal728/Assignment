package com.example.Assignment.Dao;

import com.example.Assignment.Model.Data;


public interface FetchNumberDao {
     Data getValue(String categoryCode);

     Data save(Data data);

     int update(Data data);
}
