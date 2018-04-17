package cn.zxtaotao.solr.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @Field("id")
    private Long id;

    @Field("title")
    private String title;

    @Field("sellPoint")
    private String sellPoint;

    @Field("price")
    private Long price;

    @Field("image")
    private String image;

    @Field("cid")
    private Long cid;

    @Field("status")
    private Integer status;

    @Field("created")
    private Long created;

    @Field("updated")
    private Long updated;

    public Long getCreated() {
        return this.created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return this.updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return this.sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCid() {
        return this.cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String[] getImages() {
        return StringUtils.split(getImage(), ',');
    }

    public String toString() {
        return "Item [id=" + this.id + ", title=" + this.title + ", sellPoint=" + this.sellPoint + ", price="
                + this.price + ", image=" + this.image + ", cid=" + this.cid + ", status=" + this.status
                + "]";
    }
}
