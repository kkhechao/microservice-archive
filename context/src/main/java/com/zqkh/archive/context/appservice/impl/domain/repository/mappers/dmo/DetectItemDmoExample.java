package com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo;

import java.util.ArrayList;
import java.util.List;

public class DetectItemDmoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DetectItemDmoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andGeneArchiveIdIsNull() {
            addCriterion("gene_archive_id is null");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdIsNotNull() {
            addCriterion("gene_archive_id is not null");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdEqualTo(String value) {
            addCriterion("gene_archive_id =", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdNotEqualTo(String value) {
            addCriterion("gene_archive_id <>", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdGreaterThan(String value) {
            addCriterion("gene_archive_id >", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("gene_archive_id >=", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdLessThan(String value) {
            addCriterion("gene_archive_id <", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdLessThanOrEqualTo(String value) {
            addCriterion("gene_archive_id <=", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdLike(String value) {
            addCriterion("gene_archive_id like", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdNotLike(String value) {
            addCriterion("gene_archive_id not like", value, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdIn(List<String> values) {
            addCriterion("gene_archive_id in", values, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdNotIn(List<String> values) {
            addCriterion("gene_archive_id not in", values, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdBetween(String value1, String value2) {
            addCriterion("gene_archive_id between", value1, value2, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andGeneArchiveIdNotBetween(String value1, String value2) {
            addCriterion("gene_archive_id not between", value1, value2, "geneArchiveId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIsNull() {
            addCriterion("disease is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseIsNotNull() {
            addCriterion("disease is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseEqualTo(String value) {
            addCriterion("disease =", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseNotEqualTo(String value) {
            addCriterion("disease <>", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseGreaterThan(String value) {
            addCriterion("disease >", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseGreaterThanOrEqualTo(String value) {
            addCriterion("disease >=", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseLessThan(String value) {
            addCriterion("disease <", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseLessThanOrEqualTo(String value) {
            addCriterion("disease <=", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseLike(String value) {
            addCriterion("disease like", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseNotLike(String value) {
            addCriterion("disease not like", value, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseIn(List<String> values) {
            addCriterion("disease in", values, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseNotIn(List<String> values) {
            addCriterion("disease not in", values, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseBetween(String value1, String value2) {
            addCriterion("disease between", value1, value2, "disease");
            return (Criteria) this;
        }

        public Criteria andDiseaseNotBetween(String value1, String value2) {
            addCriterion("disease not between", value1, value2, "disease");
            return (Criteria) this;
        }

        public Criteria andRiskIsNull() {
            addCriterion("risk is null");
            return (Criteria) this;
        }

        public Criteria andRiskIsNotNull() {
            addCriterion("risk is not null");
            return (Criteria) this;
        }

        public Criteria andRiskEqualTo(String value) {
            addCriterion("risk =", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotEqualTo(String value) {
            addCriterion("risk <>", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskGreaterThan(String value) {
            addCriterion("risk >", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskGreaterThanOrEqualTo(String value) {
            addCriterion("risk >=", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLessThan(String value) {
            addCriterion("risk <", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLessThanOrEqualTo(String value) {
            addCriterion("risk <=", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLike(String value) {
            addCriterion("risk like", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotLike(String value) {
            addCriterion("risk not like", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskIn(List<String> values) {
            addCriterion("risk in", values, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotIn(List<String> values) {
            addCriterion("risk not in", values, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskBetween(String value1, String value2) {
            addCriterion("risk between", value1, value2, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotBetween(String value1, String value2) {
            addCriterion("risk not between", value1, value2, "risk");
            return (Criteria) this;
        }

        public Criteria andLocusInfoIsNull() {
            addCriterion("locus_info is null");
            return (Criteria) this;
        }

        public Criteria andLocusInfoIsNotNull() {
            addCriterion("locus_info is not null");
            return (Criteria) this;
        }

        public Criteria andLocusInfoEqualTo(String value) {
            addCriterion("locus_info =", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoNotEqualTo(String value) {
            addCriterion("locus_info <>", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoGreaterThan(String value) {
            addCriterion("locus_info >", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoGreaterThanOrEqualTo(String value) {
            addCriterion("locus_info >=", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoLessThan(String value) {
            addCriterion("locus_info <", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoLessThanOrEqualTo(String value) {
            addCriterion("locus_info <=", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoLike(String value) {
            addCriterion("locus_info like", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoNotLike(String value) {
            addCriterion("locus_info not like", value, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoIn(List<String> values) {
            addCriterion("locus_info in", values, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoNotIn(List<String> values) {
            addCriterion("locus_info not in", values, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoBetween(String value1, String value2) {
            addCriterion("locus_info between", value1, value2, "locusInfo");
            return (Criteria) this;
        }

        public Criteria andLocusInfoNotBetween(String value1, String value2) {
            addCriterion("locus_info not between", value1, value2, "locusInfo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}