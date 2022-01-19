package jpabook.jpashop.domain;

import jpabook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Member {
    @Column(name = "member_id")
    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // order 테이블에 있는 member 필드에 의해 매핑
    private List<Order> orders = new ArrayList<>();

}
