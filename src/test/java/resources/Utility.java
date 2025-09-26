package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.*;
import java.util.Properties;

public class Utility {
    public static RequestSpecification reqSpec;
    public RequestSpecification getReqSpec() throws FileNotFoundException {
        if(reqSpec==null)
        {
            PrintStream log = new PrintStream(new FileOutputStream("APILogs.text"));
            reqSpec= new RequestSpecBuilder().setBaseUri(getBaseURI()).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return reqSpec;
        }
        return reqSpec;
    }

    public String readProperty(String property) {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/java/Configurations/global.properties")) {
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(property);
    }

    public String getBaseURI()
    {
        return readProperty("BaseURI");
    }

    public String getJsonPath(Response response, String key)
    {
        return new JsonPath(response.asString()).get(key).toString();
    }
}
