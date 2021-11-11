package com.telran.contact.api.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contact.api.dto.AuthRequestDto;
import com.telran.contact.api.dto.AuthResponseDto;
import com.telran.contact.api.dto.ContactDto;
import com.telran.contact.api.dto.GetAllContactDto;
import lombok.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;

public class RestAssuredTests {
	@BeforeMethod
	public void ensurePreconditions() {
		RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
		RestAssured.basePath = "api";
	}

	@Test
	public void loginRestAssuredTest() {
		AuthRequestDto requestDto = AuthRequestDto.builder()
				.email("krooos@gm.com")
				.password("Krooos12345~")
				.build();

		AuthResponseDto responseDto = given()
				.contentType("application/json")
				.body(requestDto)
				.post("login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().as(AuthResponseDto.class);

		System.out.println(responseDto.getToken());

		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imtyb29vc0BnbS5jb20ifQ.Py7pmu7xugNGolAS9otTKArdpxU6cohQr54s9_LEYmY";

		AuthResponseDto responseToken = given()
				.contentType("application/json")
				.body(requestDto)
				.post("login")
				.then()
				.assertThat().statusCode(200)
				.body(containsString("token"))
				.body("token", equalTo(token))
//				.statusCode(400) .extract().path("message"); System.out.println(errorMessage);
				.extract().response().as(AuthResponseDto.class);


//		System.out.println(responseDto.getToken());
		System.out.println(responseToken);
	}

	@Test
	public void getAllContactTest() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imtyb29vc0BnbS5jb20ifQ.Py7pmu7xugNGolAS9otTKArdpxU6cohQr54s9_LEYmY";

		GetAllContactDto responseDto = given()
				.header("Authorization", token)
				.get("contact")
				.then()
				.assertThat().statusCode(200)
				.extract().body().as(GetAllContactDto.class);

		for (ContactDto contact: responseDto.getContacts()) {
			System.out.println(contact.getId() + "***" + contact.getName() + "***");
			System.out.println("=======================================================");
		}
	}

	@Test
	public void deleteContactTest() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imtyb29vc0BnbS5jb20ifQ.Py7pmu7xugNGolAS9otTKArdpxU6cohQr54s9_LEYmY";
		String status = given()
				.header("Authorization", token)
				.delete("contact/18191") // 18191 - id from 18191***Karl***  - row #70 - contact.getId() + "***" + contact.getName() + "***"
				.then()
				.assertThat().statusCode(200)
				.extract().path("status");

		System.out.println(status);
	}
}
