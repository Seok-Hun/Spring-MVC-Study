package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
        // 뷰의 논리 이름
    }

    /**
     * 상품 상세 컨트롤러
     * @param itemId 조회할 상품의 ID, @PathVariable로 URL에서 얻는다.
     * @param model 비어있는 model
     * @return basic/item - 뷰의 논리 이름
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 상품 등록 폼 컨트롤러
     * 단순히 뷰 템플릿만 호출
     * @return basic/addForm
     */
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    /**
     * 상품 등록 컨트롤러 - @RequestParam
     * @RequestParam으로 상품 등록
     * @return basic/item (상품 상세 보기와 같은 뷰 템플릿)
     */
    //@PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        // key가 item 문자열이고, value가 item 객체

        return "basic/item";
        // 상품 저장 후 저장된 내용을 보기 위해 상세 보기 뷰 템플릿을 사용
        // model 데이터 사용
    }

    /**
     * 상품 등록 컨트롤러 - @ModelAttribute
     * @ModelAttribute로 상품 등록
     * @return basic/item (상품 상세 보기와 같은 뷰 템플릿)
     */
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){

        // item 파라미터가 생성되는 과정은 아래 과정과 동일하다.
        // Item item = new Item();
        // item.setItemName(itemName);
        // item.setPrice(price);
        // item.setQuantity(quantity);

        itemRepository.save(item);

        // @ModelAttribute("item") 코드가 아래 코드와 동일한 역할이다.
        // model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * 상품 등록 컨트롤러 - @ModelAttribute 이름 생략
     * @ModelAttribute의 이름을 생략한다.
     * @return basic/item (상품 상세 보기와 같은 뷰 템플릿)
     */
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){

        itemRepository.save(item);

        return "basic/item";
    }

    /**
     * 상품 등록 컨트롤러 - @ModelAttribute 전체 생략
     * @ModelAttribute 전체를 생략한다..
     * @return basic/item (상품 상세 보기와 같은 뷰 템플릿)
     */
    @PostMapping("/add")
    public String addItemV4(Item item){

        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * 상품 등록 컨트롤러 - PRG 패턴 도입
     * POST - Redirect - GET 패턴을 사용한다.
     * @return 등록한 상품의 상세 페이지 경로로 redirect
     */
    @PostMapping("/add")
    public String addItemV5(Item item){

        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    /**
     * 상품 수정 폼 컨트롤러
     * @param itemId
     * @param model
     * @return 상품 수정 뷰 템플릿
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(
            @PathVariable Long itemId,
            Model model
    ){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);

        return "basic/editForm";
    }

    /**
     * 상품 수정 컨트롤러
     * @param itemId
     * @param item
     * @return 상품 상세 정보 경로로 redirect
     */
    @PostMapping("/{itemId}/edit")
    public String edit(
            @PathVariable Long itemId,
            @ModelAttribute Item item
    ){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
        // 뷰 템플릿 호출 대신 상품 상세 화면으로 이동하도록 리다이렉트 호출
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}