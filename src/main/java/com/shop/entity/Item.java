package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // item 클래스를 entity 클래스로 선언
@Table(name="item2") // 테이블 생성 및 이름 item
@Getter
@Setter
@ToString
public class Item {

    @Id // 기본키 생성
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본 설정 값 (null 값이 아니며 유일하며 변하면 안된다.)
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50) // nullable = not null 을 말하며 상품명의 경우에는 항상 값이 있어야 하는 핃드
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob // Large Object 의 줄인말로 사용 지정하지 않으면 값이 255까지라서 사진을 저장하는 컬럼으로 하용할 경우 더 많은 자리수를 사용하기 때문에 사용
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING) // enum 값을 엔티티로 바꿔서 사용하게 해주는 어노테이션
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    private LocalDateTime regTime; // 등록시간

    private LocalDateTime updateTime; // 수정시간





}