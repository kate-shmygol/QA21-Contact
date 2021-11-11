package com.telran.contact.api.tests;

import com.google.gson.Gson;
import com.telran.contact.api.dto.AuthErrorDto;
import com.telran.contact.api.dto.AuthRequestDto;
import com.telran.contact.api.dto.AuthResponseDto;
import okhttp3.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttpTests {

	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	@Test
	public void loginOkHttpTest() throws IOException {
		Gson gson = new Gson();
		OkHttpClient client = new OkHttpClient();

		AuthRequestDto requestDto = AuthRequestDto.builder()
				.email("krooos@gm.com")
				.password("Krooos12345~")
				.build();
		RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);

		Request request = new Request.Builder()
				.url("https://contacts-telran.herokuapp.com/api/login")
				.post(requestBody).build();
		Response response = client.newCall(request).execute();

		String responseJson = response.body().string();

		if (response.isSuccessful()) {
			AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class);
			System.out.println(responseDto.getToken());
		} else {
			AuthErrorDto error = gson.fromJson(responseJson, AuthErrorDto.class);
			System.out.println(error.getCode());
			System.out.println(error.getMessage());
		}
	}
}
