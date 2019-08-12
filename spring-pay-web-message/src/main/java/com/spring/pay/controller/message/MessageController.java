package com.spring.pay.controller.message;

import com.github.pagehelper.PageInfo;
import com.spring.pay.common.core.model.PageParam;
import com.spring.pay.common.core.model.Result;
import com.spring.pay.common.core.utils.ResultUtils;
import com.spring.pay.service.message.api.RpTransactionMessageService;
import com.spring.pay.service.message.model.RpTransactionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private RpTransactionMessageService rpTransactionMessageService;

    @RequestMapping(value = "/list")
    public Result<Object> list(PageParam<RpTransactionMessage> pageParam) {

        PageInfo<RpTransactionMessage> rpTransactionMessagePageInfo = rpTransactionMessageService.listPage(pageParam);

        return ResultUtils.success(rpTransactionMessagePageInfo);
    }

}
