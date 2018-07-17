package com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo;

public class DetectItemDmo {
    private String geneArchiveId;

    private String disease;

    private String risk;

    private String locusInfo;

    public String getGeneArchiveId() {
        return geneArchiveId;
    }

    public void setGeneArchiveId(String geneArchiveId) {
        this.geneArchiveId = geneArchiveId == null ? null : geneArchiveId.trim();
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease == null ? null : disease.trim();
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk == null ? null : risk.trim();
    }

    public String getLocusInfo() {
        return locusInfo;
    }

    public void setLocusInfo(String locusInfo) {
        this.locusInfo = locusInfo == null ? null : locusInfo.trim();
    }
}