package com.easyexcel.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import com.easyexcel.consts.ExcelConsts;
import com.easyexcel.event.ServiceEvent;
import com.easyexcel.exception.ExcelException;
import com.easyexcel.provider.RedisService;
import com.easyexcel.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr丶s
 * @date 2020/6/3 2:24 下午
 * @description
 */
@Slf4j
@Component
public class ReadExcelListener<T> extends AnalysisEventListener<T> {

    @Override
    public void invoke(T data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);
        datas.add(data);
    }

    private List<T> datas = new ArrayList<>();

    public List<T> getData() {
        return datas;
    }

    private static ReadExcelListener readExcelListener;

    @Autowired
    private RedisService redisService;
    @Autowired
    private ServiceEvent serviceEvent;

    @PostConstruct
    public void init() {
        readExcelListener = this;
        readExcelListener.redisService = this.redisService;
        readExcelListener.serviceEvent = this.serviceEvent;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // if (datas.size() == 0) {
        //     throw new ExcelException(222, "文件不能为空");
        // }
        // 文件超过最大限制
        if (datas.size() > ExcelConsts.MAX_DATA_SIZE) {
            throw new ExcelException(ResponseEnum.RESULT_EXCEL_DATA_SIZE_OVERFLOW);
        }

        log.info("datas,{}", JSONObject.toJSONString(datas));
        String key = "excel:import:" + GeneralUtils.randomChar(10);
        log.info("导入数据[{}]条", datas.size());
        readExcelListener.redisService.save(key, this.datas, 20);
        readExcelListener.serviceEvent.register(key);
        log.info("导入数据key,{}", key);
        this.datas.clear();
        log.info("数据解析完毕！！！！");
    }


    /**
     * 去除空格
     *
     * @param deptArrays
     * @return
     */
    private String[] trim(String[] deptArrays) {
        String[] tempArrays = new String[deptArrays.length];
        for (int i = 0; i < deptArrays.length; i++) {
            tempArrays[i] = deptArrays[i].trim();
        }
        return tempArrays;
    }
}
