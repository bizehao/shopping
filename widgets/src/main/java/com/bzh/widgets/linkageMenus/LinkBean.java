package com.bzh.widgets.linkageMenus;

import java.util.List;

/**
 * @author 毕泽浩
 * @Description:
 * @time 2018/11/11 18:48
 */
public class LinkBean {
    private String typeName;

    private List<InnerType> innerTypes;

    public LinkBean(String typeName, List<InnerType> innerTypes) {
        this.typeName = typeName;
        this.innerTypes = innerTypes;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<InnerType> getInnerTypes() {
        return innerTypes;
    }

    public void setInnerTypes(List<InnerType> innerTypes) {
        this.innerTypes = innerTypes;
    }

    public static class InnerType{
        private String innerTypeName;
        private int image;

        public InnerType(String innerTypeName, int image) {
            this.innerTypeName = innerTypeName;
            this.image = image;
        }

        public String getInnerTypeName() {
            return innerTypeName;
        }

        public void setInnerTypeName(String innerTypeName) {
            this.innerTypeName = innerTypeName;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "InnerType{" +
                    "innerTypeName='" + innerTypeName + '\'' +
                    ", image=" + image +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LinkBean{" +
                "typeName='" + typeName + '\'' +
                ", innerTypes=" + innerTypes +
                '}';
    }
}
