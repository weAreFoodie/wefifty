package controller;

import controller.action.Action;
import controller.action.ViewSignUpAction;
import controller.action.FriendRequestAction;
import controller.action.GetFriendListAction;
import controller.action.GetFriendRequestListAction;
import controller.action.UpdateFriendRequestAction;

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
		} else if (command.equals("GetFriendRequestList")) {
			action = new GetFriendRequestListAction();
		} else if (command.equals("UpdateFriendRequest")) {
			action = new UpdateFriendRequestAction();
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
}
