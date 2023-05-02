package com.example.smartcity1.json;

import java.util.List;

public class BannerJson {

    /**
     * code :  200
     * msg : 查询成功
     * rows : [{"id":14,"sort":1,"advTitle":"测试首页轮播","advImg":"http://152.136.210.130:7777/profile/upload/image/202 1/04/26/183e63c6-a59d-4551-a5b4-7055ff7a9575.jpg","servModule":"新闻","targetId":1,"type":"2"}]
     * total : 1
     */

    private String code;
    private String msg;
    private Integer total;
    private List<RowsBean> rows;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 14
         * sort : 1
         * advTitle : 测试首页轮播
         * advImg : http://152.136.210.130:7777/profile/upload/image/202 1/04/26/183e63c6-a59d-4551-a5b4-7055ff7a9575.jpg
         * servModule : 新闻
         * targetId : 1
         * type : 2
         */

        private int id;
        private int sort;
        private String advTitle;
        private String advImg;
        private String servModule;
        private int targetId;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getAdvTitle() {
            return advTitle;
        }

        public void setAdvTitle(String advTitle) {
            this.advTitle = advTitle;
        }

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }

        public String getServModule() {
            return servModule;
        }

        public void setServModule(String servModule) {
            this.servModule = servModule;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
