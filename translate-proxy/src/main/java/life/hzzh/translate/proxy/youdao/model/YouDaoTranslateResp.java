package life.hzzh.translate.proxy.youdao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * <a href="https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html">有道API文档</a>
 */
@Data
public class YouDaoTranslateResp implements Serializable {

    private String errorCode;

    private String query;

    private List<String> translation;

    private String basic;

    private List<String> web;

    private String l;

    private String dict;

    private String webdict;

    private String tSpeakUrl;

    private String speakUrl;

    private List<String> returnPhrase;
}
