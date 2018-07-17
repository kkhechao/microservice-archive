package com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo;

public class GeneArchiveDmoWithBLOBs extends GeneArchiveDmo {
    private String detectionData;

    private String suggest;

    public String getDetectionData() {
        return detectionData;
    }

    public void setDetectionData(String detectionData) {
        this.detectionData = detectionData == null ? null : detectionData.trim();
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
    }
}