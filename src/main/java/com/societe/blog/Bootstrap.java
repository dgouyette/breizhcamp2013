package com.societe.blog;


import com.societe.blog.dao.TalkDao;
import com.societe.blog.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    protected Resource talkResource;

    @Autowired
    private TalkDao talkDao;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        LOGGER.debug("*******************************************************************");
        LOGGER.debug("***                                                            ****");
        LOGGER.debug("***      Chargement des Talk                                   ****");
        LOGGER.debug("***                                                            ****");
        LOGGER.debug("*******************************************************************");

        talkDao.save(JsonHelper.extractTalksFromJson(talkResource));


    }

    public void setTalkResource(Resource talkResource) {
        this.talkResource = talkResource;
    }
}


