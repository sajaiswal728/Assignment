package com.example.Assignment.Services;

import com.example.Assignment.Dao.FetchNumberDao;
import com.example.Assignment.Model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchNumberService {

    @Autowired
    FetchNumberDao fetchNumberDao;
    public String update(String categoryCode) {

        int temp_sum =0;
        int count =0;
        while (categoryCode.length()>1)
        {
            temp_sum = 0;
            for(int i=0;i<categoryCode.length();i++)
                temp_sum += (categoryCode.charAt(i)-'0');
            categoryCode = temp_sum + " ";
            count++;
        }

        Data data = new Data();
        data.setCategoryCode(categoryCode);
        data.setValue(String.valueOf(count));
        fetchNumberDao.save(data);
        return String.valueOf(count);
    }

    public String updates(String categoryCode, String value) {
        int temp_sum =0;
        int count =0;
        while (categoryCode.length()>1)
        {
            temp_sum = 0;
            for(int i=0;i<categoryCode.length();i++)
                temp_sum += (categoryCode.charAt(i)-'0');
            categoryCode = temp_sum + " ";
            count++;
        }

        if(Integer.valueOf(value)<count)
        {
        Data data = new Data();
        data.setCategoryCode(categoryCode);
        data.setValue(String.valueOf(count));
            fetchNumberDao.update(data);
        }
        return String.valueOf(count);
    }
}
