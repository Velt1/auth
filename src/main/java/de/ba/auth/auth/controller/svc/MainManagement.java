package de.ba.auth.auth.controller.svc;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import de.ba.auth.auth.lib.JsonReader;
import de.ba.auth.auth.model.*;
import de.ba.auth.auth.repo.*;


@Service
public class MainManagement {
	
	public User getUsername() {
		
		User user = new User();
		user.setUsername("Maier");
		
		//userRepo.save(user);
		return user;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject delegateManageApp( String data ) {
		
		JSONObject jobj = JsonReader.readJSONObject(data);		
		System.out.println(jobj);
		
		JSONObject jobjResponse = new JSONObject();
		jobjResponse.put("msg", "gotcha");
		jobjResponse.put("code", "OK");
		
		return jobjResponse;
	}
	
} // EoC