package jpabook.jpashop.domain;

import hellojpa.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    // 주문자의 정보를 알아오기 위해 memberId를 fk로 가져온다.
    //    @Column(name = "MEMBER_ID")
    //    private Long memberId;
    // 연관관계로 변경하기 : member와 order는 1:N 관계이다. order가 연관관계의 주인이 된다.
    @ManyToOne
    @JoinColumn(name = "member_id")
    private ShopMember member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopMember getMember() {
        return member;
    }

    public void setMember(ShopMember member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
