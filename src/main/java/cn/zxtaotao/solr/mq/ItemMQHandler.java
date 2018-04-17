package cn.zxtaotao.solr.mq;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zxtaotao.solr.bean.Item;
import cn.zxtaotao.solr.service.ItemService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemMQHandler {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    @Autowired
    private HttpSolrServer httpSolrServer;
    
    @Autowired
    private ItemService itemService;

    public void execute(String msg){
        try {
            JsonNode jsonNode = MAPPER.readTree(msg);
            Long itemId = jsonNode.get("itemId").asLong();
            String type = jsonNode.get("type").asText();
            if(StringUtils.equals(type, "update")||StringUtils.equals(type, "insert")){
                Item item = this.itemService.queryById(itemId);
                this.httpSolrServer.addBean(item);//将新的item加入solr全文检索
                this.httpSolrServer.commit();//提交
            }else if(StringUtils.equals(type, "delete")){
                this.httpSolrServer.deleteById(itemId.toString());//通过id从全文检索中删除该商品信息
                this.httpSolrServer.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
