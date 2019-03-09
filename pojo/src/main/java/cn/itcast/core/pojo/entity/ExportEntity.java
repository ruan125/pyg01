package cn.itcast.core.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExportEntity implements Serializable{
    //订单id
    private Long orderId;

    //商品id
    private Long id;
    //商品名称
    private String goods_name;
    //商品价格
    private BigDecimal price;
    //商品购买数量
    private Integer num;

    //一级分类
    private Long category1Id;
    //二级分类
    private Long category2Id;
    //三级分类
    private Long category3Id;

    public ExportEntity() {
    }

    public ExportEntity(Long orderId, String sellerId, Long id, String goods_name, BigDecimal price, Integer num, BigDecimal totalFee, Long category1Id, Long category2Id, Long category3Id) {
        this.orderId = orderId;

        this.id = id;
        this.goods_name = goods_name;
        this.price = price;
        this.num = num;
        this.category1Id = category1Id;
        this.category2Id = category2Id;
        this.category3Id = category3Id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }



    public Long getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public Long getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Long category3Id) {
        this.category3Id = category3Id;
    }

    @Override
    public String toString() {
        return "ExportEntity{" +
                "orderId=" + orderId +
                ", id=" + id +
                ", goods_name='" + goods_name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", category1Id=" + category1Id +
                ", category2Id=" + category2Id +
                ", category3Id=" + category3Id +
                '}';
    }
}
