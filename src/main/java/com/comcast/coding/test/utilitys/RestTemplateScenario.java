package com.comcast.coding.test.utilitys;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.comcast.coding.test.common.Post;

/**
 * Class which consumes external Webservice
 * 
 * @author likhithkumarmatta
 *
 */
@Component
public class RestTemplateScenario {
	
    private static final Logger logger = LogManager.getLogger(RestTemplateScenario.class);

    /**
     * Method to consume external Rest API
     * @return
     */
    public Post consumeRestAPI() {
        logger.info(" Method: consumeRestAPI(), Stage: Started");
        RestTemplate restTemplate = new RestTemplate();
        Post postresponse = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", Post.class);
        logger.info(" Method: consumeRestAPI(), Stage: Ended");
        return postresponse;

    }
}
