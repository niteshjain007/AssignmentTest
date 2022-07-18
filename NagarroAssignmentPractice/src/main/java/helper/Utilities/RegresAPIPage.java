package helper.Utilities;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RegresAPIPage extends GeneralUtility {

    public static final Logger log = Logger.getLogger(RegresAPIPage.class.getName());
    public static Integer StatusCode;

    public static String responseStringBody;

    public static RequestSpecification request;
    public static Response resp;

    public void setBaseURI(String uriAdress)
    {
    	RestAssured.baseURI = uriAdress;
    }
    public void getUsers(String pageNumber) throws IOException {
        //RestAssured.baseURI = PropertyHandler.readConfig("reqresBaseURI");
        request = given().log().all();
        request.header("Content-Type", "application/json");

        resp = request.get("api/users?page="+pageNumber);
        log.info("Response of GET Users : " + resp.getBody().asString());

        StatusCode = resp.getStatusCode();
    }
    public void verifyStatusCodeOfGetUser(int statusCode) {
        Assert.assertTrue(StatusCode == statusCode);
        testlog.log(Status.INFO, "Response Status Code" + StatusCode);

        responseStringBody = resp.getBody().asString();
        testlog.log(Status.INFO, "Response Body <textarea>" + responseStringBody + "</textarea>");

    }
    public void verifyUser(String field,String id, String value)
    {


        JSONArray users = JsonUtility.getArrayAt(responseStringBody,"$.data");
        for(int i=0; i< users.size();i++)
        {
            if(JsonUtility.getValueAt(responseStringBody,"$.data["+i+"].id").equalsIgnoreCase(id))
            {
                if(JsonUtility.getValueAt(responseStringBody,"$.data["+i+"]."+field).equals(value))
                {
                	testlog.log(Status.PASS, "the value of "+field+" for <B>id : "+id+"</B> is "+value);
                }
                else
                {
                	testlog.log(Status.PASS, "the value of "+field+" for <B>id : "+id+"</B> is not  "+value);
                }
            }
        }
       }
    public void createUser(String name, String job)
    {
        RestAssured.baseURI = PropertyUtility.readConfig("reqresBaseURI");
        request = given().log().all();
        request.header("Content-Type", "application/json");
        request.header("Accept","*/*");

        String requestbody = "{\"name\":\"Bryant\",\"job\":\"BA\"}";

        HashMap<String,String> pathAndValuesForUpdate = new HashMap<String,String>();
        pathAndValuesForUpdate.put("$.name",name);
        pathAndValuesForUpdate.put("$.job",job);

        requestbody = JsonUtility.setValues(requestbody,pathAndValuesForUpdate);
        request.body(requestbody);

        resp = request.post("api/users");
        log.info("Response of POST Users : " + resp.getBody().asString());

        StatusCode = resp.getStatusCode();
    }

    public void verifyID()
    {
        String id = JsonUtility.getValueAt(responseStringBody,"$.id");
        if(id!=null &&id.isEmpty()!=true)
        {
        	testlog.log(Status.PASS,"Id got generated successfully with value :: <B>"+id+"<B>");
        }
        else {
        	testlog.log(Status.FAIL,"Id not got generated !!");
        }
    }
    public void validateJSONSchemaForPostUser()
    {
        try {
            resp.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/jsonSchemas/createUserJsonSchema.json")));
            testlog.log(Status.PASS,"JSONSchema Validation PASS !!");
        }
        catch(java.lang.AssertionError e)
        {
        	testlog.log(Status.FAIL,"JSONSchema Validation fail : <textarea>"+e+"</textarea>");

        }
    }
}
