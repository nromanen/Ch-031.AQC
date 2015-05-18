package com.softserveinc.edu.oms.web.user;

import javax.servlet.http.HttpServletRequest;
import com.softserveinc.edu.oms.web.userinfo.UserInfoController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.user.model.UserModel;

@Controller
@RequestMapping("editUser.htm")
public class EditUserController extends AbstractFormUserController {

	private static final String USER_ID = "userID";
	private static boolean currentUserIsSelected;
	private User loggedUser;


	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String prepareForm(final ModelMap modelMap,
			final HttpServletRequest request) {
		String param = request.getParameter(USER_ID);
		Integer id;

		if (param == null) {
			id = (Integer) request.getSession(true).getAttribute(USER_ID);
		} else {
			id = Integer.parseInt(param);
			request.getSession(true).setAttribute(USER_ID, id);
		}

		User selectedUser = userService.findByID(id);
		if (selectedUser == null) {
			return "redirect:users.htm";
		}

		loggedUser = UserInfoController.getLoggedUser();
		currentUserIsSelected = selectedUser.equals(loggedUser);
		UserModel userModel = createUserModel(selectedUser);

		modelMap.addAttribute("userModel", userModel);
		return "userEditForm";
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("userModel") final UserModel userModel,
			final BindingResult result) {
		editUserValidator.validate(userModel, result);

		if (result.hasErrors()) {
			return "userEditForm";
		}

		Integer id = Integer.parseInt(userModel.getId());
		if (id == null) {
			return "errorPage";
		}
		User user = userService.findByID(id);
		if (user != null) {
			updateUser(userModel, user);
		} else {
			return "errorPage";
		}

		userService.insertOrUpdate(user);
		
		if (currentUserIsSelected && !loggedUser.getRole().equals(user.getRole())) {

			return "redirect:logout.htm";

		}

		return "redirect:users.htm";
	}
	
	private void updateUser(UserModel userModel, User user) {
		
		if (userModel.getFirstName().length() > 0) {
			user.setFirstName(userModel.getFirstName());
		} 
		if (userModel.getLastName().length() > 0) {
			user.setLastName(userModel.getLastName());
		} 
		if (userModel.getPassword().length() > 0
				&& userModel.getPassword().equals(
						userModel.getConfirmPassword())) {
			user.setPassword(userModel.getPassword());
		}
		if (userModel.getEmail().length() > 0) {
			user.setEmail(userModel.getEmail());
		}
		
		Integer roleID = Integer.parseInt(userModel.getRoleID());
		if (!roleID.equals(user.getRole().getId())){
			user.setRole(roleService.findByID(roleID));
		}

		Integer regionID = Integer.parseInt(userModel.getRegionID());
		if (!regionID.equals(user.getRegion().getId())){
			user.setRegion(regionService.findByID(regionID));
		}

		if (userModel.getCustomerTypeID() != null
				&& userModel.getCustomerTypeID().trim().length() != 0) {
			Integer customerTypeID = Integer.parseInt(userModel
					.getCustomerTypeID());
			if (!customerTypeID.equals(user.getCustomerType().getId())) {
				user.setCustomerType(customerTypeService
						.findByID(customerTypeID));
			}
		}
		
		if (userModel.getBalance() != null
				&& userModel.getBalance().trim().length() != 0) {
			if (!user.getBalance().equals(
					Double.parseDouble(userModel.getBalance())))
				user.setBalance(Double.parseDouble(userModel.getBalance()));
		}
	}

	private UserModel createUserModel(final User user) {
		UserModel userModel = new UserModel();

		userModel.setId(user.getId().toString());
		userModel.setLogin(user.getLogin());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setEmail(user.getEmail());
		userModel.setPassword(user.getPassword());
		userModel.setConfirmPassword(user.getPassword());
		userModel.setRegionID(user.getRegion().getId().toString());
		userModel.setRoleID(user.getRole().getId().toString());

		return userModel;
	}
}
