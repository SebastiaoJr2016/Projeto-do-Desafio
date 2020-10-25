package com.thecat.DesafioApi;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TesteApi extends MassaDeDados {
	String vote_id;

	@BeforeClass
	public static void urlbase() {
		RestAssured.baseURI = "https://api.thecatapi.com/v1/";
	}

	@Test
	public void cadastro() {
		Response response = given().contentType("application/json").body(corpo).when().post(url);
		validacao(response);
	}

	@Test
	public void votacao() {
		Response response = given().contentType("application/json").body(corpoVotacao).when().post("votes/");
		validacao(response);
		String id = response.jsonPath().getString("id");
		vote_id = id;
		System.out.println("ID => " + id);

	}

	@Test
	public void deletaVotacao() {
		votacao();
		delataVoto();
	}

	private void delataVoto() {

		//String url = "https://api.thecatapi.com/v1/votes/{vote_id}";

		@SuppressWarnings("unused")
		Response response = given().contentType("application/json")
				.header("x-api-key", "8d605396-5bd7-437d-adce-66bdd3613053").pathParam("vote_id", vote_id).when()
				.delete("votes/{vote_id}");		
		//validacao(response);		
	}

	@Test
	public void favoritaDesfavorita() {
		favorita();
		desfavorita();
	}

	private void favorita() {
		Response response = given().contentType("application/json")
				.header("x-api-key", "8d605396-5bd7-437d-adce-66bdd3613053").body(corpoFavorita).when()
				.post("favourites");
		String id = response.jsonPath().getString("id");
		vote_id = id;
		validacao(response);
	}

	private void desfavorita() {
		Response response = given().contentType("application/json")
				.header("x-api-key", "8d605396-5bd7-437d-adce-66bdd3613053").pathParam("favourite_id", vote_id).when()
				.delete(corpoDesfavorita);
		validacao(response);

	}

	public void validacao(Response response) {

		response.then().statusCode(200).body("message", containsString("SUCCESS"));
		System.out.println("VALIDAÇÃO DA API => " + response.body().asString());
		System.out.println("-----------------------------------------------");
	}

}
