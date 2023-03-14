package life.hzzh.translate.proxy.youdao.model;

import cn.hutool.core.date.DateUtil;
import life.hzzh.translate.model.BaseTranslateReq;
import lombok.Data;

import java.util.UUID;

@Data
public class YouDaoTranslateReq extends BaseTranslateReq {

    private String url;

    private String appKey;

    private String appSecret;

    private final String salt = UUID.randomUUID().toString();

    private String sign;

    private final String signType = "v3";

    private final long curtime = DateUtil.currentSeconds();

    @Override
    public String toString() {
        return "YouDaoTranslateRequest{" +
                "url='" + url + '\'' +
                ", appKey='" + appKey + '\'' +
                ", salt='" + salt + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", curtime=" + curtime +
                '}';
    }
}
