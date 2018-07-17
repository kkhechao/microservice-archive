package com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo;

public class BodyInfoDmo {
    private String basicArchiveId;

    private String height;

    private String weight;

    private String birthday;

    public String getBasicArchiveId() {
        return basicArchiveId;
    }

    public void setBasicArchiveId(String basicArchiveId) {
        this.basicArchiveId = basicArchiveId == null ? null : basicArchiveId.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }
}