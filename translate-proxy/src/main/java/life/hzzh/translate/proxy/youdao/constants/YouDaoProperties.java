package life.hzzh.translate.proxy.youdao.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class YouDaoProperties {

    @Value("${translate.you_dao.url:https://openapi.youdao.com/api}")
    private String url;

    @Value("${translate.you_dao.app_key}")
    private String appKey;

    @Value("${translate.you_dao.app_secret}")
    private String appSecret;
}
