package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PostTest {

    private RequestSpecification spec;

    @BeforeSuite
    public void setUp(){
        String baseURL = "https://jsonplaceholder.typicode.com/";

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void postVerificationTest(){
        Post expectedPost = new Post(1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
            //Commented block if no @AllArgsConstructor annotation
            /*expectedPost.setUserId(1).setId(1)
                    .setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                    .setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
*/
        Post actualPost = given().spec(spec).expect().statusCode(200).when().get("/posts/1").as(Post.class);
        assertEquals(actualPost, expectedPost);

    }

    @Test
    public void verifyNumberOfPostsWithUserIdTest(){
        String json = given().spec(spec).expect().statusCode(200).when().get("/posts").asString();
        JsonPath jp = new JsonPath(json);
        List<Post> posts = jp.getList("", Post.class);
        System.out.println(posts);
        int countPosts = 0;
        for (Post item : posts){
            int userIdAct = item.getUserId();
            if (userIdAct == 1){
               countPosts++;
            }
        }
        assertTrue(countPosts >= 10);
    }
}
