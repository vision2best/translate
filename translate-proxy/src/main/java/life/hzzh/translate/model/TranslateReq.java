package life.hzzh.translate.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TranslateReq implements Serializable {
    /**
     * 待输入的文字
     */
    private String q;

    /**
     * 源语言
     */
    private String from;

    /**
     * 目标语言
     */
    private String to;
}
