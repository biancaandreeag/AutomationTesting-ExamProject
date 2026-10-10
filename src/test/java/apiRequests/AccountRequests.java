package apiRequests;

import configUtility.SiteUrls;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import logger.LoggerUtility;
import objectData.AccountObject;

// conturi create si sterse prin API: pregatesc datele unui test fara sa trec prin formularul de inregistrare
public class AccountRequests {

    // POST /api/createAccount: contul se creeaza direct pe server, cu datele din accountData.xml
    public static void createAccount(AccountObject data, String email) {
        Response response = RestAssured.given()
                .formParam("name", data.getName())
                .formParam("email", email)
                .formParam("password", data.getPassword())
                .formParam("title", "Mrs")
                .formParam("birth_date", data.getDay())
                .formParam("birth_month", data.getMonth())
                .formParam("birth_year", data.getYear())
                .formParam("firstname", data.getFirstName())
                .formParam("lastname", data.getLastName())
                .formParam("address1", data.getAddress())
                .formParam("country", data.getCountry())
                .formParam("state", data.getState())
                .formParam("city", data.getCity())
                .formParam("zipcode", data.getZipcode())
                .formParam("mobile_number", data.getMobileNumber())
                .post(SiteUrls.API_URL + "/createAccount");
        // API-ul raspunde mereu cu 200; rezultatul real e in responseCode din body
        if (!response.asString().contains("\"responseCode\": 201")) {
            throw new RuntimeException("Contul nu a fost creat prin API: " + response.asString());
        }
        LoggerUtility.infoTest("Account created through API: " + email);
    }

    // apelat la finalul testului, ca pe site sa nu ramana conturi de test
    public static void deleteAccount(String email, String password) {
        RestAssured.given()
                .formParam("email", email)
                .formParam("password", password)
                .delete(SiteUrls.API_URL + "/deleteAccount");
        LoggerUtility.infoTest("Account deleted through API: " + email);
    }
}
