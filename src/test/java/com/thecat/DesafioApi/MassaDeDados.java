package com.thecat.DesafioApi;

public class MassaDeDados {
	String vote_id;
	String url = "user/passwordlesssignup"; 
	String corpo = "{\"email\": \"sebastiao.jr6@gmail.com\", \"appDescription\": \"teste the cat api\"}";
	String corpoVotacao = "{\"image_id\": \"2fg\", \"value\": \"true\", \"sub_id\": \"demo-a3320d\"}";
	String corpoFavorita = "{ \"image_id\": \"2uo\"}";
	String corpoDesfavorita = "favourites/{favourite_id}";
}
