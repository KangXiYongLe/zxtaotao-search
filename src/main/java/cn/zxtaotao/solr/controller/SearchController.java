package cn.zxtaotao.solr.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.zxtaotao.solr.bean.Item;
import cn.zxtaotao.solr.bean.SearchResult;
import cn.zxtaotao.solr.service.SearchService;

@Controller
public class SearchController {
    
    @Autowired
    private SearchService searchService;
    public static final Integer ROWS = Integer.valueOf(32);
    
    @RequestMapping(method = RequestMethod.GET, value = "search")
    public ModelAndView search(@RequestParam("q") String keyWords,
            @RequestParam(value = "page", defaultValue = "1") Integer page) {
        ModelAndView modelAndView = new ModelAndView("search");
        SearchResult searchResult = null;
        try {
            //解决get请求中文乱码问题
            keyWords = new String(keyWords.getBytes("ISO-8859-1"), "UTF-8");
            searchResult = this.searchService.search(keyWords,page,ROWS);
        } catch (Exception e) {
            e.printStackTrace();
            searchResult = new SearchResult(0L,new ArrayList<Item>(0));//如果查询出错，就创建一个空的返回结果
        }
        
        // 搜索关键字
        modelAndView.addObject("query", keyWords);
        
        // 搜索结果集
        modelAndView.addObject("itemList", searchResult.getList());

        // 当前页数
        modelAndView.addObject("page", page);
        
        // 总页数
        int total = searchResult.getTotal().intValue();
        int pages = total % ROWS == 0 ? total / ROWS : total / ROWS + 1;
        modelAndView.addObject("pages", pages);
        
        return modelAndView;
    }

}
