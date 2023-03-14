package life.hzzh.translate.proxy.youdao.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import life.hzzh.translate.model.TranslateReq;
import life.hzzh.translate.proxy.TranslateInterface;
import life.hzzh.translate.proxy.youdao.model.YouDaoTranslateReq;
import life.hzzh.translate.proxy.youdao.model.YouDaoTranslateResp;
import life.hzzh.translate.proxy.youdao.constants.YouDaoProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 文本翻译服务
 */
@Slf4j
@Service
public class YouDaoTranslateSupport implements TranslateInterface<List<String>> {
    @Resource
    private YouDaoProperties youDaoProperties;

    @Override
    public List<String> translate(TranslateReq req) {
        YouDaoTranslateReq youDaoTranslateReq = BeanUtil.copyProperties(youDaoProperties, YouDaoTranslateReq.class);
        youDaoTranslateReq.setQ(req.getQ());
        youDaoTranslateReq.setTo(req.getTo());
        youDaoTranslateReq.setFrom(req.getFrom());
        String signStr = youDaoTranslateReq.getAppKey()
                + truncate(youDaoTranslateReq.getQ())
                + youDaoTranslateReq.getSalt()
                + youDaoTranslateReq.getCurtime()
                + youDaoTranslateReq.getAppSecret();
        youDaoTranslateReq.setSign(SecureUtil.sha256(signStr));

        if (log.isInfoEnabled()) {
            log.info("translate request body:{}", youDaoTranslateReq);
        }


        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("from", youDaoTranslateReq.getFrom())
                .add("to", youDaoTranslateReq.getTo())
                .add("signType", youDaoTranslateReq.getSignType())
                .add("curtime", youDaoTranslateReq.getCurtime() + "")
                .add("appKey", youDaoTranslateReq.getAppKey())
                .add("q", youDaoTranslateReq.getQ())
                .add("salt", youDaoTranslateReq.getSalt())
                .add("sign", youDaoTranslateReq.getSign()).build();
        Request request = new Request.Builder().url(youDaoTranslateReq.getUrl()).post(requestBody).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String string = Objects.requireNonNull(response.body()).string();
                YouDaoTranslateResp youDaoTranslateResp = JSONObject.parseObject(string, YouDaoTranslateResp.class);
                return youDaoTranslateResp.getTranslation();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyList();
    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
}


