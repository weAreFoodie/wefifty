package controller;

import controller.action.Action;
import controller.action.ViewSignUpAction;
import controller.action.FriendRequestAction;
import controller.action.GetFriendListAction;
import controller.action.LoginAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getHomeAction(String command) { // list
		Action action = null;

		if (command.equals("FriendRequest")) {
			action = new FriendRequestAction();
		} else if (command.equals("GetFriendList")) {
			action = new GetFriendListAction();
		}

		return action;
	}
	
	public Action getSignUpAction(String command) {
		Action action = null;
		
		if(command.equals("login")) {
			
		}else if(command.equals("signup")) {
			action = new ViewSignUpAction();
		}
		
		return action;
	}
	
	public Action getLoginAction(String command) {
		Action action = null;
		if(command.equals("login")) {
			action = new LoginAction();
		}else {
			
		}
		
		return action;
	}
}
