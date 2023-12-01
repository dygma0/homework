package com.example.homework.user.presentation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.homework.auth.JwtProvider;
import com.example.homework.fixture.UserFixture;
import com.example.homework.user.domain.User;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@SuppressWarnings("NonAsciiCharacters")
@Transactional
class UserHttpApiControllerTest {

  @Autowired private JwtProvider jwtProvider;
  @Autowired private EntityManager entityManager;
  @LocalServerPort private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void 내_정보_조회() {
    final var user = UserFixture.CREATE_TEST_USER();
    entityManager.persist(user);
    entityManager.flush();
    final var token = jwtProvider.generateToken(user.getUsername());

    final var response =
        RestAssured.given()
            .header("Authorization", "Bearer " + token)
            .when()
            .get("/api/v1/users/me")
            .then()
            .statusCode(200)
            .extract();

    final var jsonPath = response.body();
    assertThat(jsonPath.asString()).isEqualTo(user.getUsername());
  }

  @Test
  void 비_로그인_내_정보_조회_시_403_반환() {
    RestAssured.given().when().get("/api/v1/users/me").then().statusCode(403);
  }

  @Test
  void 토큰_발급() {
    final var response =
        RestAssured.given()
            .given()
            .param("username", "test")
            .contentType("application/json")
            .accept("application/json")
            .when()
            .post("/api/v1/users/token")
            .then()
            .statusCode(200)
            .extract();

    final var jsonPath = response.body();
    assertThat(jsonPath.asString()).isNotBlank();
  }
}
