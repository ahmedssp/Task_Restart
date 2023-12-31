package ApiTest;
import Deserialization.Activity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ActivityApiTest {
    private static final String BASE_URL = "https://fakerestapi.azurewebsites.net";
    private String PathParameter = "/api/v1/Activities";

    @Test
    public void Get_request() {
        SoftAssert softAssert = new SoftAssert();
        // Perform GET request using RestAssured
        Response response = RestAssured
                .given()
                .header("accept", "text/plain; v=1.0")
                .when()
                .get(BASE_URL + PathParameter);

        Activity[] data = response.as(Activity[].class);

        // Assert the status code is 200 (OK)
        response.then().statusCode(200);
        //Assert the data of jason array length
        softAssert.assertEquals(data.length, 30);
        //Assert all id values
        for (int i = 1; i <= data.length; i++) {
            softAssert.assertEquals(data[i - 1].getId(), i);
        }
        //Assert all Title values
        for (int i = 1; i <= data.length; i++) {
            softAssert.assertEquals(data[i - 1].getTitle(), "Activity " + i);
        }
        //Assert all completed values
        for (int i = 1; i <= data.length; i++) {
            if (i % 2 == 0) {
                softAssert.assertEquals(data[i - 1].isCompleted(), true);
            } else {
                softAssert.assertEquals(data[i - 1].isCompleted(), false);
            }
        }
        response.prettyPrint();
        softAssert.assertAll();
    }

    @Test
    public void TestPostActivitiesEndpoint() throws JsonProcessingException {
        SoftAssert softAssert = new SoftAssert();
        //apply  serialization for request body
        Activity obj_body = new Activity();
        obj_body.setId(0);
        obj_body.setTitle("title_x");
        obj_body.setDueDate("2023-12-29T16:34:25.943Z");
        obj_body.setCompleted(true);
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(obj_body);

        // Set the base URI for the API
        RestAssured.baseURI = BASE_URL;

        // Perform the POST request
        Response response = RestAssured.given()
                .header("accept", "text/plain; v=1.0")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(PathParameter)
                .then()
                .extract().response();

        response.prettyPrint();
        // make  Deserialization for response body
        Activity data = response.as(Activity.class);

        // Assert the status code is 200 (OK)
        response.then().statusCode(200);
        // Assert the id  value
        softAssert.assertEquals(data.getId(), obj_body.getId());
        // Assert the Title value
        softAssert.assertEquals(data.getTitle(), obj_body.getTitle());
        // Assert the DueDate value
        softAssert.assertEquals(data.getDueDate(), obj_body.getDueDate());
        // Assert the Completed value
        softAssert.assertEquals(data.isCompleted(), obj_body.isCompleted());

        softAssert.assertAll();
    }

    @Test(dataProvider = "listof_id")
    public void TestApiPathParameterActivitiesEndpoint(int id) {
        SoftAssert softAssert = new SoftAssert();

        // Perform GET request using RestAssured
        Response response = RestAssured
                .given().pathParam("id", id)
                .header("accept", "text/plain; v=1.0")
                .when()
                .get(BASE_URL + PathParameter + "/{id}");

        Activity data = response.as(Activity.class);

        // Assert the status code is 200 (OK)
        response.then().statusCode(200);
        // Assert id
        softAssert.assertEquals(data.getId(), id);
        // Assert Completed value
        if (id % 2 == 0) {
            softAssert.assertEquals(data.isCompleted(), true);
        } else {
            softAssert.assertEquals(data.isCompleted(), false);
        }

        softAssert.assertAll();
    }

    @Test
    public void TestPutActivitiesEndpoint() throws JsonProcessingException {
        SoftAssert softAssert = new SoftAssert();
        //apply  serialization for request body
        Activity obj_body = new Activity();
        obj_body.setId(111);
        obj_body.setTitle("title_x_new");
        obj_body.setDueDate("2023-12-29T16:34:25.943Z");
        obj_body.setCompleted(false);
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(obj_body);

        // Set the base URI for the API
        RestAssured.baseURI = BASE_URL;

        // Perform the POST request
        Response response = RestAssured.given().pathParam("id", 1)
                .header("accept", "text/plain; v=1.0")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(PathParameter + "/{id}")
                .then()
                .extract().response();

        response.prettyPrint();
        // make  Deserialization for response body
        Activity data = response.as(Activity.class);

        // Assert the status code is 200 (OK)
        response.then().statusCode(200);
        // Assert the id  value
        softAssert.assertEquals(data.getId(), obj_body.getId());
        // Assert the Title value
        softAssert.assertEquals(data.getTitle(), obj_body.getTitle());
        // Assert the DueDate value
        softAssert.assertEquals(data.getDueDate(), obj_body.getDueDate());
        // Assert the Completed value
        softAssert.assertEquals(data.isCompleted(), obj_body.isCompleted());

        softAssert.assertAll();
    }

    @Test
    public void TestDeletActivitiesEndpoint() throws JsonProcessingException {
        SoftAssert softAssert = new SoftAssert();
        // Set the base URI for the API
        RestAssured.baseURI = BASE_URL;

        // Perform the POST request
        Response response = RestAssured.given().pathParam("id", 1)
                .header("accept", "text/plain; v=1.0")
                .header("Content-Type", "application/json")
                .when()
                .delete(PathParameter + "/{id}")
                .then()
                .extract().response();

        response.prettyPrint();

        // Assert the status code is 200 (OK)
        response.then().statusCode(200);

        softAssert.assertAll();
    }
    @DataProvider
    public Object listof_id() {
        Object data[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        return data;
    }


}

