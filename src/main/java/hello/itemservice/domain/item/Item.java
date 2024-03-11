package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
// @Data는 너무 많은 접근 상황을 만드므로 핵심 도메인에서 사용하기엔 위험하다.
// DTO 처럼 단순한 데이터 이동 등에서 사용
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    // 변수값이 null 이 될 수도 있을 경우 int 대신 Integer 사용
    // int이면 null 불가

    public Item(){
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
