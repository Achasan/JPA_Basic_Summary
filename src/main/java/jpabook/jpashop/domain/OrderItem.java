package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ITEM_ID")
    private Long itemId;

    private int orderprice;

    private int count;

    /**
     * 외래키들을 보자, 각 테이블의 PK를 외래키로 삼아서 외래키를 통해 객체를 호출할 수 있다.
     * 이는 지극히 관계형 DB에 맞춰서 코드를 설계한 것이다. 객체지형적이지 않아서 JPA가 가지는 메리트를 가질 수 없는 코드이다.
     * PK를 필드로 놓지말고 아예 클래스 자체(객체)를 필드로 지정하여 사용하면 객체지향적인 코드가 될 수 있지 않을까?
     * 이것이 연관관계라는 개념의 시작이 된다.
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(int orderprice) {
        this.orderprice = orderprice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
