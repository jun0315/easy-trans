package com.baidu.translate.demo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TranThread extends Thread {
    private String inupt;
    private String output;
    private TransApi transApi;
    private String fromLanguage;
    private String toLanguage;


    public TranThread(String inupt, String fromLanguage, String toLanguage) {
        this.inupt = inupt;
        this.transApi = new TransApi();
        this.fromLanguage = transApi.spanChange(fromLanguage);
        this.toLanguage = transApi.spanChange(toLanguage);
    }

    @Override
    public void run() {
        String result = transApi.getTransResult(this.inupt, fromLanguage, toLanguage);
        JSONObject jsonObject = JSONObject.fromObject(result);

        this.output = (String) (((JSONObject) ((JSONArray) jsonObject.get("trans_result")).get(0)).get("dst"));
    }

    public String getInupt() {
        return inupt;
    }

    public void setInupt(String inupt) {
        this.inupt = inupt;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
