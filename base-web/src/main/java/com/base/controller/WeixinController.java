package com.base.controller;

import com.base.PrintUtils;
import com.base.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zkdu on 2015/1/9.
 */
@Controller
@RequestMapping(value = "weixin")
public class WeixinController {

    @RequestMapping(value = "/{id}")
    public void getAccessToken(@PathVariable String id) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd13a9b90d1d7b7d9&secret=4dc1ae4646dcfaabb2ff48d77034a002";
        String res = HttpUtil.getInstance().getAndReceive(url,null);
        PrintUtils.print(res);
    }

}
