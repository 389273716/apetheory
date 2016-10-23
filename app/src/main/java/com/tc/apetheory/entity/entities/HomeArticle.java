package com.tc.apetheory.entity.entities;

/**
 * @author tc
 * @version 1.0
 * @date 2016/4/4 13:32
 * @description
 */
public class HomeArticle {
    private String id;//int	可为null	data.文章ID 此为主键。article.id
    private String tags;//string	否	data.标签
    private String title;//string	否	data.文章标题
    private String titleDate;//	string	否	data.文章发布时间
    private String inputDate;//string	否	data.导入时间
    private String url;//	文章url
    private String name;//类型名称
    private String imgUrl;//图片地址
    private String digest;//文章简介
    private String authorId;//	作者id
    private int typeId;//	类型表的id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDate() {
        return titleDate;
    }

    public void setTitleDate(String titleDate) {
        this.titleDate = titleDate;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
