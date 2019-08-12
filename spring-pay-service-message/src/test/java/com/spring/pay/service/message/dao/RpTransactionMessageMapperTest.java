package com.spring.pay.service.message.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.pay.PayApplication;
import com.spring.pay.common.core.enums.PublicEnum;
import com.spring.pay.common.core.utils.StringUtil;
import com.spring.pay.service.message.enums.MessageStatusEnum;
import com.spring.pay.service.message.model.RpTransactionMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PayApplication.class)
public class RpTransactionMessageMapperTest {

    @Autowired
    private RpTransactionMessageMapper rpTransactionMessageMapper;

    @Test
    public void testInsert() {

        RpTransactionMessage transactionMessage = new RpTransactionMessage();
        transactionMessage.setAreadlyDead(PublicEnum.YES.getId());
        transactionMessage.setConsumerQueue("consumerQueue");
        transactionMessage.setMessageBody("messageBody");
        transactionMessage.setMessageDataType("messageDataType");
        transactionMessage.setMessageId("messageId");
        transactionMessage.setMessageSendTimes(2);
        transactionMessage.setCreater("creater");
        transactionMessage.setEditor("editor");
        transactionMessage.setId(StringUtil.get32UUID());
        transactionMessage.setRemark("remark");
        transactionMessage.setStatus(MessageStatusEnum.SENDING.getId());

        rpTransactionMessageMapper.insert(transactionMessage);
    }

    @Test
    public void testGetById() {
        String id = "251b725acef24ee29cbd46411ffe8599";
        RpTransactionMessage rpTransactionMessage = rpTransactionMessageMapper.getById(id);
        System.out.println(rpTransactionMessage);
    }

    @Test
    public void testListPage() {
        Map<String, Object> map = new HashMap<>();
        map.put("areadlyDead",PublicEnum.YES.getId());
        PageHelper.startPage(1, 5);
        List<RpTransactionMessage> rpTransactionMessages = rpTransactionMessageMapper.listPage(map);
        PageInfo<RpTransactionMessage> rpTransactionMessagePageInfo = new PageInfo<>(rpTransactionMessages);
//        PageHelper.clearPage();
        System.out.println(rpTransactionMessages.size());
        System.out.println(rpTransactionMessagePageInfo);
    }
}
