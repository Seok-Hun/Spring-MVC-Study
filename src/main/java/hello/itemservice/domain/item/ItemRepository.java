package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
// component 스캔 대상
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    // static 사용: 스프링에선 싱글톤이 기본이므로 의미 없지만, 이 외의 부분에서 사용시 필요
    // Item의 id가 Long 타입이므로 key를 Long 타입으로 지정
    private static long sequence = 0L;
    // 멀티 쓰레드 환경에서는 단순한 HashMap이나 long 타입을 사용하여 저장하면 값이 꼬일 수 있으므로 다른 타입을 써야 한다.

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
        // 바로 store.values를 반환할 수도 있다.
        // ArrayList로 store.values()를 감싸면서 store의 데이터 변경 가능성을 제거했다.
    }

    public void update(Long itemId, Item updateParam){
        // updateParam의 id가 사용되지 않으므로, Item에서 id를 제거한 ItemParamDTO 객체를 따로 만드는 식으로 하는 것이 좋다.
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
