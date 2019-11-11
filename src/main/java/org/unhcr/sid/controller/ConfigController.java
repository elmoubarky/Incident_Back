package org.unhcr.sid.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.unhcr.sid.entities.Url;
import org.unhcr.sid.services.UrlService;

import lombok.Data;

@CrossOrigin("*")
@RestController
public class ConfigController {
	/*
	 * 
	 * @Autowired private UrlService urlService;
	 * 
	 * @Autowired public RestTemplate restTemplate;
	 * 
	 * // methode permettant de creer et retourner un objet de type Url
	 * 
	 * @PostMapping("/createUrl") public Url createUrl(@RequestBody UrlForm urlForm)
	 * { System.out.println("----------Url Create ------------"); return
	 * urlService.saveUrl(urlForm.getName(), urlForm.getPort(),
	 * urlForm.getDescription()); }
	 * 
	 * @RequestMapping(value = "/register/user", method = RequestMethod.POST) public
	 * ResponseEntity<String> createUser(@RequestBody UserForm userForm) {
	 * HttpHeaders headers = new HttpHeaders(); System.out.println(userForm); return
	 * new ResponseEntity<String>(HttpStatus.CREATED); }
	 * 
	 * @RequestMapping(value = "/register/create", method = RequestMethod.POST)
	 * public String createProducts(@RequestBody UserForm userForm) {
	 * 
	 * String url = "http://localhost:8086/register"; HttpHeaders headers = new
	 * HttpHeaders(); headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * HttpEntity<UserForm> entity = new HttpEntity<UserForm>(userForm,headers);
	 * System.out.println(userForm);
	 * 
	 * String result = restTemplate.exchange( url, HttpMethod.POST, entity,
	 * String.class).getBody();
	 * 
	 * System.out.println("--- result ------------- "+result);
	 * 
	 * return result; }
	 * 
	 * @RequestMapping(value = "/register/createUser", method = RequestMethod.POST)
	 * public String getRegister(@RequestBody UserForm userForm) { String url =
	 * "http://localhost:8086/register";
	 * 
	 * // Data attached to the request. HttpEntity<UserForm> requestBody = new
	 * HttpEntity<>(userForm);
	 * 
	 * // Send request with POST method. ResponseEntity<User> result =
	 * restTemplate.postForEntity(url, requestBody, User.class);
	 * 
	 * System.out.println("--- response ------------- "+result);
	 * System.out.println("Status code :" + result.getStatusCode());
	 * 
	 * // Code = 200. if (result.getStatusCode() == HttpStatus.OK) {
	 * 
	 * User e = result.getBody(); System.out.println("(User Side) User Created: "+
	 * e.getUsername()); }else { System.out.println("Error execution code:" +
	 * result.getStatusCode()); } return result.getStatusCode().toString(); }
	 * 
	 * 
	 * //methode pour la creation d'un user en appellant l'autre micro service
	 * security // Api pour Afficher plusieurs images
	 * 
	 * @PostMapping("/template/register") public ResponseEntity<UserForm>
	 * register(@ResponseBody UserForm userForm) { String url =
	 * "https://picsum.photos/v2/list"; UserForm user = new UserForm();
	 * ResponseEntity<UserForm> response = restTemplate.exchange( url,
	 * HttpMethod.POST, null, new ParameterizedTypeReference<UserForm>(){}); user =
	 * response.getBody(); return user;
	 * 
	 * }
	 * 
	 * 
	 */}

/*
 * @Data class UrlForm { private String name; private String port; private
 * String description; }
 * 
 * @Data class UserForm { private String username; private String password;
 * private String confirmedPassword; private String telephone; private String
 * email; }
 * 
 * @Data class User { private long iduser; private String username; private
 * String password; private String confirmedPassword; private String telephone;
 * private String email; }
 * 
 * @Data class UserUpdateForm { private long iduser; private String username;
 * private String telephone; private String email; }
 * 
 * @Data class UserChangePasswordForm { private String username; private String
 * password; private String lastpassword; }
 * 
 * @Data class RoleForm { private String rolename;
 * 
 * }
 */