package com.example.Assignment.Controller;

import com.example.Assignment.Dao.FetchNumberDao;
import com.example.Assignment.Exception.AppException;
import com.example.Assignment.Model.Data;
import com.example.Assignment.Model.Response;
import com.example.Assignment.Services.FetchNumberService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/FetchNextNumber")
public class FetchNextNumber {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    FetchNumberService fetchNumberService;

    @Autowired
    FetchNumberDao fetchNumberDao;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Response fetchNextNumber(String categoryCode) throws Exception {
        Response response = new Response();
        try {
            logger.info(" request received for categoryCode = {}", categoryCode);
            // Validation
            if (Strings.isEmpty(categoryCode)) {
                logger.error("categoryCode object can not be null. categoryCode = {}  " + categoryCode);
                throw new AppException("categoryCode can not be null. user reg= " + categoryCode, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            Data data = fetchNumberDao.getValue(categoryCode);
            if(data==null){
                Thread.sleep(5000);
               String newValue = fetchNumberService.update(categoryCode);
               response.setOldValue("0");
               response.setNewValue(newValue);
                return response;
            }
            Thread.sleep(5000);
           String newValue = fetchNumberService.updates(categoryCode,data.getValue());
            response.setOldValue(data.getValue());
            response.setNewValue(newValue);
            return response;

        }catch (Exception e){
            logger.error("Error occurred while processing request for categoryCode = {}",categoryCode);
            throw e;
        }
    }
}
