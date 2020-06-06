package com.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easyexcel.event.RegisterEvent;
import com.easyexcel.exception.ExcelException;
import com.easyexcel.pojo.param.DictReadParam;
import com.easyexcel.pojo.param.DictReadParamError;
import com.easyexcel.pojo.param.DictWeiteParam;
import com.easyexcel.pojo.param.model.Dict;
import com.easyexcel.provider.RedisService;
import com.easyexcel.response.ResponseEnum;
import com.easyexcel.service.ExcelService;
import com.easyexcel.util.ReadExcel;
import com.easyexcel.util.ReadExcelListener;
import com.easyexcel.util.RequestUtils;
import com.easyexcel.util.WriteExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mr丶s
 * @date 2020/6/6 1:35 下午
 * @description
 */
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {


    @Autowired
    private RedisService redisService;

    @Override
    public void writeExcel(HttpServletResponse response) {
        List<DictWeiteParam> dictWeiteParamList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DictWeiteParam dictWeiteParam = new DictWeiteParam();
            dictWeiteParam.setKey("字符串" + i);
            dictWeiteParam.setType("类型" + i);
            dictWeiteParam.setFuncAlias("dddd");
            dictWeiteParamList.add(dictWeiteParam);
        }
        WriteExcel.simpleWrite(DictWeiteParam.class, dictWeiteParamList, "文档", response);

    }

    @Override
    public void readExcel(MultipartFile excelFile) {
        try {
            if (Objects.isNull(excelFile)) {
                throw new ExcelException(ResponseEnum.RESULT_FILE_IS_NULL);
            }
            if (!ReadExcel.isExcelFile(excelFile)) {
                throw new ExcelException(ResponseEnum.RESULT_FILE_NOT_SUPPORT_TYPE);
            }
            DictReadParam dictReadParam = new DictReadParam();
            ReadExcel.readExcel(excelFile, dictReadParam.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 导入数据处理
     *
     * @param registerEvent
     */
    @EventListener
    public void importUserData(RegisterEvent registerEvent) {
        // 调取获取事件
        String key = registerEvent.getKey();
       log.info("key ---> {}", key);

        // 通过时间消息，获取数据
        Object data = redisService.get(key);

        List<DictReadParam> importData = JSONObject.parseArray(JSON.toJSONString(data), DictReadParam.class);
        // 即有错误信息时，处理
        List<DictReadParamError> errorList = new ArrayList<>(importData.size());

        if (!CollectionUtils.isEmpty(importData)) {

            StringBuilder builder = new StringBuilder();


            List<Dict> dictList = new ArrayList<>();

            for (DictReadParam e : importData) {
                Dict dict = new Dict();


                String type = e.getType();
                if (StringUtils.isEmpty(type)) {
                    builder.append(new String("类型为空".getBytes(Charset.defaultCharset()))).append("\r\n");
                } else {
                    dict.setType(type);
                }

                String value = e.getValue();
                if (StringUtils.isEmpty(value)) {
                    builder.append("数据为空").append("\r\n");
                } else {
                    dict.setValue(value);
                }

                String result = builder.toString();
                // 错误信息不为空时，保存当前对象到待插入集合中等待执行批量新增操作
                if (StringUtils.isBlank(result)) {
                    dictList.add(dict);
                } else {
                    DictReadParamError errorVO = new DictReadParamError();
                    errorVO.setErrorMsg(builder.toString());
                    // 若有错误数据时，才进行时间拷贝
                    BeanUtils.copyProperties(e, errorVO);
                    errorList.add(errorVO);
                    builder.setLength(0);
                }
            }
            log.info("正确信息:,{}", dictList);
            // 导入信息正确的插入
            if (!CollectionUtils.isEmpty(dictList)) {
                //正确信息批量插入
            }

            log.info("错误返回111：,{}", errorList);

            // 有错误的导出到Excel
            if (!CollectionUtils.isEmpty(errorList)) {
                log.info("错误返回222：,{}", errorList);
                WriteExcel.simpleWrite(DictReadParamError.class, errorList, "文档", RequestUtils.getHttpServletResponse());
            }
        }
    }


}
