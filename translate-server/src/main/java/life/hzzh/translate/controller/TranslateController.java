package life.hzzh.translate.controller;

import life.hzzh.translate.model.TranslateReq;
import life.hzzh.translate.proxy.youdao.support.YouDaoTranslateSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/translate")
@RestController
public class TranslateController {

    @Resource
    private YouDaoTranslateSupport youDaoTranslateSupport;

    @PostMapping("/text.json")
    public ResponseEntity<List<String>> transText(@RequestBody TranslateReq req) {
        List<String> translate = youDaoTranslateSupport.translate(req);
        return ResponseEntity.ok(translate);
    }

}
