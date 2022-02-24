package com.shop.repository;

import com.shop.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> ,
QuerydslPredicateExecutor<Item>{

    List<Item> findByItemNm(String itemNm); // 상품명으로 데이터를 조회하기 위한 코드

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail); // 상품을 상품명과 상품 상세 설명을 or 조건을 이용하여 조회하는 쿼리 메서드

    List<Item> findByPriceLessThan(Integer price); // 파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리 메소드

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); // 가격 내림차순으로 볼수 있는 OrderBy + 속성명 +Desc , 오름차순 Asc

    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")  // item 으로 부터 데티어를 select 하겠다는 것
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); // 파라미터로 넘어온값을 현재 itemDetail 를 like % % 사이에 ":itemDetail" 로 값이 들어가도록 해주도록 작성

    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true) // nativeQuery 를 사용하면 기존의 쿼리를 사용할 수 있지만 독립성을 잃어버리기 때문에 복작한 쿼리를 그대로 사용해야 하는경우 활용할 수 있게 해줌
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);





}