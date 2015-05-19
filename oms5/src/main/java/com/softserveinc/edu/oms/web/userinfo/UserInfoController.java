package com.softserveinc.edu.oms.web.userinfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;

@Controller
public class UserInfoController {

	private static final String USER = "user";
	private static User loggedUser;

	@RequestMapping(value = "userInfo.htm")
	public String userInfo(final ModelMap map) {

		loggedUser = SessionExplorer.getLoggedUser();

		map.put(USER, loggedUser);
		return "userinfo";
	}

	public static User getLoggedUser() {
		if (loggedUser != null) {
			return loggedUser;
		}
		return null;
	}
}
