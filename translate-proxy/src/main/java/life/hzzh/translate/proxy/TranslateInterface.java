package life.hzzh.translate.proxy;

import life.hzzh.translate.model.TranslateReq;

import java.util.Map;

public interface TranslateInterface<T> {
    T translate(TranslateReq req);
}
