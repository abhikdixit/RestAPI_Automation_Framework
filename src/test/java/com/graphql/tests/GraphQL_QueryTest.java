package com.graphql.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class GraphQL_QueryTest 

{
	@Test(priority=1)
	public void getAllFilmsTest()
	{
		RestAssured.baseURI = "https://swapi-graphql.netlify.app";
		String query = "{\"query\":\"{\\n  allFilms(first: 2) {\\n    films {\\n      created\\n      director\\n      title\\n    }\\n  }\\n}\"}";
		given().log().all()
			.contentType("application/json")
			.body(query)
			.when().log().all()
			.post("/.netlify/functions/index")
				.then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.body("data.allFilms.films[0].director", equalTo("George Lucas"));
		
	}

	
	@Test(priority=2)
	public void postPokemonAPI()
	{
		RestAssured.baseURI = "https://beta.pokeapi.co";
		String query = "{\"query\":\"{\\n  pokemon_v2_pokemon(limit: 10) {\\n    height\\n    id\\n    name\\n    order\\n    pokemon_species_id\\n  }\\n}\"}";
		given().log().all()
			.contentType("application/json")
			.body(query)
			.when().log().all()
			.post("/graphql/v1beta")
				.then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.body("data.pokemon_v2_pokemon[0].name", equalTo("bulbasaur"));
		
	}
}
