package cn.zxtaotao.solr.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.zxtaotao.common.service.ApiService;
import cn.zxtaotao.solr.bean.Item;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ItemService {

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_MANAGE_URL}")
    private String TAOTAO_MANAGE_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Item queryById(Long itemId) {
        try {
            String url = this.TAOTAO_MANAGE_URL + "/rest/api/item/" + itemId;
            //请求后台管理系统接口服务，获取商品数据
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return (Item) MAPPER.readValue(jsonData, Item.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
