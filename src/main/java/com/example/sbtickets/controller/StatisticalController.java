package com.example.sbtickets.controller;

import com.example.sbtickets.bean.TripBusBean;
import com.example.sbtickets.bean.WagesDriverBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.dao.WagesDriverDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatisticalController {
    private static final Logger logger = Logger.getLogger(StatisticalController.class);

    @Autowired
    WagesDriverDao wagesDriverDao;

    @RequestMapping(value = UrlConst.HOMEADIM.GET_WAGES_DRIVER, method = RequestMethod.PUT)
    public ResponseEntity<WrapperResponse> getListWages(@PathVariable("driverId") Integer driverId) {
        WrapperResponse result = new WrapperResponse();
        try {
            List<WagesDriverBean> listData = wagesDriverDao.getList(driverId);
            result.setStatus(HttpStatus.OK.value());
            result.setBody(listData);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }
}
